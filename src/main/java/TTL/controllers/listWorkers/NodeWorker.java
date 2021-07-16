package TTL.controllers.Lists;

import TTL.models.Node;
import java.util.HashMap;
import java.util.List;

public class NodeWorker implements Worker {
    private List<Node> nodes;

    public NodeWorker (){}

    public NodeWorker (List<Node> nodes){
        this.nodes = nodes;
    }

    public void setNodes(List<Node> nodes){
        this.nodes = nodes;
    }

    public long getNodeId(double lat, double lon)
    {
        return nodes
                .stream()
                .filter(node -> lat == node.getLatitude() && lon == node.getLongtitude())
                .map(Node::getId)
                .findFirst()
                .orElse(0L);
        /*for (Node node : nodes)
        {
            if (lat == node.getLatitude() && lon == node.getLongtitude())
            {
                return node.getId();
            }
        }
        return 0;*/
    }

    public Node getNodeByCoordinates(double lat, double lon)
    {
        return nodes
                .stream()
                .filter(node -> lat == node.getLatitude() && lon == node.getLongtitude())
                .findFirst()
                .orElse(new Node());
        /*for (Node node : nodes)
        {
            if (lat == node.getLatitude() && lon == node.getLongtitude())
            {
                return node;
            }
        }
        return new Node();*/
    }

    Boolean checkNodesInEdgeExist(long nodeStartId, long nodeEndId)
    {
        Boolean firstExist = false;
        Boolean secondExist = false;
        if (nodes != null && !nodes.isEmpty())
        {
            for(Node node : nodes)
            {
                if(node.getId() == nodeStartId)
                {
                    firstExist = true;
                }
                if(node.getId() == nodeEndId)
                {
                    secondExist = true;
                }
            }
        }
        return firstExist && secondExist;
    }


    @Override
    public HashMap<Long, Node> toHashMap() {
        HashMap<Long,Node> nodesHashMap = new HashMap<>();
        nodes.forEach(node -> nodesHashMap.put(node.getId(),node));
        return nodesHashMap;
    }
}
