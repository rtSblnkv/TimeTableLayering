package TTL.controllers.dataloader;


public class CsvLoaderFactory {

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
