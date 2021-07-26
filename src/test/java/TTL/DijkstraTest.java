package TTL;

import TTL.models.Edge;
import TTL.models.Node;
import TTL.services.graphServices.Dijkstra;
import TTL.services.graphServices.GraphCreator;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DijkstraTest {

    Node startNode;
    Node finishNode;



    public List<Node> getNodes()
    {
        Node first = new Node();
        first.setId(1);
        first.setLatitude(-1);
        first.setLongtitude(1);
        startNode = first;

        Node second = new Node();
        second.setId(2);
        second.setLatitude(-2);
        second.setLongtitude(2);

        Node node3 = new Node();
        node3.setId(3);
        node3.setLatitude(-3);
        node3.setLongtitude(3);

        Node node4 = new Node();
        node4.setId(4);
        node4.setLatitude(-4);
        node4.setLongtitude(4);
        finishNode = node4;

        List<Node> nodes = new ArrayList<>();
        nodes.add(first);
        nodes.add(second);
        nodes.add(node3);
        nodes.add(node4);

        return nodes;
    }

    public List<Edge> getEdges()
    {

        Edge firstSecond = new Edge();
        firstSecond.setDistance(5);
        firstSecond.setFrom(1);
        firstSecond.setTo(2);

        Edge firstnode3 = new Edge();
        firstnode3.setDistance(6);
        firstnode3.setFrom(1);
        firstnode3.setTo(3);

        Edge secondnode3 = new Edge();
        secondnode3.setDistance(8);
        secondnode3.setFrom(2);
        secondnode3.setTo(3);

        Edge secondnode4 = new Edge();
        secondnode4.setDistance(5);
        secondnode4.setFrom(2);
        secondnode4.setTo(4);

        Edge node3node4 = new Edge();
        node3node4.setDistance(7);
        node3node4.setFrom(3);
        node3node4.setTo(4);

        List<Edge> edges = new ArrayList<>();
        edges.add(firstSecond);
        edges.add(firstnode3);
        edges.add(secondnode3);
        edges.add(secondnode4);
        edges.add(node3node4);
        return edges;

    }

    @Test
    public void computePathesFrom() {
        List<Node> nodes = getNodes();
        List<Edge> edges = getEdges();
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
        List<Node> nodes = getNodes();
        List<Edge> edges = getEdges();
        GraphCreator gc = new GraphCreator(nodes,edges);
        HashMap<Long,Node> graph = gc.createGraph();

        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.computeMinDistancesfrom(startNode);
        List<Node> path = dijkstra.getShortestPathTo(finishNode);
        List<Node> determinatePath = new ArrayList<>();
        determinatePath.add(nodes.get(0));
        determinatePath.add(nodes.get(1));
        determinatePath.add(nodes.get(3));
        Assert.assertEquals(path,determinatePath);
    }

    @Test
    public void clearGraph() {
        List<Node> nodes = getNodes();
        List<Edge> edges = getEdges();
        GraphCreator gcClean = new GraphCreator(nodes,edges);
        HashMap<Long,Node> graphClean = gcClean.createGraph();

        GraphCreator gc = new GraphCreator(nodes,edges);
        HashMap<Long,Node> graph = gc.createGraph();
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.computeMinDistancesfrom(startNode);

        Assert.assertEquals(graphClean,dijkstra.getGraph());
    }


}