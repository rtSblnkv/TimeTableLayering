package TTL;

import TTL.entity.Branch;
import TTL.entity.Edge;
import TTL.entity.Node;
import TTL.entity.Order;

import java.util.List;
import static TTL.DataLoader.*;

public class Main {
    public static void main(String[]args)
    {
        List<Order> nodes = DataLoader.ordersToList();
        System.out.println(nodes.size());
       // List<Order> orders = DataLoader.ordersToList();
    }

}
