package TTL.controllers.dataloader;

import java.util.List;

public interface csvLoader<T> {
    List<T> csvToList();
}
