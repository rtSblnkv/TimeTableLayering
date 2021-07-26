package TTL.controllers.dataloader;

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

    private static final HashMap<String,Class> classes = new HashMap<String,Class>(){{
        put("branches",Branch.class);
        put("edges",Edge.class);
        put("nodes",Node.class);
        put("orders",Order.class);
    }};

    //???????
    public List csvToListGeneric(String tableName)
    {
        List data = null;
        try{
            data = new CsvToBeanBuilder(new FileReader(csvPaths.get(tableName)))
                    .withType(classes.get(tableName))
                    .build()
                    .parse();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return data;
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
        catch(IOException|CsvException e)
        {
            System.out.println(e.getMessage());
        }

        return allRows;

        //aaa(reader, CSVReader.class);
    }

    public <T> void aaa(T object, Class<T> tClass) {
    }

}
