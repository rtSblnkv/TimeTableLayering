package TTL;

import TTL.controllers.*;
import TTL.controllers.dataloader.DataLoader;
import TTL.controllers.ToHashMap;
import TTL.models.Branch;
import TTL.models.Edge;
import TTL.models.Node;
import TTL.models.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DijkstraRunner {

    private List<Order> orders;
    private List<Node> nodes;
    private List<Edge> edges;
    private List<Branch> branches;

    private HashMap<String, Order> ordersHashMap;
    private HashMap<Long, Node> nodesHashMap;
    private HashMap<String, Node> branchNodes;


    private HashMap<String,List<Order>> ordersHashMapByOrderType;
    private HashMap<String,List<Order>> ordersHashMapByBranch;


    public DijkstraRunner(){
        orders = DataLoader.ordersToList();
        edges = DataLoader.edgesToList();
        nodes = DataLoader.nodesToList();
        branches = DataLoader.branchesToList();

        nodesHashMap = GraphCreator.createGraph(nodes,edges);
        ordersHashMap = ToHashMap.ordersListToHashMap(orders);

        ordersHashMapByOrderType = ToHashMap.ordersListToHashMapByOrderType(orders);
        ordersHashMapByBranch = ToHashMap.ordersListToHashMapByBranch(orders,branches);
    }


    public HashMap<Node,List<Node>> getShortestForAllOrdersLinear()
    {
        HashMap<Node,List<Node>> shortPathes = new HashMap<>();
        HashMap<Node,List<Node>> shortPathesTime = new HashMap<>();

        System.out.println("Orders size = " + orders.size());

        getBranchNodeMap();

        for(Map.Entry<String,List<Order>> branchOrdersMap : ordersHashMapByBranch.entrySet())
        {

            //System.out.println(branchOrdersMap.getKey());
            Dijkstra dijkstra = new Dijkstra();
            dijkstra.setNodesHashMap(nodesHashMap);
            Dijkstra dijkstraTime = new Dijkstra();
            dijkstraTime.setNodesHashMap(nodesHashMap);
            System.out.println("____________________|______|____|____|__|__|___|_______|____________________");
            Node branchNode = branchNodes.get("BK");
            dijkstra.computePathesFrom(branchNode);
            System.out.println("distance");
            dijkstra.printNodes();
            dijkstraTime.computePathesFromByTime(branchNode);
            System.out.println("time");
            dijkstraTime.printNodes();

            List<Order> branchOrders = branchOrdersMap.getValue();
            System.out.println(branchOrdersMap.getKey());
            branchOrders.forEach(order ->{
                Node nodeTo = Getters.getNodeByCoordinates(order.getLatitude(),order.getLongtitude(),nodes);
                if(nodeTo.getId() != 0) {
                    System.out.println("\n Current " + nodeTo);
                    List<Node> pathDistance = dijkstra.getShortestPathTo(nodeTo);
                    shortPathes.put(nodeTo, pathDistance);

                    List<Node> pathTime = dijkstra.getShortestPathTo(nodeTo);
                    shortPathesTime.put(nodeTo, pathTime);

                    System.out.println(" distance" + pathDistance + "\n time" + pathTime + "\n equals = "+ pathTime.equals(pathDistance));
                }

            });
        }
        return shortPathes;
    }

    private void getBranchNodeMap()
    {
        branchNodes = new HashMap<>();
        branches.forEach(branch ->
        {
            Node branchNode = Getters.getNodeByCoordinates(branch.getLatitude(),branch.getLongtitude(),nodes);
            branchNodes.put(branch.getBranchCode(),branchNode);
        });
        System.out.println(branchNodes);

    }



    public HashMap<Node,List<Node>> getShortestToOrderLinearMap()
    {
        HashMap<Node,List<Node>> shortPathes = new HashMap<>();

        return shortPathes;
    }

    public HashMap<Node,List<Node>> getShortestForAllOrdersSplitted()
    {
        HashMap<Node,List<Node>> shortPathes = new HashMap<>();

        return shortPathes;
    }




}
