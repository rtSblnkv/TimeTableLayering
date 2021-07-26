package TTL.models;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node implements Serializable,Comparable<Node>,Cloneable {
    @CsvBindByName(column="node")
    private long id;
    @CsvBindByName(column="lat")
    private double latitude;
    @CsvBindByName(column="lon")
    private double longtitude;

    private List<Edge> edges = new ArrayList<Edge>();

    private Node previousNode;

    private boolean visited;

    private double epsilon;

    private double minDistance = Double.MAX_VALUE;


    public Node(){}

    public void addEdge(Edge edge)
    {
        edges.add(edge);
    }

    public long getId() { return id; }

    public void setId(long id)
    {
        this.id=id;
    }

    public double getLongtitude()
    {
        return longtitude;
    }

    public void setLongtitude(double longtitude)
    {
        this.longtitude = longtitude;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude  = latitude ;
    }

    public List<Edge> getEdges() { return edges; }

    public void setEdges(List<Edge> edges) { this.edges = edges; }

    public double getEpsilon() { return epsilon; }

    public void setEpsilon(double epsilon) { this.epsilon = epsilon; }

    public Node getPreviousNode() { return previousNode;}

    public void setPreviousNode(Node previousNode) { this.previousNode = previousNode; }

    public boolean isVisited() { return visited; }

    public void setVisited(boolean visited) { this.visited = visited; }

    public double getMinDistance() { return minDistance; }

    public void setMinDistance(double minDistance) { this.minDistance = minDistance; }

    @Override
    public String toString() {
        return "[" + latitude +
                "," + longtitude + "] ";
    }

    public int compareTo(Node secondNode)
    {
        return Double.compare(this.minDistance,secondNode.minDistance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id &&
                Double.compare(node.latitude, latitude) == 0 &&
                Double.compare(node.longtitude, longtitude) == 0 &&
                visited == node.visited &&
                Double.compare(node.epsilon, epsilon) == 0 &&
                Double.compare(node.minDistance, minDistance) == 0 &&
                Objects.equals(edges, node.edges) &&
                Objects.equals(previousNode, node.previousNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitude, longtitude, edges, previousNode, visited, epsilon, minDistance);
    }

    @Override
    public Node clone()
    {
        Node node = new Node();
        node.setId(id);
        node.setLongtitude(longtitude);
        node.setLatitude(latitude);
        return node;

    }
}
