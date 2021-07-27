package TTL.services.graphServices;

import TTL.TestDataCreator;
import TTL.models.Edge;
import TTL.models.Node;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class GraphCreatorTest {

    @Test
    public void createGraph() {

        List<Node> nodes = TestDataCreator.getTestNodes();
        List<Edge> edges = TestDataCreator.getEdges();
        GraphCreator gc = new GraphCreator(nodes,edges);
        HashMap<Long, Node> graph = gc.createGraph();
        HashMap<Long, Node> testGraph = TestDataCreator.getTestGraph();

        for (Long id : graph.keySet()) {
            Node node1 = graph.get(id);
            Node node2 = testGraph.get(id);
            assertEquals(node1, node2);
        }
        assertEquals(testGraph,graph);
    }
}