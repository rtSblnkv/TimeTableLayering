package TTL;

import TTL.entity.OrderItem;
import com.opencsv.bean.AbstractCsvConverter;
import com.opencsv.bean.CsvConverter;

public class TextToOrderItems extends AbstractCsvConverter {

    //"[('Cereal', 8), ('Pancake', 6)]"
    //@Override
    public Object convertToRead(String value){
        OrderItem oi = new OrderItem();
        return oi;

    }

}