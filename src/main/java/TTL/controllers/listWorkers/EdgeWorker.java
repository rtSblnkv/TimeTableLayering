package TTL.controllers.listWorkers;

import TTL.models.Edge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EdgeWorker implements Worker{
    private List<Edge> edges;

    /**
     * Empty constructor
     */
    public EdgeWorker() { }

    /**
     * Constructor.Initialize edges
     * @param edges - list of edges
     */
    public EdgeWorker(List<Edge> edges) { this.edges = edges; }

    /**
     * Sets edges value
     * @param edges - list of edges
     */
    public void setEdges(List<Edge> edges) { this.edges = edges; }


    /**
     * for each edge check if start and finish Node exist in List of nodes.
     * @param nw - Nodeworker object.Handler of list of nodes
     * @return true if all edges are correct else false
     */
    public Boolean checkEdgeNodesExist(NodeWorker nw)
    {
        return edges
                .stream()
                .anyMatch( edge -> nw.checkNodesInEdgeExist(edge.getFrom(),edge.getTo()));
    }

    /**
     * Converts list of edges into HashMap
     * @return HashMap (fromNode ID, List of edges, for which fromNode is start node)
     */
    @Override
    public HashMap<Long,List<Edge>> toHashMap() {
        HashMap<Long,List<Edge>> edgesHashMap = new HashMap<>();
        for (Edge edge : edges) {
            edgesHashMap.computeIfAbsent(edge.getFrom(), k -> new ArrayList<>()).add(edge);
        }
        return edgesHashMap;
    }
}
