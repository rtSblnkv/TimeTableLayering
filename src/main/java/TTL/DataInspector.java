package TTL;

import TTL.entity.Branch;
import TTL.entity.Edge;
import TTL.entity.Node;
import TTL.entity.Order;

import java.util.Iterator;
import java.util.List;

public class DataInspector {
    private static List<Order> orders;
    private static List<Branch> branches;
    private static List<Node> nodes;
    private static List<Edge> edges;

    DataInspector(){}

    void setOrders(List<Order> orders) {
        DataInspector.orders = orders;
    }

    void setBranches(List<Branch> branches) {
        DataInspector.branches = branches;
    }

    void setNodes(List<Node> nodes) {
        DataInspector.nodes = nodes;
    }

    void setEdges(List<Edge> edges) {
        DataInspector.edges = edges;
    }

    private long getNodeId(double lat,double lon)
    {
        for (Node node : nodes)
        {
            if (lat == node.getLatitude() && lon == node.getLongtitude())
            {
                return node.getId();
            }
        }
        return 0;
    }

    // Method get ID of nodes if customer location exist in Nodes else 0
    public void getCustomerNodes()
    {
        for (Order order : orders)
        {
            long id = getNodeId(order.getLatitude(),order.getLongtitude());
            if (id == 0)
            {
                System.out.println(order);
            }
            System.out.println("\n" + id);
        }
    }

    private Boolean checkNodesExist(long nodeStartId,long nodeEndId)
    {
        Boolean firstExist = false;
        Boolean secondExist = false;
        for(Node node : nodes)
        {
            if(node.getId() == nodeStartId)
            {
                firstExist = true;
            }
            if(node.getId() == nodeEndId)
            {
                secondExist = true;
            }
        }
        return firstExist && secondExist;
    }

    //Method deletes orders with incorrect location
    public void deleteOrdersWithIncorrectNodes()
    {
        Iterator ordersIterator = orders.iterator();
        while(ordersIterator.hasNext())
        {
            Order order = (Order)ordersIterator.next();
            long id = getNodeId(order.getLatitude(),order.getLongtitude());
            if (id == 0)
            {
                ordersIterator.remove();
            }
        }
    }

    public void checkBranchNodes()
    {
        for (Branch branch : branches)
        {
            System.out.println(getNodeId(branch.getLatitude(),branch.getLongtitude()));
        }
    }

    public void checkEdgeNodesExist()
    {
        for(Edge edge :edges)
        {
            System.out.println(checkNodesExist(edge.getFrom(),edge.getTo()));
        }
    }

}
