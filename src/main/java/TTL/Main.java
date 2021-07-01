package TTL;

import TTL.entity.*;


import java.util.List;

import static TTL.DataLoader.*;

public class Main {
    public static void main(String[]args)
    {
        List<Order> orders = DataLoader.ordersToList();
        for (Order order : orders)
        {
            System.out.println(order.getOrderItems());
        }
        System.out.println(orders.size());
    }
}
