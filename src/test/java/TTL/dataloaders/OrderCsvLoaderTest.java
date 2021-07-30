package TTL.dataloaders;

import TTL.exception_handlers.UploadDataException;
import TTL.models.Edge;
import TTL.models.Node;
import TTL.models.Order;
import TTL.models.OrderItem;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderCsvLoaderTest {

    @Test
    public void csvToList() {
        CsvLoader loader = new OrderCsvLoader();
        List<Order> nodesActual = loader.csvToList("C:\\Users\\роппг\\IdeaProjects\\TimeTableLayering\\src\\main\\resources\\TestCsv\\ordersTest.csv");

        Date date = null;

        Order order = new Order();
        order.setOrderId("ORDC01406");

        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-07");
        }
        catch(ParseException ex)
        {
            ex.printStackTrace();
        }
        order.setDate(date);
        order.setOrderType("Lunch");
        order.setBranchCode("NS");
        order.setOrderPrice(140.8);
        order.setLatitude(-37.8125391);
        order.setLongtitude(144.954121);
        order.setHasLoyality(true);
        order.setDistanceTo(8.335);
        order.setDeliveryFee(13.7004279);

        OrderItem fries = new OrderItem();
        fries.setDish("Fries");
        fries.setCount(6);

        OrderItem salad = new OrderItem();
        salad.setDish("Salad");
        salad.setCount(4);

        List<OrderItem> orderItems = new ArrayList<>(List.of(fries,salad));

        order.setOrderItems(orderItems);


        List<Order> nodesExpected = new ArrayList<>(List.of(order));

        Assert.assertEquals(nodesExpected,nodesActual);
    }

    @Test(expected = UploadDataException.class)
    public void csvToListCatchUploadDataException() {
        CsvLoader loader = new NodeCsvLoader();
        List<Node> nodes = loader.csvToList("C");
    }
}