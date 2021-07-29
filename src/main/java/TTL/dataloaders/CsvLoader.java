package TTL.dataloaders;

import TTL.exception_handlers.UploadDataException;

import java.util.List;

public interface CsvLoader {
    List csvToList(String path) throws UploadDataException;
}
