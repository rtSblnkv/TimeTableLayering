package TTL.controllers.listWorkers;

import TTL.models.Order;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class OrderWorker implements Worker {

    private List<Order> orders;

    /**
     * Empty constructor
     */
    public OrderWorker(){}

    /**
     * Constructor.Initializes orders
     * @param orders - List of orders
     */
    public OrderWorker(List<Order> orders){
        this.orders = orders;
    }

    /**
     * Sets the value of orders
     * @param orders - List of orders
     */
    public void setOrders(List<Order> orders){
        this.orders = orders;
    }

    /**
     * Returns list of branch codes from list of orders (Order.BranchCode)
     * @return list of branch codes
     */
    public List<String> getBranchCodes()
    {
        return orders
                .stream()
                .distinct()
                .map(Order::getBranchCode)
                .collect(Collectors.toList()
                );
    }

    /**
     * Returns list of order types from list of orders (Order.OrderType)
     * @return list of order types
     */
    public List<String> getOrderTypes()
    {
        return orders
                        .stream()
                        .distinct()
                        .map(Order::getOrderType)
                        .collect(Collectors.toList()
                        );
    }

    /**
     * Delete list of orders orders which Node.computed by latitude and longtitude are not exist in List of nodes.
     * @param nw - handler of list of nodes
     */
    public void deleteOrdersWithIncorrectNodes(NodeWorker nw )
    {
        orders.removeIf(order -> nw.getNodeId(order.getLatitude(),order.getLongtitude()) == 0);
    }

    /**
     * Converts list of orders into HashMap
     * @return HashMap ( order ID, order)
     */
    @Override
    public HashMap<String, Order> toHashMap() {
        HashMap<String,Order> ordersHashMap = new HashMap<>();
        orders.forEach(order -> ordersHashMap.put(order.getOrderId(),order));
        return ordersHashMap;
    }
}
