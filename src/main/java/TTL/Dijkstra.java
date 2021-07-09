package TTL;

import TTL.models.Edge;
import TTL.models.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

public class Dijkstra {

    private HashMap<Long,Node> nodesHashMap;

    public Dijkstra(){}

    public void setNodesHashMap(HashMap<Long, Node> nodesHashMap) {
        this.nodesHashMap = nodesHashMap;
    }

    // linear algorithm
    public void computePathesFrom(Node nodeFrom)
    {
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
        nodesHashMap.entrySet().stream().limit(10).forEach(System.out::println);
    }


    public void computePathesFromByTime(Node nodeFrom)
    {
        nodeFrom.setMinDistance(0);
        PriorityBlockingQueue<Node> priorityQueue = new PriorityBlockingQueue<>();
        priorityQueue.add(nodeFrom);

        while(!priorityQueue.isEmpty())
        {
            try {

                while (!priorityQueue.isEmpty()) {
                    Node curNode = priorityQueue.poll();
                    List<Edge> curNodeEdges = curNode.getEdges();
                    if(curNodeEdges != null)
                    {
                        curNodeEdges.forEach(edge -> {
                            Node nodeTo = nodesHashMap.get(edge.getTo());
                            double minDistance = curNode.getMinDistance() + edge.getRangeTime();

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
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public List<Node> getShortestPathTo(Node nodeTo)
    {
        List<Node> path = new ArrayList<>();
        for(Node node = nodeTo;node != null; node = node.getPreviousNode()){
            path.add(node);
        }
        //Collections.reverse(path);
        return path;
    }


    // parallel algorithm by distance and time
    public void computePathParallelByWeight(Node sourceNode)
    {
        sourceNode.setMinDistance(0);
    }

    // parallel algorithm
    public void computePathParallel(Node sourceNode)
    {
        sourceNode.setMinDistance(0);

    }
}
