package TTL;

import TTL.controllers.ToHashMap;
import TTL.models.Edge;
import TTL.models.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class GraphCreator {

    private static List<Edge> getEdgesByNodeId(List<Edge> edges, long nodeId)
    {
        List<Edge> curNodeEdges = new ArrayList<Edge>();
        for (Edge edge : edges)
        {
            if(edge.getFrom() == nodeId)
            {
                curNodeEdges.add(edge);
            }
        }
        return curNodeEdges;
    }

    //8 секунд
    static List<Node> createGraph(List<Node> nodes, List<Edge> edges)
    {
        nodes.forEach(node -> node.setEdges(getEdgesByNodeId(edges, node.getId())));
        return nodes;
    }

    //меньше 1 секунды
    static List<Node> createGraph(List<Node> nodes, List<Edge> edges)
    {
        HashMap<Long,List<Edge>>  edgeHashMap = ToHashMap.edgesListToHashMapOnFromNodeId(edges);
        nodes.forEach(node -> node.setEdges(edgeHashMap.get(node.getId())));
        return nodes;
    }


}
