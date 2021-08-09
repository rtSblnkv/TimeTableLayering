package TTL.models;

import com.opencsv.bean.CsvBindByName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * nodes.csv Data Object
 */

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"edges", "visited", "previousNode", "epsilon", "minDistance", "id"})
public class Node implements Serializable, Comparable<Node>, Cloneable {

    @CsvBindByName(column = "node")
    private long id;

    @CsvBindByName(column = "lat")
    private double latitude;

    @CsvBindByName(column = "lon")
    private double longtitude;

    private List<Edge> edges = new ArrayList<>();

    private Node previousNode;

    private boolean visited;

    private double epsilon;

    private double minDistance = Double.MAX_VALUE;

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    @Override
    public String toString() {
        return latitude +
                "," + longtitude;
    }

    public int compareTo(Node secondNode) {
        return Double.compare(this.minDistance, secondNode.minDistance);
    }

    public Node clone() {
        Node node = new Node();
        node.id = id;
        node.longtitude = longtitude;
        node.latitude = latitude;
        return node;

    }
}
