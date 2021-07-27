package TTL.dataloaders;

import java.io.IOException;
import java.util.List;

public interface CsvLoader {
    List csvToList(String path) throws IOException;
}
