package TTL.services.graphServices;

import TTL.exception_handlers.InvalidNodeException;
import TTL.exception_handlers.NoShortPathException;
import TTL.exception_handlers.WriteResultException;
import TTL.models.Branch;
import TTL.models.Edge;
import TTL.models.Node;
import TTL.models.Order;
import TTL.services.NodesToFileWriter;
import TTL.services.listWorkers.BranchWorker;
import TTL.services.listWorkers.NodeWorker;
import lombok.NoArgsConstructor;
import org.openjdk.jmh.annotations.*;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Computes short pathes for list of orders in 2 variations
 * 1. Making recalculating of dijkstra algorithm for each
 * order
 * 2. Calculating dijkstra for branch codes location
 * for splitting by bracnh codes order sublists
 */
@BenchmarkMode(Mode.All)
@Warmup(iterations = 2)
@State(Scope.Benchmark)
@NoArgsConstructor
public class DijkstraRunner {

    private List<Node> nodes;
    private List<Edge> edges;
    private List<Branch> branches;

    private static HashMap<String, Node> branchNodes;

    public DijkstraRunner(List<Node> nodes, List<Edge> edges, List<Branch> branches) {
        this.nodes = nodes;
        this.edges = edges;
        this.branches = branches;
    }

    /**
     * computes pathes for list of orders with claculating of dijkstra for each order
     *
     * @param orders        - order list
     * @param splitter      - splitter (if exists) for which whole order list are divided into order sublists (used for creating file name)
     * @param algorithmType - type of started algorithm (used for creating file name)
     * @return map of node and list of nodes as the shortest path to it from departure node
     */
    public HashMap<Node, List<Node>> computePathes(List<Order> orders, String splitter, String algorithmType) {

        HashMap<Node, List<Node>> shortPathes = new HashMap<>();
        String fileName = "results\\" + splitter + "_" + algorithmType + ".txt";
        String noShortPathFileName = "results\\" + splitter + "_no_short_path.txt";
        String nodeNotExistFileName = "results\\" + splitter + "_strange.txt";

        try {
            orders.forEach(order -> {

                try {
                    NodesToFileWriter.createFile(fileName);
                    NodesToFileWriter.createFile(noShortPathFileName);
                    NodesToFileWriter.createFile(nodeNotExistFileName);
                } catch (FileAlreadyExistsException ex) {
                    System.out.println(ex.getMessage());
                }

                List<Node> nodesClone = new ArrayList<>();
                for (Node node : nodes) {
                    nodesClone.add(node.clone());
                }

                NodeWorker nodeWorker = new NodeWorker(nodesClone);
                BranchWorker branchWorker = new BranchWorker(branches);

                branchNodes = branchWorker.toBranchNodeHashMap(nodeWorker);
                Node startNode = branchNodes.get(order.getBranchCode().toUpperCase());

                GraphCreator graphBuilder = new GraphCreator(nodesClone, edges);
                HashMap<Long, Node> graph = graphBuilder.createGraph();

                Dijkstra dijkstra = new Dijkstra();
                dijkstra.setGraph(graph);
                dijkstra.computeMinDistancesfrom(startNode);

                double datasetDistanceToInMetres = order.getDistanceTo() * 1000;

                try {
                    Node nodeTo = nodeWorker.getNodeByCoordinates(order.getLatitude(), order.getLongtitude());

                    List<Node> pathToCurrentNode = dijkstra.getShortestPathTo(nodeTo);
                    double epsilon = nodeTo.getMinDistance() - datasetDistanceToInMetres;
                    nodeTo.setEpsilon(epsilon);

                    shortPathes.put(nodeTo, pathToCurrentNode);
                    NodesToFileWriter.writeResultInFile(fileName, nodeTo,
                            pathToCurrentNode, datasetDistanceToInMetres,
                            nodeTo.getMinDistance(), epsilon);
                } catch (InvalidNodeException ex) {
                    NodesToFileWriter.writeErrResultInFile(nodeNotExistFileName, ex.getLat(), ex.getLon());
                } catch (NoShortPathException ex) {
                    double errNodeLat = ex.getUnattainableNode().getLatitude();
                    double errNodeLon = ex.getUnattainableNode().getLongtitude();
                    NodesToFileWriter.writeErrResultInFile(noShortPathFileName, errNodeLat, errNodeLon);
                }
            });
        } catch (WriteResultException ex) {
            ex.printStackTrace();
        }
        return shortPathes;
    }


    public HashMap<Node, List<Node>> computePathesForLayer(String branch, List<Order> orders, String type) {
        HashMap<Node, List<Node>> shortPathes = new HashMap<>();

        String fileName = "results\\" + branch + "_" + type + ".txt";
        String noShortPathFileName = "results\\" + branch + "_" + type + "_no_short_path.txt";
        String nodeNotExistFileName = "results\\" + branch + "_" + type + "_strange.txt";

        try {
            NodesToFileWriter.createFile(fileName);
            NodesToFileWriter.createFile(noShortPathFileName);
            NodesToFileWriter.createFile(nodeNotExistFileName);
        } catch (FileAlreadyExistsException ex) {
            System.out.println(ex.getMessage());
        }

        List<Node> nodesClone = new ArrayList<>();
        for (Node node : nodes) {
            nodesClone.add(node.clone());
        }

        NodeWorker nodeWorker = new NodeWorker(nodesClone);
        BranchWorker branchWorker = new BranchWorker(branches);

        branchNodes = branchWorker.toBranchNodeHashMap(nodeWorker);
        Node branchNode = branchNodes.get(branch);

        GraphCreator graphBuilder = new GraphCreator(nodesClone, edges);
        HashMap<Long, Node> graph = graphBuilder.createGraph();

        Dijkstra dijkstra = new Dijkstra();
        dijkstra.setGraph(graph);
        dijkstra.computeMinDistancesfrom(branchNode);

        try {
            orders.forEach(order -> {
                double datasetDistanceToInMetres = order.getDistanceTo() * 1000;//in metres
                try {
                    Node nodeTo = nodeWorker.getNodeByCoordinates(order.getLatitude(), order.getLongtitude());
                    List<Node> pathToCurrentNode = dijkstra.getShortestPathTo(nodeTo);
                    double epsilon = nodeTo.getMinDistance() - datasetDistanceToInMetres;//difference between my result and dataset value
                    nodeTo.setEpsilon(epsilon);
                    shortPathes.put(nodeTo, pathToCurrentNode);

                    NodesToFileWriter.writeResultInFile(fileName, nodeTo,
                            pathToCurrentNode, datasetDistanceToInMetres,
                            nodeTo.getMinDistance(), epsilon);
                } catch (InvalidNodeException ex) {
                    NodesToFileWriter.writeErrResultInFile(nodeNotExistFileName, ex.getLat(), ex.getLon());
                } catch (NoShortPathException ex) {
                    double errNodeLat = ex.getUnattainableNode().getLatitude();
                    double errNodeLon = ex.getUnattainableNode().getLongtitude();
                    NodesToFileWriter.writeErrResultInFile(noShortPathFileName, errNodeLat, errNodeLon);
                }
            });
        } catch (WriteResultException ex) {
            ex.printStackTrace();
        }
        return shortPathes;
    }

}
