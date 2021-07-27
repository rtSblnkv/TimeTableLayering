package TTL.services.graphServices;

import TTL.services.NodesToFileWriter;
import TTL.dataloaders.CsvLoader;
import TTL.dataloaders.CsvLoaderFactory;
import TTL.controllers.layers.ByBranchCodeLayers;
import TTL.controllers.layers.ByOrderTypeLayers;
import TTL.services.listWorkers.BranchWorker;
import TTL.services.listWorkers.NodeWorker;
import TTL.models.Branch;
import TTL.models.Edge;
import TTL.models.Node;
import TTL.models.Order;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Class which runs dijkstra algorithm for list of orders
 * in different variations:
 * for full list of orders
 * linear and parallel for splitted by
 *  - order type
 *  - branch code
 *  linear and parallel for splitted on branch code orders
 *  with preliminary launching of the dijkstra algorithm for each branch location
 */
@BenchmarkMode(Mode.All)
@Warmup(iterations = 2)
@State(Scope.Benchmark)
public class DijkstraRunner {

    private List<Order> orders;
    private List<Node> nodes;
    private List<Edge> edges;
    private List<Branch> branches;

    private HashMap<String, Node> branchNodes;

    private ByBranchCodeLayers byBranchCodeLayers;
    private ByOrderTypeLayers byOrderTypeLayers;

    private static final HashMap<String,String> csvPaths = new HashMap<>(){{
        put("branches","C:\\Users\\роппг\\IdeaProjects\\TimeTableLayering\\src\\main\\resources\\branches.csv");
        put("edges","C:\\Users\\роппг\\IdeaProjects\\TimeTableLayering\\src\\main\\resources\\edges.csv");
        put("nodes","C:\\Users\\роппг\\IdeaProjects\\TimeTableLayering\\src\\main\\resources\\nodes.csv");
        put("orders","C:\\Users\\роппг\\IdeaProjects\\TimeTableLayering\\src\\main\\resources\\orders.csv");
    }};

    public DijkstraRunner(){ }

