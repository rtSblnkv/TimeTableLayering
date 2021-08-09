package TTL.dataloaders;

import TTL.exception_handlers.UploadDataException;
import TTL.models.Edge;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class EdgeCsvLoader implements CsvLoader {
    /**
     * Uploads data from edges.csv
     *
     * @param path - path to edges.csv in resources directory
     * @return List of Edge type
     */
    @Override
    public List<Edge> csvToList(String path) throws UploadDataException, IllegalArgumentException {
        List<Edge> edges = null;
        try (FileReader reader = new FileReader(path)) {
            edges = new CsvToBeanBuilder(reader)
                    .withType(Edge.class)
                    .build()
                    .parse();
        } catch (IOException | NullPointerException ex) {
            String errMessage = "Can't be parsed : " + ex.getMessage();
            throw new UploadDataException(errMessage, ex);
        }
        if (edges == null || edges.isEmpty()) {
            throw new IllegalArgumentException(" edges list is empty or null");
        }

        return edges;
    }
}
