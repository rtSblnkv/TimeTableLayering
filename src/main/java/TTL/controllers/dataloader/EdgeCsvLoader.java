package TTL.controllers.dataloader;

import TTL.models.Edge;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class EdgeCsvLoader implements CsvLoader {
    /**
     * Uploads data from edges.csv
     * @param path - path to edges.csv in resources directory
     * @return List of Edge type
     */
    @Override
    public List<Edge> csvToList(String path) {
        List<Edge> edges = null;
        try{
            edges = new CsvToBeanBuilder(new FileReader(path))
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
}
