package TTL;

import TTL.controllers.ToHashMap;
import TTL.models.Edge;
import TTL.models.Node;

import java.util.HashMap;
import java.util.List;

public class GraphCreator {
    //меньше 1 секунды
    static HashMap<Long, Node> createGraph(List<Node> nodes, List<Edge> edges)
    {
        HashMap<Long, Node> nodesHashMap;

        edges.forEach(edge -> edge.setRangeTime());

        HashMap<Long,List<Edge>>  edgeHashMap = ToHashMap.edgesListToHashMapOnFromNodeId(edges);
        nodes.forEach(node -> node.setEdges(edgeHashMap.get(node.getId())));

        nodesHashMap = ToHashMap.nodesListToHashMap(nodes);
        return nodesHashMap;
    }


}
