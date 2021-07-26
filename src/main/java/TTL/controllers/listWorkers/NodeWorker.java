package TTL.controllers.listWorkers;

import TTL.models.Node;

import java.util.HashMap;
import java.util.List;

public class NodeWorker implements Worker {
    private List<Node> nodes;

    /**
     * Empty constructor
     */
    public NodeWorker (){}

    /**
     * Constructor. Initializes nodes
     * @param nodes - List of Nodes
     */
    public NodeWorker (List<Node> nodes){
        this.nodes = nodes;
    }

    /**
     * Sets the value of nodes
     * @param nodes - List of nodes
     */
    public void setNodes(List<Node> nodes){
        this.nodes = nodes;
    }

    /**
     * Returns the nodeId of node with latitude = lat and longtitude = lon
     * @param lat - node latitude
     * @param lon - node longtitude
     * @return node id
     */
    public long getNodeId(double lat, double lon)
    {
        return nodes
                .stream()
                .filter(node -> lat == node.getLatitude() && lon == node.getLongtitude())
                .map(Node::getId)
                .findFirst()
                .orElse(0L);
    }

    /**
     * Returns the Node object with latitude = lat and longtitude = lon
     * @param lat - node latitude
     * @param lon - node longtitude
     * @return Node
     */
    public Node getNodeByCoordinates(double lat, double lon)
    {
        return nodes
                .stream()
                .filter(node -> lat == node.getLatitude() && lon == node.getLongtitude())
                .findFirst()
                .orElse(new Node());
    }

    /**
     * Checks nodes with nodeStartId and nodeEndId exists in list of nodes
     * @param nodeStartId - id of start node in edge
     * @param nodeEndId - id of end node in edge
     * @return true if both nodes are exist else false
     */
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

    /**
     * Converts list of nodes into HashMap
     * @return HashMap ( Node ID, Node)
     */
    @Override
    public HashMap<Long, Node> toHashMap() {
        HashMap<Long,Node> nodesHashMap = new HashMap<>();
        nodes.forEach(node -> nodesHashMap.put(node.getId(),node));
        return nodesHashMap;
    }
}
