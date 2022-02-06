package TTL.services.graphServices.Scorers;

import TTL.models.Edge;

public interface NewNodeScorer<T extends Edge> {
    double computeCost(T edge);
}
