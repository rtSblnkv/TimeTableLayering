package TTL.dataloaders;

import TTL.exception_handlers.UploadDataException;
import TTL.models.Node;
import TTL.utils.FileURLDecoder;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NodeCsvLoaderTest {

    @Test
    public void csvToList() {
        CsvLoader loader = new NodeCsvLoader();
        String path = FileURLDecoder.getPathToResource("nodes.csv");
        List<Node> nodesActual = loader.csvToList(path);
        Node node = new Node();
        node.setId(711327755L);
        node.setLatitude(-37.807675);
        node.setLongtitude(144.9558726);

        List<Node> nodesExpected = new ArrayList<>(List.of(node));

        Assert.assertEquals(nodesExpected,nodesActual);
    }

    @Test(expected = UploadDataException.class)
    public void csvToListCatchUploadDataException() {
        CsvLoader loader = new NodeCsvLoader();
        String path = FileURLDecoder.getPathToResource("nodes.csv");
        List<Node> nodes = loader.csvToList(path);
    }

    @Test(expected = IllegalArgumentException.class)
    public void csvToListCatchIllegalArgumentException() {
        CsvLoader loader = new NodeCsvLoader();
        String path = FileURLDecoder.getPathToResource("nodeTestEmpty.csv");
        List<Node> nodes = loader.csvToList(path);
    }
}