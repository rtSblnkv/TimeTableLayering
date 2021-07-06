package TTL;

import TTL.models.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

public class Dijkstra {

    private HashMap<Long,Node> nodesHashMap;

    public void setNodesHashMap(HashMap<Long, Node> nodesHashMap) {
        this.nodesHashMap = nodesHashMap;
    }

    // linear algorithm
    public void computePathesFrom(Node nodeFrom)
    {
        nodeFrom.setMinDistance(0);
        PriorityBlockingQueue<Node> priorityQueue = new PriorityBlockingQueue<>();
        priorityQueue.add(nodeFrom);

        while(!priorityQueue.isEmpty())
        {
            Node curNode = priorityQueue.poll();

            curNode.getEdges().forEach(edge ->{
                Node nodeTo = nodesHashMap.get(edge.getTo());
                double minDistance = nodeTo.getMinDistance() + edge.getDistance();

                if(minDistance < curNode.getMinDistance())
                {
                    priorityQueue.remove(curNode);
                    nodeTo.setPreviousNode(curNode);
                    nodeTo.setMinDistance(minDistance);
                    priorityQueue.add(nodeTo);
                }
            });
        }
    }

    public void computePathesFromByTime(Node nodeFrom)
    {
        nodeFrom.setMinDistance(0);
        PriorityBlockingQueue<Node> priorityQueue = new PriorityBlockingQueue<>();
        priorityQueue.add(nodeFrom);

        while(!priorityQueue.isEmpty())
        {
            Node curNode = priorityQueue.poll();

            curNode.getEdges().forEach(edge ->{
                Node nodeTo = nodesHashMap.get(edge.getTo());
                double minDistance = nodeTo.getMinDistance() + edge.getRangeTime();

                if(minDistance < curNode.getMinDistance())
                {
                    priorityQueue.remove(curNode);
                    nodeTo.setPreviousNode(curNode);
                    nodeTo.setMinDistance(minDistance);
                    priorityQueue.add(nodeTo);
                }
            });
        }
    }

    public List<Node> getShortestPathTo(Node nodeTo)
    {
        List<Node> path = new ArrayList<>();
        for(Node node = nodeTo;node != null; node = node.getPreviousNode()){
            path.add(node);
        }
        Collections.reverse(path);
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
