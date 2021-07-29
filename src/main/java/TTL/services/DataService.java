package TTL.services;

import TTL.dataloaders.CsvLoader;
import TTL.dataloaders.CsvLoaderFactory;
import TTL.exception_handlers.UploadDataException;
import TTL.models.Branch;
import TTL.models.Edge;
import TTL.models.Node;
import TTL.models.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.HashMap;
import java.util.List;

@State(Scope.Benchmark)
@NoArgsConstructor
@Getter
public class DataService {

    private List<Order> orders;

    private List<Node> nodes;

    private List<Edge> edges;

    private List<Branch> branches;

    private static final HashMap<String,String> csvPaths = new HashMap<>(){{
        put("branches","C:\\Users\\роппг\\IdeaProjects\\TimeTableLayering\\src\\main\\resources\\branches.csv");
        put("edges","C:\\Users\\роппг\\IdeaProjects\\TimeTableLayering\\src\\main\\resources\\edges.csv");
        put("nodes","C:\\Users\\роппг\\IdeaProjects\\TimeTableLayering\\src\\main\\resources\\nodes.csv");
        put("orders","C:\\Users\\роппг\\IdeaProjects\\TimeTableLayering\\src\\main\\resources\\orders.csv");
    }};

    /**
     * Uploads data from dataset with csvToList methods
     * initialize branches,edges,nodes,orders.
     */
    @Benchmark
    public void uploadDataFromCsvFiles() throws UploadDataException
    {
        try {
            CsvLoaderFactory loaderFactory = new CsvLoaderFactory();

            CsvLoader branchLoader = loaderFactory.createCsvLoader("branches");
            branches = branchLoader.csvToList(csvPaths.get("branches"));

            System.out.println("branches " + branches.size());

            CsvLoader edgeLoader = loaderFactory.createCsvLoader("edges");
            edges = edgeLoader.csvToList(csvPaths.get("edges"));

            System.out.println("edges " + edges.size());

            CsvLoader nodeLoader = loaderFactory.createCsvLoader("nodes");
            nodes = nodeLoader.csvToList(csvPaths.get("nodes"));

            System.out.println("nodes " + nodes.size());

            CsvLoader orderLoader = loaderFactory.createCsvLoader("orders");
            orders = orderLoader.csvToList(csvPaths.get("orders"));

            System.out.println("orders " + orders.size());
        }
        catch (UploadDataException|IllegalArgumentException ex)
        {
            throw new UploadDataException(ex.getMessage(),ex);
        }
    }
}
