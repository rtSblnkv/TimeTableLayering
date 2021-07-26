package TTL.services.graphServices;

import TTL.controllers.listWorkers.EdgeWorker;
import TTL.controllers.listWorkers.NodeWorker;
import TTL.models.Edge;
import TTL.models.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphCreator {


    private List<Node> nodes;
    private List<Edge> edges;
    //меньше 1 секунды

    /**
     * Empty constructor
     */
    public GraphCreator(){}

    /**
     * Constructor. Initialize nodes and edges
     * @param nodes - list of Nodes
     * @param edges - list of Edges
     */
    public GraphCreator(List<Node> nodes, List<Edge> edges) {
        this.nodes = new ArrayList<>(nodes);
        this.edges = new ArrayList<>(edges);
    }

    /**
     * set the value of nodes
     * @param nodes - list of Nodes
     */
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * set the value of edges
     * @param edges - list of edges
     */
    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    /**
     * create graph structure from Nodes and edges lists by adding list of edges for each node
     * @return Map(node id , node)
     */
    public  HashMap<Long, Node> createGraph()
    {
        EdgeWorker edgeWorker = new EdgeWorker(edges);

        HashMap<Long,List<Edge>> edgeHashMap = edgeWorker.toHashMap();
        NodeWorker nodeWorker = new NodeWorker(nodes);
        HashMap<Long, Node> graph = nodeWorker.toHashMap();
        graph.forEach((id,node) -> node.setEdges(edgeHashMap.get(node.getId())
        ));
        return graph;
    }


}
