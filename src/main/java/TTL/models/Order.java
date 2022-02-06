package TTL.models;


import TTL.utils.TextToOrderItems;
import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * orders.csv Data Object
 */
@NoArgsConstructor
@Setter
@ToString
@EqualsAndHashCode
public class Order {

    @CsvBindByName(column="order_id")
    @Getter
    private String orderId;

    @CsvBindByName
    @Getter
    @CsvDate("YYYY-MM-DD")
    private Date date;

    @CsvBindByName
    @Getter
    private String time;

    @CsvBindByName(column="order_type")
    @Getter
    private String orderType;

    @CsvBindByName(column="branch_code")
    private String branchCode;

    @CsvBindByName(column="order_price")
    @Getter
    private double orderPrice;

    @CsvBindByName(column="customer_lat")
    @Getter
    private double latitude;

    @CsvBindByName(column="customer_lon")
    @Getter
    private double longtitude;

    @CsvBindByName(column="customerHasloyalty?")
    @Getter
    private Boolean hasLoyality;

    @CsvBindAndSplitByName(column="order_items",splitOn = "\\), \\(",
            elementType= OrderItem.class,converter = TextToOrderItems.class)
    @Getter
    private List<OrderItem> orderItems;

    @CsvBindByName(column="distance_to_customer_KM")
    @Getter
    private double distanceTo;

    @CsvBindByName(column="delivery_fee")
    @Getter
    private double deliveryFee;

    public String getBranchCode() {
        return branchCode.toUpperCase();
    }

}
