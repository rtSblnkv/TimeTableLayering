package TTL.models;


import TTL.utils.TextToOrderItems;
import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * orders.csv Data Object
 */
@ToString
@NoArgsConstructor
public class Order {

    @CsvBindByName(column="order_id")
    @Getter @Setter
    private String orderId;

    @CsvBindByName
    @Getter @Setter
    @CsvDate("YYYY-MM-DD")
    private Date date;

    @CsvBindByName
    @Getter @Setter
    private String time;

    @CsvBindByName(column="order_type")
    @Getter @Setter
    private String orderType;

    @CsvBindByName(column="branch_code")
    @Setter
    private String branchCode;

    @CsvBindByName(column="order_price")
    @Getter @Setter
    private double orderPrice;

    @CsvBindByName(column="customer_lat")
    @Getter @Setter
    private double latitude;

    @CsvBindByName(column="customer_lon")
    @Getter @Setter
    private double longtitude;

    @CsvBindByName(column="customerHasloyalty?")
    @Getter @Setter
    private Boolean hasLoyality;

    @CsvBindAndSplitByName(column="order_items",splitOn = "\\), \\(",
            elementType= OrderItem.class,converter = TextToOrderItems.class)
    @Getter @Setter
    private List<OrderItem> orderItems;

    @CsvBindByName(column="distance_to_customer_KM")
    @Getter @Setter
    private double distanceTo;

    @CsvBindByName(column="delivery_fee")
    @Getter @Setter
    private double deliveryFee;

    public String getBranchCode() {
        return branchCode.toUpperCase();
    }

}
