package TTL.controllers.listWorkers;

import TTL.models.Order;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class OrderWorker implements Worker {

    private List<Order> orders;

    public OrderWorker(){}

    public OrderWorker(List<Order> orders){
        this.orders = orders;
    }

    public void setOrders(List<Order> orders){
        this.orders = orders;
    }

    public List<String> getBranchCodes()
    {
        return orders
                .stream()
                .distinct()
                .map(Order::getBranchCode)
                .collect(Collectors.toList()
                );
        /*List<String> branchCodes = new ArrayList<String>();
        for (Branch branch: branches)
        {
            String branchCode = branch.getBranchCode();
            if (! branchCodes.contains(branchCode))
            {
                branchCodes.add(branchCode);
            }
        }
        return branchCodes;*/
    }

    public List<Double> getOrdersDistancesTo()
    {
        return orders
                .stream()
                .map(Order::getDistanceTo)
                .collect(Collectors.toList());
    }


    public List<String> getOrderTypes()
    {
        return orders
                        .stream()
                        .distinct()
                        .map(Order::getOrderType)
                        .collect(Collectors.toList()
                        );
        /*ArrayList<String> orderTypes = new ArrayList<String>();
        for (Order order: orders)
        {
            String orderType = order.getOrderType();
            if (! orderTypes.contains(orderType))
            {
                orderTypes.add(orderType);
            }
        }
        return orderTypes;*/
    }

    public void deleteOrdersWithIncorrectNodes(NodeWorker nw )
    {
        orders.removeIf(order -> nw.getNodeId(order.getLatitude(),order.getLongtitude()) == 0);
    }

    @Override
    public HashMap<String, Order> toHashMap() {
        HashMap<String,Order> ordersHashMap = new HashMap<>();
        orders.forEach(order -> ordersHashMap.put(order.getOrderId(),order));
        return ordersHashMap;
    }
}
