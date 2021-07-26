package TTL.services.graphServices;

import TTL.controllers.listWorkers.NodeWorker;
import TTL.models.Edge;
import TTL.models.Node;
import TTL.models.Order;
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

    /*public List<Node> getNodes()
    {
        Node node1 = new Node();
        node1.setId(1);
        node1.setLatitude(-1);
        node1.setLongtitude(1);
        //startNode = node1;

        Node node2 = new Node();
        node2.setId(2);
        node2.setLatitude(-2);
        node2.setLongtitude(2);

        Node node3 = new Node();
        node3.setId(3);
        node3.setLatitude(-3);
        node3.setLongtitude(3);

        Node node4 = new Node();
        node4.setId(4);
        node4.setLatitude(-4);
        node4.setLongtitude(4);

        Node node5 = new Node();
        node5.setId(5);
        node5.setLatitude(-5);
        node5.setLongtitude(5);

        Node node6 = new Node();
        node6.setId(6);
        node6.setLatitude(-6);
        node6.setLongtitude(6);

        Node node7 = new Node();
        node7.setId(7);
        node7.setLatitude(-7);
        node7.setLongtitude(7);

        List<Node> nodes = new ArrayList<>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        nodes.add(node5);
        nodes.add(node6);
        nodes.add(node7);

        return nodes;
    }

    public List<Edge> getEdges()
    {
        Edge edge12 = new Edge();
        edge12.setDistance(5);
        edge12.setFrom(1);
        edge12.setTo(2);

        Edge edge13 = new Edge();
        edge13.setDistance(6);
        edge13.setFrom(1);
        edge13.setTo(3);

        Edge edge23 = new Edge();
        edge23.setDistance(8);
        edge23.setFrom(2);
        edge23.setTo(3);

        Edge edge24 = new Edge();
        edge24.setDistance(5);
        edge24.setFrom(2);
        edge24.setTo(4);

        Edge edge34 = new Edge();
        edge34.setDistance(7);
        edge34.setFrom(3);
        edge34.setTo(4);

        Edge edge35 = new Edge();
        edge35.setDistance(3);
        edge35.setFrom(3);
        edge35.setTo(5);

        Edge edge45 = new Edge();
        edge45.setDistance(4);
        edge45.setFrom(4);
        edge45.setTo(5);

        Edge edge46 = new Edge();
        edge46.setDistance(5);
        edge46.setFrom(4);
        edge46.setTo(6);

        Edge edge57 = new Edge();
        edge57.setDistance(7);
        edge57.setFrom(5);
        edge57.setTo(7);

        List<Edge> edges = new ArrayList<>();
        edges.add(edge12);
        edges.add(edge13);
        edges.add(edge23);
        edges.add(edge24);
        edges.add(edge34);
        edges.add(edge35);
        edges.add(edge45);
        edges.add(edge46);
        edges.add(edge57);
        return edges;

    }

    // nodes [1,2,3,4,5,6,7]
    // edges [12,13,23,24,34,35,45,46,57]
    public HashMap<Long, Node> testGraph()
    {
        List<Node> nodes = getNodes();
        List<Edge> edges = getEdges();

        nodes.get(0).addEdge(edges.get(0));
        nodes.get(0).addEdge(edges.get(1));
        nodes.get(1).addEdge(edges.get(2));
        nodes.get(1).addEdge(edges.get(3));
        nodes.get(2).addEdge(edges.get(4));
        nodes.get(2).addEdge(edges.get(5));
        nodes.get(3).addEdge(edges.get(6));
        nodes.get(3).addEdge(edges.get(7));
        nodes.get(4).addEdge(edges.get(8));

        NodeWorker nw = new NodeWorker(nodes);
        return  nw.toHashMap();
    }

    public List<Order> getOrders()
    {
        Order order1 = new Order();
        order1.setBranchCode("NS");
        order1.setLatitude(-6);
        order1.setLongtitude(6);

        Order order2 = new Order();
        order2.setBranchCode("TP");
        order2.setLatitude(-6);
        order2.setLongtitude(6);

        Order order3 = new Order();
        order3.setBranchCode("BK");
        order3.setLatitude(-7);
        order3.setLongtitude(7);

        Order order4 = new Order();
        order2.setBranchCode("TP");
        order2.setLatitude(-4);
        order2.setLongtitude(4);

        Order order5 = new Order();
        order5.setBranchCode("NS");
        order5.setLatitude(-2);
        order5.setLongtitude(2);

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);

        return orders;
    }*/

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