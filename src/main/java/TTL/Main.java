package TTL;

import TTL.controllers.*;
import TTL.models.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;

public class Main {
    private static List<Order> orders;
    private static List<Branch> branches;
    private static List<Node> nodes;
    private static List<Edge> edges;

    //DONE:Посчитать корреляцию order_type и BranchCode : 0.14823003 - параметры независимы

    public static void main(String[]args)
    {
        printTime();
        System.out.println("nodes");
        //orders = DataLoader.ordersToList();
        nodes = DataLoader.nodesToList();
        printTime();
        //branches = DataLoader.branchesToList();
        System.out.println("edges");
        edges = DataLoader.edgesToList();
        printTime();
        System.out.println("createGraph");
        GraphCreator.createGraph(nodes,edges);
        System.out.println("createGraph done");
        printTime();
        System.out.println("HashMap");
        GraphCreator.createGraphHash(nodes,edges);
        System.out.println("HashMap done");
        printTime();

        //checkData();
    }

    static void printTime()
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        System.out.println(formatter.format(date));
    }

    static void checkData()
    {
        // Class which checkes input data
        DataInspector di = new DataInspector();

        di.setNodes(nodes);
        di.setBranches(branches);
        di.setEdges(edges);
        di.setOrders(orders);

        di.deleteOrdersWithIncorrectNodes();
        Getters.getCustomerNodesId(nodes,orders);
        System.out.println("Edges");
        di.checkEdgeNodesExist();
        System.out.println("Branches");
        di.checkBranchNodes();
        System.out.println(Correlation.getCorrelationOfOrderTypesAndBranchCodes(orders,branches));
    }







}
