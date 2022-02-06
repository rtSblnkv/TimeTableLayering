package TTL.controllers.layers;

import TTL.models.Order;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
public abstract class Layers {

    private List<Order> orders;

    private HashMap<String,List<Order>> layers;

    private ArrayList<String> splitters;

    Layers(List<Order> orders) {
        this.orders = orders;
        splitters = new ArrayList<>();
        layers = new HashMap<>();
    }

    public abstract void setSplitter();
    public abstract void splitOnLayers();
}
