package TTL.controllers.layers;

import TTL.models.Order;

import java.util.HashMap;
import java.util.List;

public abstract class Layers {

    private HashMap<String,List<Order>> layers;

    public Layers() {
    }

    public HashMap<String,List<Order>> getLayers() {
        return layers;
    }

    public abstract void getSplitter();
    public abstract void splitOrderOnLayersBySplitter();

}
