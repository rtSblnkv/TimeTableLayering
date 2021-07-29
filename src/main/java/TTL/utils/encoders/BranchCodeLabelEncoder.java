package TTL.utils.encoders;

import TTL.models.Order;
import TTL.services.listWorkers.OrderWorker;

import java.util.List;

/**
 * Class realizes label encoder to double for branch codes in order list
 */
public class BranchCodeLabelEncoder implements LabelEncoder {
    /**
     * realizes label encoder for branch code elements of order in order list
     * @param orders - order list
     * @return array of double, which correspond to the branch code created number
     * with position,which correspond to the position of the order in order list
     */
    @Override
    public double[] labelEncode(List<Order> orders) {
        OrderWorker ow = new OrderWorker(orders);
        List<String> branchCodes = ow.getBranchCodes();
        int ordersSize = orders.size();
        double[] branchCodeLE = new double[ordersSize];
        int i = 0;
        for(Order order : orders)
        {
            branchCodeLE[i] = branchCodes.indexOf(order.getBranchCode().toUpperCase());
            i++;
        }
        System.out.println(ordersSize);
        return branchCodeLE;
    }

}
