package TTL.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Object of order_items column in orders.csv
 */
@NoArgsConstructor
@Getter @Setter
public class OrderItem{

    private String dish;

    private int count;

    @Override
    public String toString() { return "(" + dish + "," + count + ")"; }
}
