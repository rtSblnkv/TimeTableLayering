package TTL.controllers;

import TTL.models.Branch;
import TTL.models.Edge;
import TTL.models.Node;
import TTL.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Getters {

    //TODO: Start to use Stream API instead of it

    public static long getNodeId(double lat, double lon,List<Node> nodes)
    {
        for (Node node : nodes)
        {
            if (lat == node.getLatitude() && lon == node.getLongtitude())
            {
                return node.getId();
            }
        }
        return 0;
    }


    // Method get ID of nodes if customer location exist in Nodes else 0
    public static long[] getCustomerNodesId(List<Node> nodes,List<Order> orders)
    {
        long[] customerNodesId = new long[orders.size()];
        int i = 0;
        for (Order order : orders)
        {
            customerNodesId[i] = getNodeId(order.getLatitude(),order.getLongtitude(),nodes);
        }
        return customerNodesId;
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

    public static Long getBranchNodeIdByBranchCode(String branchCode,List<Branch>branches)
    {
        //return branches
        // .stream()
        // .filter(branch -> branch.getBranchCode() == branchCode.toUpperCase())
        // .collect(Collectors.toList())
        // .get(0)
        // .getNodeId();
        for(Branch branch : branches)
        {
            if(branch.getBranchCode().equals(branchCode.toUpperCase()))
            {
                return branch.getNodeId();
            }
        }
        return 0L;
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
