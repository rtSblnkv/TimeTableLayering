package TTL;

import com.opencsv.CSVReader;
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

    public static List<String[]> csvToList(String key)
    {
        CSVReader reader;
        List <String[]> allRows = null;
        try{
            reader = new CSVReader(new FileReader(csvPaths.get(key)));
            allRows = reader.readAll();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        catch(CsvException exp)
        {
            System.out.println(exp.getMessage());
        }
        return allRows;
    }

    public static void printInfo(List<String[]> data)
    {
        for(String[] line : data)
        {
            for(String str : line)
            {
                System.out.println(str + " ");
            }
            System.out.println("\n");
        }
    }

}
