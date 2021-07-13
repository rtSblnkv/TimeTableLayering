package TTL.controllers.datainspector.encoder;

import TTL.models.Order;

import java.util.List;

public interface LabelEncoder {
    int[] labelEncode(List<Order> orders);
}
