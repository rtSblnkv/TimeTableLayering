package TTL.models;


import TTL.utils.TextToOrderItems;
import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * orders.csv Data Object
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Order {

    @CsvBindByName(column = "order_id")
    private String orderId;

    @CsvBindByName
    @CsvDate("YYYY-MM-DD")
    private Date date;

    @CsvBindByName
    private String time;

    @CsvBindByName(column = "order_type")
    private String orderType;

    @CsvBindByName(column = "branch_code")
    private String branchCode;

    @CsvBindByName(column = "order_price")
    private double orderPrice;

    @CsvBindByName(column = "customer_lat")
    private double latitude;

    @CsvBindByName(column = "customer_lon")
    private double longtitude;

    @CsvBindByName(column = "customerHasloyalty?")
    private Boolean hasLoyality;

    @CsvBindAndSplitByName(column = "order_items", splitOn = "\\), \\(",
            elementType = OrderItem.class, converter = TextToOrderItems.class)
    private List<OrderItem> orderItems;

    @CsvBindByName(column = "distance_to_customer_KM")
    private double distanceTo;

    @CsvBindByName(column = "delivery_fee")
    private double deliveryFee;

    public String getBranchCode() {
        return branchCode.toUpperCase();
    }
}
