package TTL;

import TTL.controllers.dataloader.DataLoader;
import TTL.controllers.ToHashMap;
import TTL.models.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    private static List<Order> orders;
    private static List<Branch> branches;
    private static List<Node> nodes;
    private static List<Edge> edges;

    //DONE:Посчитать корреляцию order_type и BranchCode : 0.14823003 - параметры независимы

    public static void main(String[]args)
    {
        DijkstraRunner dr = new DijkstraRunner();
        dr.getShortestForAllOrdersLinear();
    }

    private static void test()
    {
        printTime();
        System.out.println("orders");
        orders = DataLoader.ordersToList();
        printTime();
        System.out.println("nodes");
        nodes = DataLoader.nodesToList();
        printTime();
        System.out.println("branches");
        branches = DataLoader.branchesToList();
        printTime();
        /*System.out.println("createGraph");
        GraphCreator.createGraph(nodes,edges);
        System.out.println("createGraph done");
        printTime();*/
        System.out.println("HashMap");
        GraphCreator.createGraph(nodes,edges);
        System.out.println("HashMap done");
        printTime();
        System.out.println("Split on order type");
        System.out.println(ToHashMap.ordersListToHashMapByOrderType(orders));
        printTime();
        System.out.println("Split on branch code");
        System.out.println(ToHashMap.ordersListToHashMapByBranch(orders,branches));
        printTime();
    }

    private static void printTime()
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        System.out.println(formatter.format(date));
    }








}
