package TTL.models;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Node implements Serializable,Comparable<Node> {
    @CsvBindByName(column="node")
    private long id;
    @CsvBindByName(column="lat")
    private double latitude;
    @CsvBindByName(column="lon")
    private double longtitude;

    private List<Edge> edges = new ArrayList<Edge>();

    private Node previousNode;

    private boolean visited;

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

    public Node getPreviousNode() { return previousNode;}

    public void setPreviousNode(Node previousNode) { this.previousNode = previousNode; }

    public boolean isVisited() { return visited; }

    public void setVisited(boolean visited) { this.visited = visited; }

    public double getMinDistance() { return minDistance; }

    public void setMinDistance(double minDistance) { this.minDistance = minDistance; }

    @Override
    public String toString() {
        /*return " id = " + id +
                ", latitude = " + latitude +
                ", longtitude = " + longtitude +
                ", visited = " + visited +
                ", minDistance = " + minDistance +
                ", edges = " + edges + "\n";*/
        return "id = "+ id + ", min = " + minDistance;
    }

    public int compareTo(Node secondNode)
    {
        return Double.compare(this.minDistance,secondNode.minDistance);
    }
}
