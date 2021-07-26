package TTL.controllers.dataloader;

import TTL.models.Node;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class NodeCsvLoader implements CsvLoader {
    /**
     * Uploads data from nodes.csv
     * @param path - path to nodes.csv in resources directory
     * @return List of Node type
     */
    @Override
    public List csvToList(String path) {
        List<Node> nodes = null;
        try{
            nodes = new CsvToBeanBuilder(new FileReader(path))
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
}
