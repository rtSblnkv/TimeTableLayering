package TTL;

import TTL.models.Edge;
import TTL.models.Node;
import TTL.models.Order;
import TTL.services.listWorkers.EdgeWorker;
import TTL.services.listWorkers.NodeWorker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Test data creator for DijkstraTest,DijkstraRunnerTest,GraphCreatorTest.
 */
public class TestDataCreator {

    public static List<Node> getTestNodes()
    {
        List<Node> nodes = new ArrayList<>();
        for(int i = 1; i<=7;i++) {
            Node node = new Node();
            node.setLatitude(-i);
            node.setLongtitude(i);
            node.setId(i);

            nodes.add(node);
        }
        return nodes;
    }

    public static List<Edge> getTestEdges()
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

        return new ArrayList<>(List.of(edge12,edge13,edge23,edge24,edge34,edge35,edge45,edge46,edge57));
    }

    // nodes [1,2,3,4,5,6,7]
    // edges [12,13,23,24,34,35,45,46,57]
    public static HashMap<Long, Node> getTestGraph()
    {
        List<Node> nodes = getTestNodes();
        List<Edge> edges = getTestEdges();

        EdgeWorker edgeWorker = new EdgeWorker(edges);

        HashMap<Long,List<Edge>> edgeHashMap = edgeWorker.toHashMap();
        NodeWorker nodeWorker = new NodeWorker(nodes);
        HashMap<Long, Node> nodesHashMap = nodeWorker.toHashMap();

        HashMap<Node,List<Edge>> graph = new HashMap<>();

        edgeHashMap.keySet().forEach(id -> graph.put(
                        nodesHashMap.get(id),
                        edgeHashMap.get(id)
                )
        );

        NodeWorker nw = new NodeWorker(nodes);
        return  nw.toHashMap();
    }

    public static List<Order> getTestOrders()
    {
        List<Order> orders = new ArrayList<>();
        for(int i = 1; i<=7;i++)
        {
            Order order = new Order();
            order.setBranchCode("NS");
            order.setLatitude(-i);
            order.setLongtitude(i);
            i++;
        }
        return orders;
    }
}
