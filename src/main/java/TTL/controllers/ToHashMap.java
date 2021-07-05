package TTL.controllers;

import TTL.models.Edge;
import TTL.models.Node;
import TTL.models.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ToHashMap {

    public static HashMap<Long, Node> nodesListToHashMap(List<Node> nodes)
    {
        HashMap<Long,Node> nodesHashMap = new HashMap<>();
        nodes.forEach(node -> nodesHashMap.put(node.getId(),node));
        return nodesHashMap;
    }

    public static HashMap<Long,List<Edge>> edgesListToHashMapOnFromNodeId(List<Edge> edges)
    {
        HashMap<Long,List<Edge>> edgesHashMap = new HashMap<>();
        edges.forEach(edge ->{
            if(!edgesHashMap.containsKey(edge.getFrom()))
            {
                edgesHashMap.put(edge.getFrom(), new ArrayList<>());
            }

            edgesHashMap.get(edge.getFrom()).add(edge);
        });
        //System.out.println(edgesHashMap);
        return edgesHashMap;
    }

    public static HashMap<String, Order> ordersListToHashMap(List<Order> orders)
    {
        HashMap<String,Order> ordersHashMap = new HashMap<>();
        orders.forEach(order -> ordersHashMap.put(order.getOrderId(),order));
        return ordersHashMap;
    }
}
