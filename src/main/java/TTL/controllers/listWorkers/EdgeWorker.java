package TTL.controllers.Lists;

import TTL.models.Edge;
import TTL.models.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EdgeWorker implements Worker{
    private List<Edge> edges;

    public EdgeWorker() { }

    public EdgeWorker(List<Edge> edges) { this.edges = edges; }

    public void setEdges(List<Edge> edges) { this.edges = edges; }

    public double[] getOrdersDistances()
    {
        double[] distances = new double[edges.size()];
        int i = 0;
        for(Edge edge:edges)
        {
            distances[i] = edge.getDistance();
            i++;
        }
        return distances;
    }

    public Boolean checkEdgeNodesExist(NodeWorker nw)
    {
        return edges
                .stream()
                .anyMatch( edge -> nw.checkNodesInEdgeExist(edge.getFrom(),edge.getTo()));
        /*for(Edge edge :edges)
        {
            if(!)
            {
                return false;
            }
        }
        return true;*/
    }

    @Override
    public HashMap<Long,List<Edge>> toHashMap() {
        HashMap<Long,List<Edge>> edgesHashMap = new HashMap<>();
        edges.forEach(edge ->{
            if(!edgesHashMap.containsKey(edge.getFrom()))
            {
                edgesHashMap.put(edge.getFrom(), new ArrayList<>());
            }
            edgesHashMap.get(edge.getFrom()).add(edge);
        });
        return edgesHashMap;
    }
}
