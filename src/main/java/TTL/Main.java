package TTL;

import TTL.models.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    //DONE:Посчитать корреляцию order_type и BranchCode : 0.14823003 - параметры независимы

    public static final HashMap<String,String> csvPaths = new HashMap<String,String>(){{

        put("branches","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\branches.csv");
        put("edges","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\edges.csv");
        put("nodes","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\nodes.csv");
        put("orders","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\orders.csv");
    }};

    public static void main(String[]args) {
        printTime();
        System.out.println("Start");
        DijkstraRunner dr = new DijkstraRunner();
        printTime();
        System.out.println("Linear");
        HashMap<Node,List<Node>> pathes = dr.getShortestForAllOrdersLinear();
        printTime();
        pathes.forEach((node,path) -> System.out.println(node.getId() + "="+ path));
        System.out.println(pathes.keySet().size());
        System.out.println("Parallel");
        HashMap<Node,List<Node>> pathesParallel = dr.getShortestForAllOrdersParallel();
        printTime();
        pathesParallel.forEach((node,path) -> System.out.println(node.getId() + "="+ path));
    }

    private static void printTime()
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ss:SS");
        System.out.println(formatter.format(date));
    }













}
