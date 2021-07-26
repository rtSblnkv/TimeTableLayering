package TTL;

import TTL.controllers.layers.*;
import TTL.controllers.listWorkers.*;
import TTL.controllers.dataloader.CsvLoader;
import TTL.controllers.dataloader.CsvLoaderFactory;
import TTL.models.*;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@BenchmarkMode(Mode.All)
@Warmup(iterations = 2)
@State(Scope.Benchmark)
public class DijkstraRunner {

    private List<Order> orders;
    private List<Node> nodes;
    private List<Edge> edges;
    private List<Branch> branches;

    private HashMap<String, Node> branchNodes;

    private ByBranchCodeLayers byBranchCodeLayer;
    private ByOrderTypeLayers byOrderTypeLayers;

    private static final HashMap<String,String> csvPaths = new HashMap<>(){{
        put("branches","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\branches.csv");
        put("edges","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\edges.csv");
        put("nodes","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\nodes.csv");
        put("orders","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\orders.csv");
    }};

    /**
     * get the list of Orders
     * @return list of Orders
     */
    public List<Order> getOrders() { return orders; }


    /**
     * Constructor. Upload data from csv files into lists, split orders on layers by Branch code and order type.
     */
    public DijkstraRunner(){
        uploadDataFromCsvFiles();
        byBranchCodeLayer = new ByBranchCodeLayers(orders);
        byOrderTypeLayers = new ByOrderTypeLayers(orders);

        NodeWorker nodeWorker = new NodeWorker(nodes);
        BranchWorker branchWorker = new BranchWorker(branches);

        branchNodes = branchWorker.toBranchNodeHashMap(nodeWorker);
    }

    @Benchmark
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

    /**
     * Linear computing of shortest pathes and distances for each order in list orders splitted by branch code
     * @return Map(Node, lists,which contains shortest path to the Node from restaurant)
     */
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

    /**
     * Parallel computing of shortest pathes and distances for each order in list orders splitted by branch code
     * @return Map(Node, lists,which contains shortest path to the Node from restaurant)
     */
    @Benchmark
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

    private HashMap<Node,List<Node>> computePathesForLayer(String branch,List<Order> orders,String type)
    {
        HashMap<Node,List<Node>> shortPathes = new HashMap<>();

        List<Node> nodesClone = new ArrayList<>();

        NodeWorker nodeWorker  = new NodeWorker(nodesClone);

        Dijkstra dijkstra = new Dijkstra();

        GraphCreator graphBuilder = new GraphCreator(nodesClone,edges);
        HashMap<Long, Node> graph = graphBuilder.createGraph();
        dijkstra.setGraph(graph);

        Node branchNode = branchNodes.get(branch);
        System.out.println("Layer of " + branchNode);
        System.out.println("____________________________________________________________");

        dijkstra.computeMinDistancesfrom(branchNode);
        String fileName = "resources/"+ branch + type + ".txt";
        NodesToFileWriter.createFile(fileName);

        orders.forEach(order ->{
            Node nodeTo = nodeWorker.getNodeByCoordinates(order.getLatitude(),order.getLongtitude());
            if(nodeTo.getId() != 0) {
                System.out.println("\n Current node : " + nodeTo);
                List<Node> pathToCurrentNode = dijkstra.getShortestPathTo(nodeTo);
                if(!pathToCurrentNode.isEmpty())
                {
                    double datasetDistanceToInMetres = order.getDistanceTo() * 1000;
                    double epsilon = nodeTo.getMinDistance() - datasetDistanceToInMetres ;
                    if( nodeTo.getMinDistance() > 1000000)
                    {
                        NodesToFileWriter.writeResultInFile("strange.txt",nodeTo,pathToCurrentNode,datasetDistanceToInMetres,nodeTo.getMinDistance(),epsilon);
                    }
                    else{
                        nodeTo.setEpsilon(epsilon);
                        shortPathes.put(nodeTo, pathToCurrentNode);
                        NodesToFileWriter.writeResultInFile(fileName,nodeTo,pathToCurrentNode,datasetDistanceToInMetres,nodeTo.getMinDistance(),epsilon);
                    }
                }
            }
            else
            {
                System.out.println(nodeTo + " Short path doesn't exist.");
                NodesToFileWriter.writeResultInFile(fileName,nodeTo,null,Double.NaN,nodeTo.getMinDistance(),Double.NaN);
            }
        });
        return shortPathes;
    }
}
