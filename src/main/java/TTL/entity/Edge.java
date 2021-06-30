package TTL.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvIgnore;

import java.io.Serializable;

public class Edge implements Serializable {

    @CsvBindByName(column = "u")
    private long from;

    @CsvBindByName(column = "v")
    private long to;

    @CsvBindByName(column="distance(m)")
    private double distance;

    @CsvBindByName(column="speed(km/h)")
    private double speedLimit;

    @CsvIgnore
    private int street_type;

    @CsvIgnore
    private double dist_on_limit;


    public Edge(){ }

    public long getFrom() { return from; }

    public void setFrom(long from) { this.from = from; }

    public long getTo() { return to; }

    public void setTo(long to) { this.to = to; }

    public void setTo(int to) { this.to = to; }

    public double getDistance() { return distance; }

    public void setDistance(double distance) { this.distance = distance; }

    public double getSpeedLimit() { return speedLimit;}

    public void setSpeedLimit(int speedLimit) { this.speedLimit = speedLimit; }

    public double getDist_on_limit() { return dist_on_limit; }

    public void setDist_on_limit(double dist_on_limit) { this.dist_on_limit = dist_on_limit;}

    @Override
    public String toString() {
        return "from: " + from +
                ", to: " + to +
                ", distance: " + distance +
                ", speedLimit: " + speedLimit;
    }
}
