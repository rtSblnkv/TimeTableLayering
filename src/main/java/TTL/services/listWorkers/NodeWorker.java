package TTL.services.listWorkers;

import TTL.exception_handlers.InvalidNodeException;
import TTL.models.Node;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

/**
 * Class which operates with list of nodes
 */
@NoArgsConstructor
@AllArgsConstructor
public class NodeWorker implements Worker {

    @Setter
    private List<Node> nodes;

    /**
     * Returns the nodeId of node with latitude = lat and longtitude = lon
     * or else return 0L
     * @param lat - node latitude
     * @param lon - node longtitude
     * @return node id
     */
    public long getNodeId(double lat, double lon) throws InvalidNodeException
    {
        Long nodeId = nodes
                .stream()
                .filter(node -> lat == node.getLatitude() && lon == node.getLongtitude())
                .map(Node::getId)
                .findFirst()
                .orElse(0L);

        if (nodeId == 0L){
            String errMessage = "No Nodes with [" + lat + "," + lon +"] coordinates in the Dataset";
            throw new InvalidNodeException(lat,lon,errMessage);
        }
        return nodeId;
    }

    /**
     * Returns the Node object with latitude = lat and longtitude = lon
     * or Else returns empty Node
     * @param lat - node latitude
     * @param lon - node longtitude
     * @return Node
     */
    public Node getNodeByCoordinates(double lat, double lon) throws InvalidNodeException
    {
        Node node = nodes
                .stream()
                .filter(curNode -> lat == curNode.getLatitude() && lon == curNode.getLongtitude())
                .findFirst()
                .orElse(new Node());

        if (node.equals(new Node())){
            String errMessage = "No Nodes with [" + lat + "," + lon +"] coordinates in the Dataset";
            throw new InvalidNodeException(lat,lon,errMessage);
        }
        return node;
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
