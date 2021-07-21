package TTL.controllers.datainspector.encoder;

import TTL.controllers.listWorkers.OrderWorker;
import TTL.models.Order;

import java.util.List;

public class OrderTypeLabelEncoder implements LabelEncoder {
   @Override
    public double[] labelEncode(List<Order> orders) {
       OrderWorker ow = new OrderWorker(orders);
        List<String> orderTypes = ow.getOrderTypes();
        int ordersSize = orders.size();
        double[] orderTypeLE = new double[ordersSize];
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
