package TTL.controllers.datainspector.encoder;

import TTL.models.Order;
import java.util.List;

public interface LabelEncoder {
    double[] labelEncode(List<Order> orders);
}