    /**
     * Uploads data from dataset with csvToList methods
     * initialize branches,edges,nodes,orders.
     */
    @Benchmark
    public void uploadDataFromCsvFiles()
    {
        try {
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
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Linear computing of shortest pathes and distances for each order in full list of orders
     * @return Map(Node, lists,which contains shortest path to the Node from restaurant)
     */
    @Benchmark
    public HashMap<Node,List<Node>> getShortestForAllOrders()
    {
        System.out.println("Linear All orders");
        return computePathes(orders,"","orders");
    }

    /**
     * Linear computing of shortest pathes and distances for each order in list orders splitted by order type
     * @return Map(Node, lists,which contains shortest path to the Node from restaurant)
     */
    @Benchmark
    public HashMap<Node,List<Node>> getShortestForAllOrdersByOrderTypeLinear()
    {
        byOrderTypeLayers = new ByOrderTypeLayers(orders);
        System.out.println("Order Type Linear");
        return byOrderTypeLayers.getLayers()
                .entrySet()
                .stream()
                .map(branchOrders -> computePathes(branchOrders.getValue(),branchOrders.getKey(),"OrderTypeLinear"))
                .reduce(this::merge)
                .orElse(new HashMap<>() );
    }

    /**
     * Parallel computing of shortest pathes and distances for each order in list orders splitted by order type
     * @return Map(Node, lists,which contains shortest path to the Node from restaurant)
     */
    @Benchmark
    public HashMap<Node,List<Node>> getShortestForAllOrdersByOrderTypeParallel()
    {
        byOrderTypeLayers = new ByOrderTypeLayers(orders);
        System.out.println(" Order Type Parallel");
        return byOrderTypeLayers.getLayers()
                .entrySet()
                .parallelStream()
                .map(branchOrders -> computePathes(branchOrders.getValue(),branchOrders.getKey(),"OrderTypeParallel"))
                .reduce(this::merge)
                .orElse(new HashMap<>() );
    }

    /**
     * Linear computing of shortest pathes and distances for each order in list orders splitted by branch code
     * @return Map(Node, lists,which contains shortest path to the Node from restaurant)
     */
    @Benchmark
    public HashMap<Node,List<Node>> getShortestForAllOrdersByBranchCodeLinear()
    {
        byBranchCodeLayers = new ByBranchCodeLayers(orders);
        System.out.println("Branch Code Linear");
        return byBranchCodeLayers.getLayers()
                .entrySet()
                .stream()
                .map(branchOrders -> computePathes(branchOrders.getValue(),branchOrders.getKey(),"BranchCodeLinear"))
                .reduce(this::merge)
                .orElse(new HashMap<>() );
    }

    /**
     * Parallel computing of shortest pathes and distances for each order in list orders splitted by branch code
     * @return Map(Node, lists,which contains shortest path to the Node from restaurant)
     */
    @Benchmark
    public HashMap<Node,List<Node>> getShortestForAllOrdersByBranchCodeParallel()
    {
        byBranchCodeLayers = new ByBranchCodeLayers(orders);
        System.out.println("Branch Code Parallel");
        return byBranchCodeLayers.getLayers()
                .entrySet()
                .parallelStream()
                .map(branchOrders -> computePathes(branchOrders.getValue(),branchOrders.getKey(),"BranchCodeParallel"))
                .reduce(this::merge)
                .orElse(new HashMap<>() );
    }

    /**
     * Linear computing of shortest pathes and distances for each order in list orders splitted by branch code
     * Using computePathesForLayer method
     * @return Map(Node, lists,which contains shortest path to the Node from restaurant)
     */
    @Benchmark
    public HashMap<Node,List<Node>> getShortestForOrdersByBranchCodeLinear()
    {
        byBranchCodeLayers = new ByBranchCodeLayers(orders);
        System.out.println("Branch Code Linear by Layer");
        return byBranchCodeLayers.getLayers()
                .entrySet()
                .stream()
                .map(branchOrders -> computePathesForLayer(branchOrders.getKey(),branchOrders.getValue(),"BranchCodeLinear"))
                .reduce(this::merge)
                .orElse(new HashMap<>() );
    }

    /**
     * Parallel computing of shortest pathes and distances for each order in list orders splitted by branch code
     * Using computePathesForLayer method
     * @return Map(Node, lists,which contains shortest path to the Node from restaurant)
     */
    @Benchmark
    public HashMap<Node,List<Node>> getShortestForOrdersByBranchCodeParallel() {
        byBranchCodeLayers = new ByBranchCodeLayers(orders);
        System.out.println("Branch Code Parallel by Layer");
        return byBranchCodeLayers.getLayers()
                .entrySet()
                .parallelStream()
                .map(branchOrders -> computePathesForLayer(branchOrders.getKey(),branchOrders.getValue(),"BranchCodeParallel"))
                .reduce(this::merge)
                .orElse(new HashMap<>() );
    }

    private HashMap<Node,List<Node>> merge(HashMap<Node,List<Node>> x,HashMap<Node,List<Node>> y) {
        x.putAll(y);
        return x;
    }

    private HashMap<Node,List<Node>> computePathes(List<Order> orders,String splitter,String algorithmType) {
        HashMap<Node,List<Node>> shortPathes = new HashMap<>();

        orders.forEach(order -> {

            List<Node> nodesClone = new ArrayList<>();
            for(Node node: nodes) {
                nodesClone.add(node.clone());
            }

            NodeWorker nodeWorker = new NodeWorker(nodesClone);
            BranchWorker branchWorker = new BranchWorker(branches);

            branchNodes = branchWorker.toBranchNodeHashMap(nodeWorker);
            Node startNode = branchNodes.get(order.getBranchCode().toUpperCase());

            GraphCreator graphBuilder = new GraphCreator(nodesClone,edges);
            HashMap<Long, Node> graph = graphBuilder.createGraph();

            Dijkstra dijkstra = new Dijkstra();
            dijkstra.setGraph(graph);
            dijkstra.computeMinDistancesfrom(startNode);

            String fileName = "results\\" + splitter +"_"+ algorithmType + ".txt";
            String noShortPathFileName = "results\\"+ splitter +"_no_short_path.txt";
            String nodeNotExistFileName = "results\\"+ splitter +"_strange.txt";

            NodesToFileWriter.createFile(fileName);
            NodesToFileWriter.createFile(noShortPathFileName);
            NodesToFileWriter.createFile(nodeNotExistFileName);

            Node nodeTo = nodeWorker.getNodeByCoordinates(order.getLatitude(),order.getLongtitude());

            if(!nodeTo.equals(new Node())) {
                List<Node> pathToCurrentNode = dijkstra.getShortestPathTo(nodeTo);

                if(!pathToCurrentNode.isEmpty())
                {
                    double datasetDistanceToInMetres = order.getDistanceTo() * 1000;
                    double epsilon = nodeTo.getMinDistance() - datasetDistanceToInMetres ;

                    if( nodeTo.getMinDistance() > 1000000)
                    {
                        NodesToFileWriter.writeResultInFile(
                                noShortPathFileName, nodeTo,
                                pathToCurrentNode, datasetDistanceToInMetres,
                                nodeTo.getMinDistance(), epsilon);
                    } else{
                        nodeTo.setEpsilon(epsilon);
                        shortPathes.put(nodeTo, pathToCurrentNode);
                        NodesToFileWriter.writeResultInFile(
                                fileName, nodeTo,
                                pathToCurrentNode, datasetDistanceToInMetres,
                                nodeTo.getMinDistance(), epsilon);
                    }
                } else {
                    System.out.println(nodeTo + "Short path doesn't exist");
                }
            } else {
                nodeTo.setLongtitude(order.getLongtitude());
                nodeTo.setLatitude(order.getLatitude());
                NodesToFileWriter.writeResultInFile(
                        nodeNotExistFileName, nodeTo,
                        new ArrayList<>(), Double.NaN,
                        nodeTo.getMinDistance(), Double.NaN);
            }
        });
        return shortPathes;
    }

    private HashMap<Node,List<Node>> computePathesForLayer(String branch,List<Order> orders,String type) {
        HashMap<Node,List<Node>> shortPathes = new HashMap<>();

        String fileName = "results\\"+branch +"_"+ type + ".txt";
        String noShortPathFileName = "results\\"+branch +"_"+ type +"_no_short_path.txt";
        String nodeNotExistFileName = "results\\"+branch +"_"+ type +"_strange.txt";

        NodesToFileWriter.createFile(fileName);
        NodesToFileWriter.createFile(noShortPathFileName);
        NodesToFileWriter.createFile(nodeNotExistFileName);


        List<Node> nodesClone = new ArrayList<>();
        for(Node node: nodes) {
            nodesClone.add(node.clone());
        }

        NodeWorker nodeWorker = new NodeWorker(nodesClone);
        BranchWorker branchWorker = new BranchWorker(branches);

        branchNodes = branchWorker.toBranchNodeHashMap(nodeWorker);
        Node branchNode = branchNodes.get(branch);

        GraphCreator graphBuilder = new GraphCreator(nodesClone,edges);
        HashMap<Long, Node> graph = graphBuilder.createGraph();

        Dijkstra dijkstra = new Dijkstra();
        dijkstra.setGraph(graph);
        dijkstra.computeMinDistancesfrom(branchNode);

        orders.forEach(order ->{
            Node nodeTo = nodeWorker.getNodeByCoordinates(order.getLatitude(),order.getLongtitude());
            if(!nodeTo.equals(new Node())) {
                List<Node> pathToCurrentNode = dijkstra.getShortestPathTo(nodeTo);
                if(!pathToCurrentNode.isEmpty()) {

                    double datasetDistanceToInMetres = order.getDistanceTo() * 1000;//in metres
                    double epsilon = nodeTo.getMinDistance() - datasetDistanceToInMetres;//difference between my result and dataset value

                    if( nodeTo.getMinDistance() > 1000000)
                    {
                        NodesToFileWriter.writeResultInFile(
                                noShortPathFileName, nodeTo,
                                pathToCurrentNode, datasetDistanceToInMetres,
                                nodeTo.getMinDistance(), epsilon);
                    } else{
                        nodeTo.setEpsilon(epsilon);
                        shortPathes.put(nodeTo, pathToCurrentNode);

                        NodesToFileWriter.writeResultInFile(
                                fileName, nodeTo,
                                pathToCurrentNode, datasetDistanceToInMetres,
                                nodeTo.getMinDistance(), epsilon);
                    }
                } else {
                    System.out.println(nodeTo + "Short path doesn't exist");
                }
            } else {
                nodeTo.setLongtitude(order.getLongtitude());
                nodeTo.setLatitude(order.getLatitude());
                NodesToFileWriter.writeResultInFile(
                        nodeNotExistFileName, nodeTo,
                        new ArrayList<>(), Double.NaN,
                        nodeTo.getMinDistance(), Double.NaN);
            }
        });
        return shortPathes;
    }

    public List<Order> getOrders() { return orders; }
}
