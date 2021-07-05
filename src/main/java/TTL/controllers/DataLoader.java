package TTL.controllers;

import TTL.models.Branch;
import TTL.models.Edge;
import TTL.models.Node;
import TTL.models.Order;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DataLoader {

    private static final HashMap<String,String> csvPaths = new HashMap<String,String>(){{
        put("branches","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\branches.csv");
        put("edges","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\edges.csv");
        put("nodes","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\nodes.csv");
        put("orders","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\orders.csv");
    }};

    public static List<Branch> branchesToList()
    {
        List<Branch> branches = null;
        try{
            branches = new CsvToBeanBuilder(new FileReader(csvPaths.get("branches")))
                    .withType(Branch.class)
                    .build()
                    .parse();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        return branches;
    }

    public static List<Edge> edgesToList()
    {
        List<Edge> edges = null;
        try{
            edges = new CsvToBeanBuilder(new FileReader(csvPaths.get("edges")))
                    .withType(Edge.class)
                    .build()
                    .parse();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        return edges;
    }

    public static List<Node> nodesToList()
    {
        List<Node> nodes = null;
        try{
            nodes = new CsvToBeanBuilder(new FileReader(csvPaths.get("nodes")))
                    .withType(Node.class)
                    .build()
                    .parse();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        return nodes;
    }

    public static List<Order> ordersToList()
    {
        List<Order> orders = null;
        try{
            orders = new CsvToBeanBuilder(new FileReader(csvPaths.get("orders")))
                    .withType(Order.class)
                    .build()
                    .parse();
        }
        catch(IOException ex)
        {
            System.out.println("DataLoader IOException: " + ex.getMessage());
        }
        catch(NullPointerException ex)
        {
            System.out.println("DataLoader NPException: " + ex.getMessage());
        }
        return orders;
    }

    //non-actual
    public static List<String[]> csvToList(String key)
    {
        CSVReader reader;
        List <String[]> allRows = null;
        try{
            reader = new CSVReader(new FileReader(csvPaths.get(key)));
            allRows = reader.readAll();
        }
        catch(IOException |CsvException e)
        {
            System.out.println(e.getMessage());
        }
        return allRows;
    }

}
