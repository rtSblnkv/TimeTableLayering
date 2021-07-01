package TTL;

import TTL.entity.*;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    //DONE:Посчитать корреляцию order_type и BranchCode : 0.14823003 - параметры независимы

    public static void main(String[]args)
    {
        List<Order> orders = DataLoader.ordersToList();
        List<Node> nodes = DataLoader.nodesToList();
        List<Branch> branches = DataLoader.branchesToList();
        List<Edge> edges = DataLoader.edgesToList();

        // Class which checkes input data
        DataInspector di = new DataInspector();

        di.setNodes(nodes);
        di.setBranches(branches);
        di.setEdges(edges);
        di.setOrders(orders);

        //deleteOrdersWithIncorrectNodes();
        //getCustomerNodes();
        //System.out.println("Edges");
        //checkEdgeNodesExist();
        System.out.println(Correlation.getCorrelationOfOrderTypesAndBranchCodes(orders,branches));
    }







}
