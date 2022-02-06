package TTL.services.graphServices.Scorers;

import TTL.models.Edge;

public class EdgeDistanceScorer<T extends Edge> implements NewNodeScorer<T>{
    @Override
    public double computeCost(T edge) {
        return edge.getDistance();
    }
}
