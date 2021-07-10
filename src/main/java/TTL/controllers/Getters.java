package TTL.controllers;

import TTL.models.Branch;
import TTL.models.Node;
import TTL.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Getters {

    //TODO: Start to use Stream API instead of it

    public static long getNodeId(double lat, double lon,List<Node> nodes)
    {
        return nodes
                .stream()
                .filter(node -> lat == node.getLatitude() && lon == node.getLongtitude())
                .map(Node::getId)
                .findFirst()
                .orElse(0L);
        /*for (Node node : nodes)
        {
            if (lat == node.getLatitude() && lon == node.getLongtitude())
            {
                return node.getId();
            }
        }
        return 0;*/
    }

    public static Node getNodeByCoordinates(double lat, double lon,List<Node> nodes)
    {
        return nodes
                .stream()
                .filter(node -> lat == node.getLatitude() && lon == node.getLongtitude())
                .findFirst()
                .orElse(new Node());
        /*for (Node node : nodes)
        {
            if (lat == node.getLatitude() && lon == node.getLongtitude())
            {
                return node;
            }
        }
        return new Node();*/
    }

    public static List<String> getBranchCodes(List<Branch> branches)
    {
        return new ArrayList<>(
                branches
                .stream()
                .map(Branch::getBranchCode)
                .collect(Collectors.toSet())
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


    public static List<String> getOrderTypes(List<Order> orders)
    {
        return new ArrayList<>(
                orders
                .stream()
                .map(Order::getOrderType)
                .collect(Collectors.toSet())

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

}
