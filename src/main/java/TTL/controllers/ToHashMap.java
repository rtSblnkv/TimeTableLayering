package TTL.controllers;

import TTL.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ToHashMap {

    //TODO: Start to use Stream API instead of it

    public static HashMap<Long, Node> nodesListToHashMap(List<Node> nodes)
    {
        HashMap<Long,Node> nodesHashMap = new HashMap<>();
        nodes.forEach(node -> nodesHashMap.put(node.getId(),node));
        return nodesHashMap;
    }

    public static HashMap<String, Order> ordersListToHashMap(List<Order> orders)
    {
        HashMap<String,Order> ordersHashMap = new HashMap<>();
        orders.forEach(order -> ordersHashMap.put(order.getOrderId(),order));
        return ordersHashMap;
    }

    public static HashMap<String, Branch> branchesListToHashMap(List<Branch> branches)
    {
        HashMap<String,Branch> branchesHashMap = new HashMap<>();
        branches.forEach(branch -> branchesHashMap.put(branch.getBranchCode(),branch));
        return branchesHashMap;
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
        return edgesHashMap;
    }

    public static HashMap<String,List<Order>> ordersListToHashMapByOrderType(List<Order> orders)
    {
        List<String> orderTypes = Getters.getOrderTypes(orders);
        HashMap<String,List<Order>> ordersOnOrderTypeHashMap = new HashMap<>();
        orderTypes.forEach(orderType -> ordersOnOrderTypeHashMap.put(orderType,new ArrayList<Order>()));
        orders.forEach(order -> ordersOnOrderTypeHashMap.get(order.getOrderType()).add(order));
        return ordersOnOrderTypeHashMap;
    }

    public static HashMap<String,List<Order>> ordersListToHashMapByBranch(List<Order> orders)
    {
        List<String> branchCodes = Getters.getBranchCodes(orders);
        HashMap<String,List<Order>> ordersOnBranchCodeHashMap = new HashMap<>();
        branchCodes.forEach(branchCode -> ordersOnBranchCodeHashMap.put(branchCode,new ArrayList<Order>()));
        orders.forEach(order -> ordersOnBranchCodeHashMap.get(order.getBranchCode().toUpperCase()).add(order));
        return ordersOnBranchCodeHashMap;
    }
}
