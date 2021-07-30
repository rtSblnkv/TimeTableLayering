package TTL.services.graphServices;

import TTL.controllers.layers.ByBranchCodeLayers;
import TTL.controllers.layers.ByOrderTypeLayers;
import TTL.models.Branch;
import TTL.models.Edge;
import TTL.models.Node;
import TTL.models.Order;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.HashMap;
import java.util.List;


/**
 * Class which calculates short pathes for list of orders
 * in different variations:
 * for full list of orders
 * linear and parallel versions for splitted by
 *  - order type
 *  - branch code
 *  linear and parallel for splitted on branch code orders
 *  with preliminary launching of the dijkstra algorithm for each branch location
 */
@State(Scope.Benchmark)
@NoArgsConstructor
@AllArgsConstructor
public class PathCalculatorLauncher {

    private DijkstraRunner runner;
    private List<Order> orders;

    /**
     * Linear computing of shortest pathes and distances for each order in full list of orders
     * @return Map(Node, lists,which contains shortest path to the Node from restaurant)
     */
    @Benchmark
    public HashMap<Node, List<Node>> getShortestForAllOrders()
    {
        System.out.println("Linear All orders");
        return runner.computePathes(orders,"","orders");
    }

    /**
     * Linear computing of shortest pathes and distances for each order in list orders splitted by order type
     * @return Map(Node, lists,which contains shortest path to the Node from restaurant)
     */
    @Benchmark
    public HashMap<Node,List<Node>> getShortestForAllOrdersByOrderTypeLinear()
    {
        ByOrderTypeLayers byOrderTypeLayers = new ByOrderTypeLayers(orders);
        System.out.println("Order Type Linear");
        return byOrderTypeLayers.getLayers()
                .entrySet()
                .stream()
                .map(branchOrders -> runner.computePathes(branchOrders.getValue(),branchOrders.getKey(),"OrderTypeLinear"))
                .reduce(this::merge)
                .orElse(new HashMap<>() );
    }

    /**
     * Parallel computing of shortest pathes and distances for each order in list orders splitted by order type
     * @return Map(Node, lists,which contains shortest path to the Node from restaurant)
     */
    @Benchmark
    public HashMap<Node,List<Node>> getShortestForAllOrdersByOrderTypeParallel()
    {
        ByOrderTypeLayers  byOrderTypeLayers = new ByOrderTypeLayers(orders);
        System.out.println(" Order Type Parallel");
        return byOrderTypeLayers.getLayers()
                .entrySet()
                .parallelStream()
                .map(branchOrders -> runner.computePathes(branchOrders.getValue(),branchOrders.getKey(),"OrderTypeParallel"))
                .reduce(this::merge)
                .orElse(new HashMap<>() );
    }

    /**
     * Linear computing of shortest pathes and distances for each order in list orders splitted by branch code
     * @return Map(Node, lists,which contains shortest path to the Node from restaurant)
     */
    @Benchmark
    public HashMap<Node,List<Node>> getShortestForAllOrdersByBranchCodeLinear()
    {
        ByBranchCodeLayers byBranchCodeLayers = new ByBranchCodeLayers(orders);
        System.out.println("Branch Code Linear");
        return byBranchCodeLayers.getLayers()
                .entrySet()
                .stream()
                .map(branchOrders -> runner.computePathes(branchOrders.getValue(),branchOrders.getKey(),"BranchCodeLinear"))
                .reduce(this::merge)
                .orElse(new HashMap<>() );
    }

    /**
     * Parallel computing of shortest pathes and distances for each order in list orders splitted by branch code
     * @return Map(Node, lists,which contains shortest path to the Node from restaurant)
     */
    @Benchmark
    public HashMap<Node,List<Node>> getShortestForAllOrdersByBranchCodeParallel()
    {
        ByBranchCodeLayers byBranchCodeLayers = new ByBranchCodeLayers(orders);
        System.out.println("Branch Code Parallel");
        return byBranchCodeLayers.getLayers()
                .entrySet()
                .parallelStream()
                .map(branchOrders -> runner.computePathes(branchOrders.getValue(),branchOrders.getKey(),"BranchCodeParallel"))
                .reduce(this::merge)
                .orElse(new HashMap<>() );
    }

    /**
     * Linear computing of shortest pathes and distances for each order in list orders splitted by branch code
     * Using computePathesForLayer method
     * @return Map(Node, lists,which contains shortest path to the Node from restaurant)
     */
    @Benchmark
    public HashMap<Node,List<Node>> getShortestForOrdersByBranchCodeLinearPreliminary()
    {
        ByBranchCodeLayers byBranchCodeLayers = new ByBranchCodeLayers(orders);
        System.out.println("Branch Code Linear by Layer");
        return byBranchCodeLayers.getLayers()
                .entrySet()
                .stream()
                .map(branchOrders -> runner.computePathesForLayer(branchOrders.getKey(),branchOrders.getValue(),"BranchCodeLinear"))
                .reduce(this::merge)
                .orElse(new HashMap<>() );
    }

    /**
     * Parallel computing of shortest pathes and distances for each order in list orders splitted by branch code
     * Using computePathesForLayer method
     * @return Map(Node, lists,which contains shortest path to the Node from restaurant)
     */
    @Benchmark
    public HashMap<Node,List<Node>> getShortestForOrdersByBranchCodeParallelPreliminary() {
        ByBranchCodeLayers byBranchCodeLayers = new ByBranchCodeLayers(orders);
        System.out.println("Branch Code Parallel by Layer");
        return byBranchCodeLayers.getLayers()
                .entrySet()
                .parallelStream()
                .map(branchOrders -> runner.computePathesForLayer(branchOrders.getKey(),branchOrders.getValue(),"BranchCodeParallel"))
                .reduce(this::merge)
                .orElse(new HashMap<>() );
    }

    /**
     * merge 2 hashmaps into 1
     * @param firstMap - HashMap <Node,List<Node>>
     * @param secondMap- HashMap <Node,List<Node>>
     * @return
     */
    private HashMap<Node,List<Node>> merge(HashMap<Node,List<Node>> firstMap,HashMap<Node,List<Node>> secondMap) {
        firstMap.putAll(secondMap);
        return firstMap;
    }

}
