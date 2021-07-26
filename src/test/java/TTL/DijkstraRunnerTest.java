package TTL;

import TTL.controllers.datainspector.DeviationCalculator;
import TTL.controllers.listWorkers.NodeWorker;
import TTL.models.Edge;
import TTL.models.Node;
import TTL.models.Order;
import TTL.services.graphServices.DijkstraRunner;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;


public class DijkstraRunnerTest {

    public List<Node> getNodes()
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
    }

    @Test
    public void getShortestForAllOrdersLinear() {
        DijkstraRunner runner = new DijkstraRunner();
        HashMap<Node,List<Node>> pathes = runner.getShortestForAllOrdersLinear();
        List<Double> epsilons =
                pathes
                        .keySet()
                        .stream()
                        .map(Node::getEpsilon)
                        .collect(Collectors.toList());
        System.out.println("Epsilons  " + epsilons);
        DeviationCalculator devCalc = new DeviationCalculator(epsilons);
        double average = devCalc.getAverage();
        double deviation = devCalc.getStandartDeviation();
        System.out.println("Average epsilon = " + average + "; Standart deviation= " + deviation);
        Assert.assertTrue(deviation< 2000);

    }

    @Test
    public void getShortestForAllOrdersParallel() {
        DijkstraRunner runner = new DijkstraRunner();
        HashMap<Node,List<Node>> pathes = runner.getShortestForAllOrdersParallel();
        List<Double> epsilons =
                pathes
                .keySet()
                .stream()
                .map(Node::getEpsilon)
                .collect(Collectors.toList());
        System.out.println("Epsilons  " + epsilons);
        DeviationCalculator devCalc = new DeviationCalculator(epsilons);
        double average = devCalc.getAverage();
        double deviation = devCalc.getStandartDeviation();
        System.out.println("Average epsilon = " + average + "; Standart deviation= " + deviation);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void computePathesForLayer() {
    }
}