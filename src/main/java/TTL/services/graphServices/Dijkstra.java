package TTL.services.graphServices;

import TTL.models.Edge;
import TTL.models.Node;

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
public class Dijkstra {
    private HashMap<Long,Node> graph;

    public Dijkstra(){}

    public Dijkstra(HashMap<Long, Node> nodesHashMap) { this.graph = nodesHashMap; }

    /**
     * compute minDistances and set previous Node,
     * from which the distance is the shortest, for each Node in Graph.
     * @param nodeFrom - Node from which min distances will be computed
     */
    public void computeMinDistancesfrom(Node nodeFrom)
    {
        if(graph == null)
        {
            System.out.println("nodesHashMap is empty");
            return;
        }
        nodeFrom.setMinDistance(0);
        PriorityBlockingQueue<Node> priorityQueue = new PriorityBlockingQueue<>();
        priorityQueue.add(nodeFrom);
        try {

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
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Returns shortest path to Node nodeTo from node,
     * for which computeMinDistancesFrom was launched
     * @param nodeTo - The final point to compute short path for
     * @return list of nodes, which contains the shortest path to nodeTo
     */
    public List<Node> getShortestPathTo(Node nodeTo)
    {
        if(graph == null)
        {
            System.out.println("Graph is empty");
            return new ArrayList<>();
        }
        List<Node> path = new ArrayList<>();
        for(Node node = nodeTo;node != null; node = node.getPreviousNode()){
            path.add(node);
        }
        Collections.reverse(path);
        return path;
    }

    public void setGraph(HashMap<Long, Node> nodesHashMap) { this.graph = nodesHashMap; }

    public HashMap<Long,Node> getGraph() { return graph; }
}
