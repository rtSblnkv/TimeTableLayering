package TTL.utils;

import TTL.models.OrderItem;
import TTL.utils.TextToOrderItems;
import org.junit.Assert;
import org.junit.Test;

public class TextToOrderItemsTest {

    // На вход данному методу поступают [('Steak', 3   ;   'Chicken', 6 или 'Burger', 8)]

    @Test
    public void convertToRead() {
        TextToOrderItems ttoi = new TextToOrderItems();
        OrderItem oi1 = new OrderItem();
        oi1.setCount(3);
        oi1.setDish("Steak");

        OrderItem oi2 = new OrderItem();
        oi2.setCount(6);
        oi2.setDish("Chicken");

        OrderItem oi3 = new OrderItem();
        oi3.setCount(8);
        oi3.setDish("Burger");

        Assert.assertTrue(orderItemEquals(oi1,(OrderItem)ttoi.convertToRead("[('Steak', 3")));
        Assert.assertTrue(orderItemEquals(oi2,(OrderItem)ttoi.convertToRead("'Chicken', 6")));
        Assert.assertTrue(orderItemEquals(oi3,(OrderItem)ttoi.convertToRead("'Burger', 8)]")));
        Assert.assertTrue(orderItemEquals(oi3,(OrderItem)ttoi.convertToRead("Burger , 8 ")));
    }

    boolean orderItemEquals(OrderItem first,OrderItem second)
    {
        boolean dishEquals = first.getDish().equals(second.getDish());
        boolean countEquals = first.getCount() == second.getCount();
        return dishEquals && countEquals;
    }
}
