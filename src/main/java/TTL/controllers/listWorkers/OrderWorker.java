package TTL.controllers.Lists;

import TTL.controllers.Getters;
import TTL.models.Node;
import TTL.models.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class OrderWorker implements Worker {

    private List<Order> orders;

    public OrderWorker(){}

    public OrderWorker(List<Order> orders){
        this.orders = orders;
    }

    public void setOrders(List<Order> orders){
        this.orders = orders;
    }

    public List<String> getBranchCodes()
    {
        return orders
                .stream()
                .distinct()
                .map(Order::getBranchCode)
                .collect(Collectors.toList()
                );
        /*List<String> branchCodes = new ArrayList<String>();
        for (Branch branch: branches)
        {
            String branchCode = branch.getBranchCode();
            if (! branchCodes.contains(branchCode))
            {
                branchCodes.add(branchCode);
            }
        }
        return branchCodes;*/
    }


    public List<String> getOrderTypes()
    {
        return orders
                        .stream()
                        .distinct()
                        .map(Order::getOrderType)
                        .collect(Collectors.toList()
                        );
        /*ArrayList<String> orderTypes = new ArrayList<String>();
        for (Order order: orders)
        {
            String orderType = order.getOrderType();
            if (! orderTypes.contains(orderType))
            {
                orderTypes.add(orderType);
            }
        }
        return orderTypes;*/
    }

    private static List<Node> getListOfOrderNodes(List<Order> ordersGrouped)
    {
        List<Node> orderNodes = new ArrayList<>();
        for (Order order: ordersSorted)
        {
            Node curNode = ;
            if(curNode.getLatitude()!= 0)
            {
                orderNodes.add(curNode);
            }
        }
        return orderNodes;*//*
        return ordersGrouped
                .stream()
                .map(order -> Getters.getNodeByCoordinates(order.getLatitude(),order.getLongtitude(),nodes))
                .filter(node -> node.getLatitude() != 0)
                .collect(Collectors.toList());
    }

    public void deleteOrdersWithIncorrectNodes(NodeWorker nw )
    {
        orders.removeIf(order -> nw.getNodeId(order.getLatitude(),order.getLongtitude()) == 0);
    }

    public HashMap<String,List<Order>> ordersListToHashMapByOrderType()
    {
        List<String> orderTypes = getOrderTypes();
        HashMap<String,List<Order>> ordersOnOrderTypeHashMap = new HashMap<>();
        orderTypes.forEach(orderType -> ordersOnOrderTypeHashMap.put(orderType,new ArrayList<Order>()));
        orders.forEach(order -> ordersOnOrderTypeHashMap.get(order.getOrderType()).add(order));
        return ordersOnOrderTypeHashMap;
    }

    public HashMap<String,List<Order>> ordersListToHashMapByBranch()
    {
        List<String> branchCodes = getBranchCodes();
        HashMap<String,List<Order>> ordersOnBranchCodeHashMap = new HashMap<>();
        branchCodes.forEach(branchCode -> ordersOnBranchCodeHashMap.put(branchCode,new ArrayList<Order>()));
        orders.forEach(order -> ordersOnBranchCodeHashMap.get(order.getBranchCode()).add(order));
        return ordersOnBranchCodeHashMap;
    }


    @Override
    public HashMap<String, Order> toHashMap() {
        HashMap<String,Order> ordersHashMap = new HashMap<>();
        orders.forEach(order -> ordersHashMap.put(order.getOrderId(),order));
        return ordersHashMap;
    }
}
