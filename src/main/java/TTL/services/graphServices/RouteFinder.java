package TTL.services.graphServices;

import TTL.models.Node;
import TTL.models.Route;

public interface RouteFinder<T extends Node> {
    T getNodeById(long id);
    Route<T> getRoute(T nodeFrom, T nodeTo);
}
