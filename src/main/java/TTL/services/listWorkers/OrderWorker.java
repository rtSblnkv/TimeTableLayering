package TTL.services.listWorkers;

import TTL.models.Order;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class which operates with list of orders
 */
@NoArgsConstructor
@AllArgsConstructor
public class OrderWorker implements Worker {

    @Setter
    private List<Order> orders;

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
     * Delete list of orders orders which Node computed by order latitude and longtitude are not exist in List of nodes.
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
