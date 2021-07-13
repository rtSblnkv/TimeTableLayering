package TTL.controllers.datainspector.encoder;

import TTL.controllers.Getters;
import TTL.models.Order;

import java.util.List;

public class OrderTypeLabelEncoder implements LabelEncoder {
    @Override
    public int[] labelEncode(List<Order> orders) {
        List<String> orderTypes = Getters.getOrderTypes(orders);
        int ordersSize = orders.size();
        int[] orderTypeLE = new int[ordersSize];
        int i = 0;
        for(Order order : orders)
        {
            orderTypeLE[i]= orderTypes.indexOf(order.getOrderType());
            i++;
        }
        System.out.println(ordersSize);
        return orderTypeLE;
    }
}
