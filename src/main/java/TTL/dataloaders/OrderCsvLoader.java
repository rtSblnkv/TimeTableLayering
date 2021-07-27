package TTL.dataloaders;

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
        try(FileReader reader = new FileReader(path)){
            orders = new CsvToBeanBuilder(reader)
                    .withType(Order.class)
                    .build()
                    .parse();
        }
        catch(IOException|NullPointerException ex)
        {
            System.out.println("DataLoader of " + path + "IOException " + ex.getMessage());
        }
        return orders;
    }
}
