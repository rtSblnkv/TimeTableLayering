package TTL.dataloaders;

import TTL.exception_handlers.UploadDataException;
import TTL.models.Edge;
import TTL.models.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EdgeCsvLoaderTest {

    @Test
    public void csvToList() {
        CsvLoader loader = new EdgeCsvLoader();
        List<Edge> nodesActual = loader.csvToList("C:\\Users\\роппг\\IdeaProjects\\TimeTableLayering\\src\\main\\resources\\TestCsv\\edgesTest.csv");
        Edge edge = new Edge();
        edge.setFrom(711327755);
        edge.setTo(711332946);
        edge.setDistance(58.0);

        List<Edge> nodesExpected = new ArrayList<>(List.of(edge));

        Assert.assertEquals(nodesExpected,nodesActual);
    }

    @Test(expected = UploadDataException.class)
    public void csvToListCatchUploadDataException() {
        CsvLoader loader = new NodeCsvLoader();
        List<Node> nodes = loader.csvToList("C:\\Users\\роппг\\IdeaProjects\\TimeTableLayering\\src\\main\\resources\\TestCsv\\edge.csv");
    }
}