package TTL.controllers;

import TTL.models.Branch;
import TTL.models.Node;
import TTL.models.Order;

import java.util.ArrayList;
import java.util.List;

public class Getters {

    //TODO: Start to use Stream API instead of it

    public static long getNodeId(double lat, double lon,List<Node> nodes)
    {
        /*return nodes
                .stream()
                .filter(node -> lat == node.getLatitude() && lon == node.getLongtitude())
                .collect(Collectors.toList())
                .get(0);*/
        for (Node node : nodes)
        {
            if (lat == node.getLatitude() && lon == node.getLongtitude())
            {
                return node.getId();
            }
        }
        return 0;
    }

    public static Node getNodeByCoordinates(double lat, double lon,List<Node> nodes)
    {
        /*return nodes
                .stream()
                .filter(node -> lat == node.getLatitude() && lon == node.getLongtitude())
                .collect(Collectors.toList())
                .get(0);*/
        for (Node node : nodes)
        {
            if (lat == node.getLatitude() && lon == node.getLongtitude())
            {
                return node;
            }
        }
        return new Node();
    }

    public static ArrayList<String> getBranchCodes(List<Branch> branches)
    {
        ArrayList<String> branchCodes = new ArrayList<String>();
        for (Branch branch: branches)
        {
            String branchCode = branch.getBranchCode();
            if (! branchCodes.contains(branchCode))
            {
                branchCodes.add(branchCode);
            }
        }
        return branchCodes;
    }


    public static ArrayList<String> getOrderTypes(List<Order> orders)
    {
        ArrayList<String> orderTypes = new ArrayList<String>();
        for (Order order: orders)
        {
            String orderType = order.getOrderType();
            if (! orderTypes.contains(orderType))
            {
                orderTypes.add(orderType);
            }
        }
        return orderTypes;
    }

}
