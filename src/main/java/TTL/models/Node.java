package TTL.models;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * nodes.csv Data Object
 */
@EqualsAndHashCode(exclude = {"visited","previousNode","epsilon","minDistance"})
public class Node implements Serializable,Comparable<Node>,Cloneable {

    @CsvBindByName(column="node")
    private long id;

    @CsvBindByName(column="lat")
    private double latitude;

    @CsvBindByName(column="lon")
    private double longtitude;

    private List<Edge> edges = new ArrayList<>();

    private Node previousNode;

    private boolean visited;

    private double epsilon;

    private double minDistance = Double.MAX_VALUE;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    public Node() {
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    @Override
    public String toString() {
        return  latitude +
                "," + longtitude ;
    }

    public int compareTo(Node secondNode) {
        return Double.compare(this.minDistance,secondNode.minDistance);
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
