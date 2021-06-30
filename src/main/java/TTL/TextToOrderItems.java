package TTL;

import TTL.entity.OrderItem;
import com.opencsv.bean.AbstractCsvConverter;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextToOrderItems extends AbstractCsvConverter {
    //TODO: Решить трабл с двойным ArrayList [[(gg),()]]
    public Object convertToRead(String orders) {
        List<OrderItem> ois = new ArrayList<OrderItem>();
        Matcher matcher = Pattern.compile("\\('\\w*',\\s+\\d+\\)").matcher(orders);
        //('Cereal', 8)
        try {
            while (matcher.find()) {
                //('Cereal', 8)
                int startOfSubstring = matcher.start() + 1;
                int endOfSubstring = matcher.end() - 1;
                //'Cereal', 8
                String orderItem = orders.substring(startOfSubstring, endOfSubstring);
                // { "'Cereal'", "8"}
                String[] items = orderItem.split(", ");
                //Cereal
                String dish = items[0].substring(1, items[0].length() - 1);
                //(int) 8
                int count = Integer.parseInt(items[1]);

                OrderItem oi = new OrderItem();
                oi.setDish(dish);
                oi.setCount(count);
                ois.add(oi);
            }
        }
        catch(Exception ex)
        {
            System.out.println("textToOrder " + ex.getMessage());
        }
        return ois;
    }
}