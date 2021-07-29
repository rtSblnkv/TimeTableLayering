package TTL.utils.encoders;

import TTL.models.Order;
import TTL.services.listWorkers.OrderWorker;

import java.util.List;

/**
 * Class realizes label encoder to double for order types in order list
 */
public class OrderTypeLabelEncoder implements LabelEncoder {
   @Override
   /**
    * realizes label encoder for order type elements of order in order list
    * @param orders - order list
    * @return array of double, which correspond to the order type created number
    * with position,which correspond to the position of the order in order list
    */
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
