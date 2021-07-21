package TTL;

import TTL.controllers.*;
import TTL.controllers.layers.ByBranchCodeLayers;
import TTL.controllers.layers.ByOrderTypeLayers;
import TTL.controllers.listWorkers.BranchWorker;
import TTL.controllers.listWorkers.NodeWorker;
import TTL.controllers.listWorkers.OrderWorker;
import TTL.controllers.dataloader.CsvLoader;
import TTL.controllers.dataloader.CsvLoaderFactory;
import TTL.models.*;
import org.openjdk.jmh.annotations.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@BenchmarkMode(Mode.All)
@Warmup(iterations = 2)
@State(Scope.Benchmark)
public class DijkstraRunner {

    private List<Order> orders;
    private List<Node> nodes;
    private List<Edge> edges;
    private List<Branch> branches;

    //private HashMap<String, Order> ordersHashMap;
    private HashMap<String, Node> branchNodes;

    ByBranchCodeLayers byBranchCodeLayer;
    ByOrderTypeLayers byOrderTypeLayers;


    private static final HashMap<String,String> csvPaths = new HashMap<>(){{
        put("branches","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\branches.csv");
        put("edges","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\edges.csv");
        put("nodes","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\nodes.csv");
        put("orders","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\orders.csv");
    }};

    public List<Order> getOrders() { return orders; }


    public DijkstraRunner(){
        uploadDataFromCsvFiles();
        byBranchCodeLayer = new ByBranchCodeLayers(orders);
        byOrderTypeLayers = new ByOrderTypeLayers(orders);
    }

    //@Benchmark
    private void uploadDataFromCsvFiles()
    {
        CsvLoaderFactory loaderFactory = new CsvLoaderFactory();

        CsvLoader branchLoader = loaderFactory.createCsvLoader("branches");
        branches = branchLoader.csvToList(csvPaths.get("branches"));

        System.out.println("branches " + branches.size());

        CsvLoader edgeLoader = loaderFactory.createCsvLoader("edges");
        edges = edgeLoader.csvToList(csvPaths.get("edges"));

        System.out.println("edges " + edges.size());

        CsvLoader nodeLoader = loaderFactory.createCsvLoader("nodes");
        nodes = nodeLoader.csvToList(csvPaths.get("nodes"));

        System.out.println("nodes " + nodes.size());

        CsvLoader orderLoader = loaderFactory.createCsvLoader("orders");
        orders = orderLoader.csvToList(csvPaths.get("orders"));

        System.out.println("orders " + orders.size());
    }

    @Benchmark
    public HashMap<Node,List<Node>> getShortestForAllOrdersLinear()
    {
        System.out.println("Linear");
        return byBranchCodeLayer.getLayers()
                .entrySet()
                .stream()
                .map(branchOrders -> computePathesForLayer(branchOrders.getKey(),branchOrders.getValue(),"Linear"))
                .reduce(this::merge)
                .orElse(new HashMap<>() );
    }

    //@Benchmark
    public HashMap<Node,List<Node>> getShortestForAllOrdersParallel()
    {
        System.out.println("Parallel");
        return byBranchCodeLayer.getLayers()
                .entrySet()
                .parallelStream()
                .map(branchOrders -> computePathesForLayer(branchOrders.getKey(),branchOrders.getValue(),"Parallel"))
                .reduce(this::merge)
                .orElse(new HashMap<>() );
    }

    private HashMap<Node,List<Node>> merge(HashMap<Node,List<Node>> x,HashMap<Node,List<Node>> y)
    {
        x.putAll(y);
        return x;
    }

    private HashMap<Node,List<Node>> computePathesForLayer(String branch,List<Order> branchOrders,String type)
    {
        HashMap<Node,List<Node>> shortPathes = new HashMap<>();
        NodeWorker nodeWorker = new NodeWorker(nodes);
        BranchWorker branchWorker = new BranchWorker(branches);

        branchNodes = branchWorker.toBranchNodeHashMap(nodeWorker);
        Dijkstra dijkstra = new Dijkstra();

        GraphCreator graphBuilder = new GraphCreator(nodes,edges);
        HashMap<Long, Node> graph = graphBuilder.createGraph();
        dijkstra.setNodesHashMap(graph);

        Node branchNode = branchNodes.get(branch);
        System.out.println("Layer of " + branchNode);
        System.out.println("____________________________________________________________");

        dijkstra.computePathesFrom(branchNode);
        String fileName = branch + type + ".txt";
        NodesToFileWriter.createFile(fileName);

        branchOrders.forEach(order ->{
            Node nodeTo = nodeWorker.getNodeByCoordinates(order.getLatitude(),order.getLongtitude());
            if(nodeTo.getId() != 0 && nodeTo.getMinDistance() < 1000000000) {
                System.out.println("\n Current node : " + nodeTo);
                List<Node> pathToCurrentNode = dijkstra.getShortestPathTo(nodeTo);
                if(!pathToCurrentNode.isEmpty())
                {
                    double datasetDistanceToInMetres = order.getDistanceTo() * 1000;
                    double epsilon = nodeTo.getMinDistance() - datasetDistanceToInMetres ;

                    nodeTo.setEpsilon(epsilon);
                    shortPathes.put(nodeTo, pathToCurrentNode);

                    NodesToFileWriter.writeResultInFile(fileName,nodeTo,pathToCurrentNode,datasetDistanceToInMetres,nodeTo.getMinDistance(),epsilon);
                }
            }
        });
        dijkstra.clearGraph();
        return shortPathes;
    }
}
