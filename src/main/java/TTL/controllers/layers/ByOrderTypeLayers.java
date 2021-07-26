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
