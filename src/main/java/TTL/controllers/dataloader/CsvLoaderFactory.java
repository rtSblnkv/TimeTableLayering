package TTL.controllers.dataloader;

import java.util.HashMap;

public class CsvLoaderFactory {

    private static final HashMap<String,String> csvPaths = new HashMap<String,String>(){{
        put("branches","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\branches.csv");
        put("edges","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\edges.csv");
        put("nodes","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\nodes.csv");
        put("orders","C:\\Users\\роппг\\IdeaProjects\\magentaTTL\\src\\main\\resources\\orders.csv");
    }};

    public CsvLoader createCsvLoader(String tableName)
    {
        switch(tableName)
        {
            case"branches": return new BranchCsvLoader();

            case"orders": return new OrderCsvLoader();

            case"edges":return new EdgeCsvLoader();

            case"nodes":return new NodeCsvLoader();

            default: return null;
        }
    }
}
