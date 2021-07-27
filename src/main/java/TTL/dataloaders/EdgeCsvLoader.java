package TTL.dataloaders;

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
    public List<Edge> csvToList(String path) throws IOException {
        List<Edge> edges = null;
        try (FileReader reader = new FileReader(path)) {
            edges = new CsvToBeanBuilder(reader)
                    .withType(Edge.class)
                    .build()
                    .parse();
        } catch (IOException ex) {
            throw new IOException("Edge csvToList " + ex.getMessage());
        }
        return edges;
    }
}
