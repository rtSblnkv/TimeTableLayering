package TTL.services.graphServices;

import TTL.TestDataCreator;
import TTL.exception_handlers.NoShortPathException;
import TTL.models.Edge;
import TTL.models.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DijkstraTest {

    private Node startNode;
    private Node finishNode;
    private List<Node> nodes;
    private List<Edge> edges;

    @Before
    public void initializeTestData()
    {
        nodes = TestDataCreator.getTestNodes();
        startNode = nodes.get(0);
        finishNode = nodes.get(3);
        edges = TestDataCreator.getTestEdges();
    }

    @Test
    public void computePathesFrom() {
        GraphCreator gc = new GraphCreator(nodes,edges);
        HashMap<Long,Node> graph = gc.createGraph();

        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.computeMinDistancesfrom(startNode);

        List minDistancesTest = Arrays.asList(0.0,5.0,6.0,10.0);
        List<Double> minDistances = nodes
                .stream()
                .map(Node::getMinDistance)
                .collect(Collectors.toList());

        Assert.assertEquals(minDistances,minDistancesTest);
    }

    @Test
    public void getShortestPathTo() {
        GraphCreator gc = new GraphCreator(nodes,edges);
        HashMap<Long,Node> graph = gc.createGraph();

        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.computeMinDistancesfrom(startNode);
        List<Node> path = dijkstra.getShortestPathTo(finishNode);

        List<Node> determinatePath = new ArrayList<>(List.of(nodes.get(0), nodes.get(1), nodes.get(3)));

        Assert.assertEquals(path,determinatePath);
    }

    @Test(expected = NoShortPathException.class)
    public void getShortestPathToCatchNoShortestPathException()
    {
        GraphCreator gc = new GraphCreator(nodes,edges);
        HashMap<Long,Node> graph = gc.createGraph();

        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.computeMinDistancesfrom(startNode);
        List<Node> path = dijkstra.getShortestPathTo(nodes.get(7));
    }

}