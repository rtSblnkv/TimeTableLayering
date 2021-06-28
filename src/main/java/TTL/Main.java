package TTL;

import java.util.List;
import static TTL.DataLoader.*;

public class Main {
    public static void main(String[]args)
    {
        List<String[]> data  = csvToList("orders");
        printInfo(data.subList(0,5));
    }

}
