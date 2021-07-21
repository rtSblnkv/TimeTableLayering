package TTL.controllers.layers;

import TTL.controllers.listWorkers.NodeWorker;
import TTL.models.Node;
import TTL.models.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Layers {

    private List<Order> orders;
    private HashMap<String,List<Order>> layers;
    private ArrayList<String> splitters;


    public Layers() { }

    public Layers(List<Order> orders) {

        this.orders = orders;
        splitters = new ArrayList<>();
        layers = new HashMap<>();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public HashMap<String, List<Order>> getLayers() {
        return layers;
    }

    public ArrayList<String> getSplitters() {
        return splitters;
    }

    public void setLayers(HashMap<String, List<Order>> layers) {
        this.layers = layers;
    }

    public void setSplitters(ArrayList<String> splitters) {
        this.splitters = splitters;
    }


    private static List<Node> getListOfOrderNodes(List<Order> ordersGroupedBySplitter,List<Node> nodes)
    {
        NodeWorker nodeWorker = new NodeWorker(nodes);

        return ordersGroupedBySplitter
                .stream()
                .map(order -> nodeWorker.getNodeByCoordinates(order.getLatitude(),order.getLongtitude()))
                .filter(order -> order.getLatitude() != 0)
                .collect(Collectors.toList());
    }

    public abstract void setSplitter();
    public abstract void splitOnLayers();

}
