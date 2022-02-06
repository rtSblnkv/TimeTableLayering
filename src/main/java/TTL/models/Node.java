package TTL.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByName;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * nodes.csv Data Object
 */

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
public class Node implements Serializable,Cloneable {

    @CsvBindByName(column="node")
    private long id;

    @CsvBindByName(column="lat")
    private double latitude;

    @CsvBindByName(column="lon")
    private double longtitude;

    @Override
    public String toString() {
        return  "[" + latitude +
                "," + longtitude + "]";
    }

    public Node clone()
    {
        Node node = new Node();
        node.id = id;
        node.longtitude = longtitude;
        node.latitude = latitude;
        return node;

    }
}
