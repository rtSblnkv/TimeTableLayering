package TTL;

import TTL.entity.OrderItem;
import com.opencsv.bean.AbstractCsvConverter;

public class TextToOrderItems extends AbstractCsvConverter {
    public Object convertToRead(String orders) {
        OrderItem oi = new OrderItem();
        //[('Steak', 3), ('Salad', 1), ('Chicken', 6), ('Fries', 4), ('Burger', 8)] - Поступает CsvToBeanReader
        //Сплит по ), (
        // На вход данному методу поступают [('Steak', 3 ; 'Chicken', 6 или 'Burger', 8)]
        try{
        //Если поступил [('Steak', 3
        if (orders.startsWith("[("))
        {
            orders = orders.substring(2);
        }
        //Если поступил 'Burger', 8)]
        if (orders.endsWith(")]"))
        {
            orders = orders.substring(0,orders.length()-2);
        }
        // Если  'Chicken', 6
        // Сплитим  'Burger', 8 по , и пробелу получаем {"'Burger'","8"}
        String[] items = orders.split(", ");
        // OrderItem передаем блюдо без кавычек Burger
        oi.setDish(getItemWithoutSingleQuotes(items[0]));
        // 8
        oi.setCount(Integer.parseInt(items[1]));
        }
        catch(Exception ex)
        {
            System.out.println("textToOrder " + ex.getMessage());
        }
        return oi;
    }

    private String getItemWithoutSingleQuotes(String item)
    {
        if (item.startsWith("'") && item.startsWith("'"))
        {
            return item.substring(1,item.length()-1);
        }
        else return item;
    }
}