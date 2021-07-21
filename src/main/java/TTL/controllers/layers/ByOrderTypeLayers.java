package TTL.controllers.layers;

import TTL.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ByOrderTypeLayers extends Layers {

    public ByOrderTypeLayers(List<Order> orders)
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
                        .map(Order::getOrderType)
                        .collect(Collectors.toSet())
        ));

        /*ArrayList<String> orderTypes = new ArrayList<String>();
        for (Order order: super.getOrders())
        {
            String orderType = order.getOrderType();
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
                .forEach(orderType -> super
                        .getLayers()
                        .put(orderType,new ArrayList<Order>()));

        super
                .getOrders()
                .forEach(order -> super
                        .getLayers()
                        .get(order.getOrderType())
                        .add(order));
    }
}
