package TTL.controllers.dataloader;

import TTL.models.Order;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class OrderCsvLoader implements CsvLoader {
    /**
     * Uploads data from orders.csv
     * @param path - path to orders.csv in resources directory
     * @return List of Order type
     */
    @Override
    public List<Order> csvToList(String path) {
        List<Order> orders = null;
        try{
            orders = new CsvToBeanBuilder(new FileReader(path))
                    .withType(Order.class)
                    .build()
                    .parse();
        }
        catch(IOException ex)
        {
            System.out.println("DataLoader IOException: " + ex.getMessage());
        }
        catch(NullPointerException ex)
        {
            System.out.println("DataLoader NPException: " + ex.getMessage());
        }
        return orders;
    }
}
