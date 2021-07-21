package TTL;

import TTL.models.Edge;
import TTL.models.Node;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

public class Dijkstra {

    private HashMap<Long,Node> nodesHashMap;

    public Dijkstra(){}

    public Dijkstra(HashMap<Long, Node> nodesHashMap) {
        this.nodesHashMap = nodesHashMap;
    }

    public void setNodesHashMap(HashMap<Long, Node> nodesHashMap) {
        this.nodesHashMap = nodesHashMap;
    }
    public HashMap<Long,Node> getNodesHashMap() {
        return nodesHashMap;
    }

    // linear algorithm
    public void computePathesFrom(Node nodeFrom)
    {
        if(nodesHashMap == null)
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
                        Node nodeTo = nodesHashMap.get(edge.getTo());
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

    public void printNodes(){

        if(nodesHashMap == null)
        {
            System.out.println("nodesHashMap is empty");
            return;
        }
        nodesHashMap
                .entrySet()
                .stream()
                .limit(2)
                .forEach(System.out::println);
    }

    public List<Node> getShortestPathTo(Node nodeTo)
    {
        if(nodesHashMap == null)
        {
            System.out.println("nodesHashMap is empty");
            return new ArrayList<>();
        }
        List<Node> path = new ArrayList<>();
        for(Node node = nodeTo;node != null; node = node.getPreviousNode()){
            path.add(node);
        }
        Collections.reverse(path);
        return path;
    }

    public void clearGraph()
    {
        nodesHashMap.forEach((hash,node) -> clearNode(node));
    }

    public Node clearNode(Node node)
    {
        node.setMinDistance(Double.MAX_VALUE);
        node.setEdges(new ArrayList<>());
        node.setPreviousNode(null);
        return node;
    }
}
