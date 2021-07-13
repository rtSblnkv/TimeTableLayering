package TTL.controllers.datainspector.encoder;

import TTL.controllers.Getters;
import TTL.models.Order;

import java.util.List;

public class BranchCodeLabelEncoder implements LabelEncoder {
    @Override
    public int[] labelEncode(List<Order> orders) {
        List<String> branchCodes = Getters.getBranchCodes(orders);
        int ordersSize = orders.size();
        int[] branchCodeLE = new int[ordersSize];
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
