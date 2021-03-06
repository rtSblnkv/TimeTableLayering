package TTL.services.graphServices;

import TTL.models.Edge;
import TTL.models.Node;
import TTL.services.listWorkers.EdgeWorker;
import TTL.services.listWorkers.NodeWorker;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *  Class which creates graph structure from
 *  list of nodes (nodes)
 *  list of edges (edges)
 **/
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class GraphCreator {

    private List<Node> nodes;
    private List<Edge> edges;

    /**
     * Creates graph structure from Nodes and Edges lists by adding list of adjacent edges for each node
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
