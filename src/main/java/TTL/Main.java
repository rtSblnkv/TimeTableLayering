package TTL;

import TTL.DataUtils.Correlation;
import TTL.DataUtils.DataInspector;
import TTL.DataUtils.DataLoader;
import TTL.DataUtils.Getters;
import TTL.entity.*;

import java.util.List;

public class Main {
    private static List<Order> orders;
    private static List<Branch> branches;
    private static List<Node> nodes;
    private static List<Edge> edges;

    //DONE:Посчитать корреляцию order_type и BranchCode : 0.14823003 - параметры независимы

    public static void main(String[]args)
    {
        orders = DataLoader.ordersToList();
        nodes = DataLoader.nodesToList();
        branches = DataLoader.branchesToList();
        edges = DataLoader.edgesToList();
        checkData();
    }

    static void checkData()
    {
        // Class which checkes input data
        DataInspector di = new DataInspector();

        di.setNodes(nodes);
        di.setBranches(branches);
        di.setEdges(edges);
        di.setOrders(orders);

        di.deleteOrdersWithIncorrectNodes();
        Getters.getCustomerNodesId(nodes,orders);
        System.out.println("Edges");
        di.checkEdgeNodesExist();
        System.out.println("Branches");
        di.checkBranchNodes();
        System.out.println(Correlation.getCorrelationOfOrderTypesAndBranchCodes(orders,branches));
    }







}
