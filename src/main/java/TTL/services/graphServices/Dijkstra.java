package TTL.services.graphServices;

import TTL.exception_handlers.NoShortPathException;
import TTL.models.Edge;
import TTL.models.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Dijkstra algorithm realization
 * graph - variable, which contains graph structure
 * methods : computeMinDistanceFrom , getShortestpathTo
 */
@NoArgsConstructor
@AllArgsConstructor
public class Dijkstra {

    @Getter @Setter
    private HashMap<Long,Node> graph;

    /**
     * compute minDistances and set previous Node,
     * from which the distance is the shortest, for each Node in Graph.
     * @param nodeFrom - Node from which min distances will be computed
     */
    public void computeMinDistancesfrom(Node nodeFrom)
    {
        try{
        nodeFrom.setMinDistance(0);
        PriorityBlockingQueue<Node> priorityQueue = new PriorityBlockingQueue<>();
        priorityQueue.add(nodeFrom);

            while (!priorityQueue.isEmpty()) {
                Node curNode = priorityQueue.poll();
                List<Edge> curNodeEdges = curNode.getEdges();
                if(curNodeEdges != null) {
                    curNodeEdges.forEach(edge -> {
                        Node nodeTo = graph.get(edge.getTo());
                        double minDistance = curNode.getMinDistance() + edge.getDistance();
                        if (minDistance < nodeTo.getMinDistance()) {
                            priorityQueue.remove(curNode);
                            nodeTo.setPreviousNode(curNode);
                            nodeTo.setMinDistance(minDistance);
                            priorityQueue.add(nodeTo);
                        }
                    });
                }
            }
        }
        catch(RuntimeException ex)
        {
            System.out.println("Error: "+ex.getMessage());
        }
    }

    /**
     * Returns shortest path to Node nodeTo from node,
     * for which computeMinDistancesFrom was launched
     * @param nodeTo - The final point to compute short path for
     * @return list of nodes, which contains the shortest path to nodeTo
     */
    public List<Node> getShortestPathTo(Node nodeTo) throws NoShortPathException
    {
        List<Node> path = new ArrayList<>();
        for(Node node = nodeTo;node != null; node = node.getPreviousNode()){
            path.add(node);
        }
        if(path.size() == 1)
        {
            String errMessage = "No short path for order with coordinates [" + nodeTo.getLatitude() + "," + nodeTo.getLongtitude() + "]";
            throw new NoShortPathException(errMessage,nodeTo);
        }
        Collections.reverse(path);
        return path;
    }
}
