package TTL.dataloaders;

import TTL.exception_handlers.UploadDataException;
import TTL.models.Edge;
import TTL.models.Node;
import TTL.utils.FileURLDecoder;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EdgeCsvLoaderTest {

    @Test
    public void csvToList() {
        CsvLoader loader = new EdgeCsvLoader();
        String path = FileURLDecoder.getPathToResource("edges.csv");
        //String path = getClass().getResource("/TestCsv/edgesTest.csv").toString();
        List<Edge> nodesActual = loader.csvToList(path);
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
        String path = FileURLDecoder.getPathToResource("edges.csv");
        List<Node> nodes = loader.csvToList(path);
    }

}