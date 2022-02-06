package TTL.services.graphServices.a_star;

import TTL.exception_handlers.NoShortPathException;
import TTL.models.Edge;
import TTL.models.Node;
import TTL.models.Route;
import TTL.models.RouteNode;
import TTL.services.graphServices.RouteFinder;
import TTL.services.graphServices.Scorers.DistanceTargetScorer;
import TTL.services.graphServices.Scorers.EdgeDistanceScorer;
import TTL.services.graphServices.Scorers.NewNodeScorer;
import TTL.services.graphServices.Scorers.TargetScorer;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@NoArgsConstructor
@Setter
public class AStar <T extends Node> implements RouteFinder<T> {

    private NewNodeScorer<Edge> nextNodeTargetScorer;
    private TargetScorer<T> targetScorer;
    private Map<T,List<Edge>> graph;

    public AStar(Map<T,List<Edge>> graph){
        this.graph = graph;
        this.nextNodeTargetScorer = new EdgeDistanceScorer<>();
        this.targetScorer = new DistanceTargetScorer<>();
    }

    public AStar(NewNodeScorer<Edge> nextNodeTargetScorer, TargetScorer<T> targetScorer, Map<T,List<Edge>> graph){
        this.graph = graph;
        this.nextNodeTargetScorer = nextNodeTargetScorer;
        this.targetScorer = targetScorer;
    }

    /**
     * Returns shortest path to Node nodeTo from node,
     * for which computeMinDistancesFrom was launched
     * @param nodeFrom - start point
     * @param nodeTo - The final point to compute short path for
     * @return list of nodes, which contains the shortest path to nodeTo
     */
    public Route<T> getRoute(T nodeFrom, T nodeTo,NewNodeScorer<Edge> nextNodeTargetScorer,TargetScorer<T> targetScorer) throws NoShortPathException
    {
        Queue<RouteNode<T>> openSet = new PriorityQueue<>();
        Map<Long,RouteNode<T>> allNodes = new HashMap<>();

        RouteNode<T> start = new RouteNode<>(
                nodeFrom,
                null,
                0,
                targetScorer.computeCost(nodeFrom,nodeTo)
        );

        openSet.add(start);
        allNodes.put(nodeFrom.getId(),start);

        while(!openSet.isEmpty()){
            RouteNode<T> next = openSet.poll();
            if (next.getCurrent().equals(nodeTo)) {
                List<T> route = new ArrayList<>();
                RouteNode<T> current = next;
                double score = current.getRouteScore();

                for(;;  current = allNodes.get(current.getPrevious().getId())){
                    route.add(0, current.getCurrent());
                    if(current.getPrevious() == null) break;
                }

                Route<T> result = new Route<T>();
                result.setRouteScore(score);
                result.setRoute(route);
                return result;
            }
            List<Edge> curNodeEdges = graph.get(next.getCurrent());
            if(curNodeEdges != null){
                curNodeEdges.forEach(edge -> {
                    if(nodeWithIdExist(edge.getTo())){
                        T curNodeTo = getNodeById(edge.getTo());
                        RouteNode<T> nextNode = allNodes.getOrDefault(curNodeTo.getId(), new RouteNode<T>(curNodeTo));
                        allNodes.put(curNodeTo.getId(), nextNode);

                        double newScore = next.getRouteScore() + nextNodeTargetScorer.computeCost(edge);
                        if (newScore < nextNode.getRouteScore()) {
                            nextNode.setPrevious(next.getCurrent());
                            nextNode.setRouteScore(newScore);
                            nextNode.setEstimatedScore(newScore + targetScorer.computeCost(getNodeById(edge.getTo()), nodeTo));
                            openSet.add(nextNode);
                        }
                    }
                });
            }
        }
        throw new NoShortPathException("No route found", start.getCurrent());
    }

    public boolean nodeWithIdExist(long id) {
        return graph.keySet()
                .parallelStream()
                .anyMatch(node -> node.getId() == id);
    }

    @Override
    public T getNodeById(long id) {
        return graph.keySet()
                .parallelStream()
                .filter(node -> node.getId() == id)
                .findAny()
                .get();
    }

    public  Route<T> getRoute(T nodeFrom, T nodeTo) throws NoShortPathException {
        return getRoute(nodeFrom,nodeTo,nextNodeTargetScorer,targetScorer);
    }
}
