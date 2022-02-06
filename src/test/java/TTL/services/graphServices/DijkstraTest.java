package TTL.services.graphServices;

import TTL.TestDataCreator;
import TTL.exception_handlers.NoShortPathException;
import TTL.models.Edge;
import TTL.models.Node;
import TTL.models.Route;
import TTL.services.graphServices.dijkstra.Dijkstra;
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
    public void getShortestPathTo() {
        GraphCreator gc = new GraphCreator(nodes,edges);
        HashMap<Node,List<Edge>> graph = gc.createGraph();

        Dijkstra dijkstra = new Dijkstra(graph);
        Route<Node> path = dijkstra.getRoute(startNode,finishNode);

        List<Node> determinatePath = new ArrayList<>(List.of(nodes.get(0), nodes.get(1), nodes.get(3)));

        Assert.assertEquals(path.getRoute(),determinatePath);
    }

    @Test(expected = NoShortPathException.class)
    public void getShortestPathToCatchNoShortestPathException()
    {
        GraphCreator gc = new GraphCreator(nodes,edges);
        HashMap<Node,List<Edge>> graph = gc.createGraph();

        Dijkstra dijkstra = new Dijkstra(graph);
        Route<Node> path = dijkstra.getRoute(startNode,nodes.get(6));
    }

}