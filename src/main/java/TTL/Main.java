package TTL;

import TTL.entity.*;


import java.util.List;

import static TTL.DataLoader.*;

public class Main {
    public static void main(String[]args)
    {
        List<Order> nodes = DataLoader.ordersToList();
        System.out.println(nodes.size());
    }
}
