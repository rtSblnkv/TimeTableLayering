package TTL.services.graphServices.Scorers;

import TTL.models.Node;

public interface TargetScorer<T extends Node> {
    double computeCost(T from, T to);
}
