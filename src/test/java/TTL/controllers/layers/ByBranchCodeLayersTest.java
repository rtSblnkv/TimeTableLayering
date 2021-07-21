package TTL.controllers.layers;

import TTL.DijkstraRunner;
import TTL.models.Order;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ByBranchCodeLayersTest {

    @Test
    public void setSplitter() {
        List<String> splitters = Arrays.asList("NS","TP","BK");

        Order order1 = new Order();
        order1.setBranchCode("NS");

        Order order2 = new Order();
        order2.setBranchCode("TP");

        Order order3 = new Order();
        order3.setBranchCode("BK");

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);

        Collections.sort(splitters);

        ByBranchCodeLayers bbcl = new ByBranchCodeLayers(orders);

        List<String> progSplitters = bbcl.getSplitters();
        Collections.sort(progSplitters);

        Assert.assertEquals(splitters,progSplitters);
    }

    @Test
    public void splitOnLayers() {
    }
}