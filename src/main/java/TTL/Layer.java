package TTL;

import TTL.models.Order;

import java.util.HashMap;
import java.util.List;

public abstract class Layer {

    private HashMap<String, List<?>> layers;
    private List<?> graph;

    public Layer() {
    }

    public HashMap<String, List<?>> getLayers() {
        return layers;
    }

    public void setLayers(HashMap<String, List<?>> layers) {
        this.layers = layers;
    }

    public List<?> getGraph() {
        return graph;
    }

    public void setGraph(List<?> graph) {
        this.graph = graph;
    }

    public abstract void createLayer(List<Order> orders);



}
