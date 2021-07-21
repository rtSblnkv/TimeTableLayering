package TTL;

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
    public GraphCreator(){}

    public GraphCreator(List<Node> nodes, List<Edge> edges) {
        this.nodes = new ArrayList<>(nodes);
        this.edges = new ArrayList<>(edges);
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

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
