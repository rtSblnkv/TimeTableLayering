package TTL.utils;

import TTL.models.OrderItem;
import com.opencsv.bean.AbstractCsvConverter;

public class TextToOrderItems extends AbstractCsvConverter {
    /**
     * Converts list in column order_items of orders.csv into OrderItem Object
     * Used as converter in parsing of orders.csv
     *
     * @param orders - element of order_items
     * @return OrderItem vaue contains Dish(string) and count(Integer)
     */
    public Object convertToRead(String orders) {
        OrderItem oi = new OrderItem();
        //[('Steak', 3), ('Salad', 1), ('Chicken', 6), ('Fries', 4), ('Burger', 8)] - Got by CsvToBeanReader
        //Split on ), (
        // Input data: [('Steak', 3   or   'Chicken', 6 or 'Burger', 8)]
        try {
            //If [('Steak', 3
            if (orders.startsWith("[(")) {
                orders = orders.substring(2);
            }
            //Or if 'Burger', 8)]
            if (orders.endsWith(")]")) {
                orders = orders.substring(0, orders.length() - 2);
            }
            // or if  'Chicken', 6
            // split 'Burger', 8 on ', ' and get {"'Burger'","8"}
            String[] items = orders.split(", ");
            // return dish without quotes to OrderItem Burger
            oi.setDish(getItemWithoutSingleQuotes(items[0]));
            // 8
            oi.setCount(Integer.parseInt(items[1]));
        } catch (Exception ex) {
            System.out.println("textToOrder " + ex.getMessage());
        }
        return oi;
    }

    private String getItemWithoutSingleQuotes(String item) {
        if (item.startsWith("'") && item.startsWith("'")) {
            return item.substring(1, item.length() - 1);
        } else return item;
    }
}