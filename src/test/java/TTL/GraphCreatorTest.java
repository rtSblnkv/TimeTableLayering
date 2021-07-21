package TTL;

import TTL.controllers.listWorkers.NodeWorker;
import TTL.models.Branch;
import TTL.models.Edge;
import TTL.models.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class GraphCreatorTest {

    public List<Node> getNodes()
    {
        Node first = new Node();
        first.setId(1);
        first.setLatitude(-1);
        first.setLongtitude(1);

        Node second = new Node();
        second.setId(2);
        second.setLatitude(-2);
        second.setLongtitude(2);

        Node third = new Node();
        third.setId(3);
        third.setLatitude(-3);
        third.setLongtitude(3);

        Node fourth = new Node();
        fourth.setId(4);
        fourth.setLatitude(-4);
        fourth.setLongtitude(4);

        List<Node> nodes = new ArrayList<>();
        nodes.add(first);
        nodes.add(second);
        nodes.add(third);
        nodes.add(fourth);

        return nodes;
    }

    public List<Edge> getEdges()
    {
        Edge firstSecond = new Edge();
        firstSecond.setDistance(5);
        firstSecond.setFrom(1);
        firstSecond.setTo(2);

        Edge firstthird = new Edge();
        firstthird.setDistance(6);
        firstthird.setFrom(1);
        firstthird.setTo(3);

        Edge secondthird = new Edge();
        secondthird.setDistance(8);
        secondthird.setFrom(2);
        secondthird.setTo(3);

        Edge secondfourth = new Edge();
        secondfourth.setDistance(5);
        secondfourth.setFrom(2);
        secondfourth.setTo(4);

        Edge thirdfourth = new Edge();
        thirdfourth.setDistance(7);
        thirdfourth.setFrom(3);
        thirdfourth.setTo(4);

        List<Edge> edges = new ArrayList<>();
        edges.add(firstSecond);
        edges.add(firstthird);
        edges.add(secondthird);
        edges.add(secondfourth);
        edges.add(thirdfourth);
        return edges;

    }
    // nodes [1,2,3,4]
    // edges [12,13,23,24,34]
    public HashMap<Long, Node> testGraph()
    {
        List<Node> nodes = getNodes();
        List<Edge> edges = getEdges();

        nodes.get(0).addEdge(edges.get(0));
        nodes.get(0).addEdge(edges.get(1));
        nodes.get(1).addEdge(edges.get(2));
        nodes.get(1).addEdge(edges.get(3));
        nodes.get(2).addEdge(edges.get(4));
        nodes.get(3).setEdges(null);

        NodeWorker nw = new NodeWorker(nodes);
        return  nw.toHashMap();
    }

    @Test
    public void createGraph() {
        List<Node> nodes = getNodes();
        List<Edge> edges = getEdges();

        GraphCreator gc = new GraphCreator(nodes,edges);
        HashMap<Long, Node> graph = gc.createGraph();
        HashMap<Long, Node> testGraph = testGraph();

        for (Long id : graph.keySet()) {
            Node node1 = graph.get(id);
            Node node2 = testGraph.get(id);
            boolean equals = node1.equals(node2);
            assertEquals(node1, node2);
        }
        assertEquals(testGraph,graph);
    }
}