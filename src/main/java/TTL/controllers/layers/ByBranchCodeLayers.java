package TTL.controllers.layers;

import TTL.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ByBranchCodeLayers extends Layers {

    public ByBranchCodeLayers(List<Order> orders)
    {
        super(orders);
        setSplitter();
        splitOnLayers();
    }

    @Override
    public void setSplitter() {
        super.setSplitters(new ArrayList<>(
                super.getOrders()
                        .stream()
                        .map(Order::getBranchCode)
                        .collect(Collectors.toSet())
        ));

        /*ArrayList<String> orderTypes = new ArrayList<String>();
        for (Order order: super.getOrders())
        {
            String orderType = order.getBranchCode();
            if (! orderTypes.contains(orderType))
            {
                orderTypes.add(orderType);
            }
        }
        super.setSplitters(orderTypes);*/
    }

    @Override
    public void splitOnLayers() {
        super
                .getSplitters()
                .forEach(branchCode -> super
                        .getLayers()
                        .put(branchCode,new ArrayList<Order>()));

        super
                .getOrders()
                .forEach(order -> super
                        .getLayers()
                        .get(order.getBranchCode())
                        .add(order));
    }
}
