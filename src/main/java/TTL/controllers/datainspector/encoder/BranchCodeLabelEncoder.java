package TTL.controllers.datainspector.encoder;

import TTL.controllers.listWorkers.OrderWorker;
import TTL.models.Order;

import java.util.List;

public class BranchCodeLabelEncoder implements LabelEncoder {
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
