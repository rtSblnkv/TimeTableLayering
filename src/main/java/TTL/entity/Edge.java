package TTL.entity;

import java.io.Serializable;

public class Edge implements Serializable {
    private Node from;
    private Node to;
    private double distance;
    private int speed_limit;

    public Edge(){ }

    public Edge(Node from, Node to, double distance, int speed_limit) {
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.speed_limit = speed_limit;
    }

    public Node getFrom() {
        return from;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public Node getTo() {
        return to;
    }

    public void setTo(Node to) {
        this.to = to;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getSpeed_limit() {
        return speed_limit;
    }

    public void setSpeed_limit(int speed_limit) {
        this.speed_limit = speed_limit;
    }

}
