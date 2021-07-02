package TTL.DataUtils;

import TTL.entity.Branch;
import TTL.entity.Edge;
import TTL.entity.Node;
import TTL.entity.Order;

import java.util.Iterator;
import java.util.List;

public class DataInspector {
    private List<Order> orders;
    private List<Branch> branches;
    private List<Node> nodes;
    private List<Edge> edges;

    public DataInspector(){}

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    private Boolean checkNodesExist(long nodeStartId, long nodeEndId)
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
            long id = Getters.getNodeId(order.getLatitude(),order.getLongtitude(),nodes);
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
            System.out.println(Getters.getNodeId(branch.getLatitude(),branch.getLongtitude(),nodes));
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
