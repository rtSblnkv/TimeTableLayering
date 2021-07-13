package TTL.controllers.datainspector;

import TTL.controllers.Getters;
import TTL.models.Branch;
import TTL.models.Edge;
import TTL.models.Node;
import TTL.models.Order;

import javax.xml.crypto.Data;
import java.util.Iterator;
import java.util.List;

public class DataInspector {
    private List<Order> orders;
    private List<Branch> branches;
    private List<Node> nodes;
    private List<Edge> edges;

    public DataInspector(){}

    public List<Order> getOrders() { return orders; }

    public void setOrders(List<Order> orders) { this.orders = orders; }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    //correct
    private Boolean checkNodesExist(long nodeStartId, long nodeEndId)
    {
        Boolean firstExist = false;
        Boolean secondExist = false;
        if (nodes != null && !nodes.isEmpty())
        {
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
        }
        return firstExist && secondExist;
    }

    public Boolean checkEdgeNodesExist()
    {
        return edges
                .stream()
                .anyMatch( edge -> checkNodesExist(edge.getFrom(),edge.getTo()));
        /*for(Edge edge :edges)
        {
            if(!)
            {
                return false;
            }
        }
        return true;*/
    }

    //Method deletes orders with incorrect location
    public void deleteOrdersWithIncorrectNodes()
    {
        orders.removeIf(order -> Getters.getNodeId(order.getLatitude(),order.getLongtitude(),nodes) == 0);
    }




}
