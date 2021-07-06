package TTL.models;

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

    private double rangeTime;

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

    public double getRangeTime() { return rangeTime; }

    public void setRangeTime() {
        rangeTime = distance * 3.6 / speedLimit;
    }

    @Override
    public String toString() {
        return "from: " + from +
                ", to: " + to +
                ", distance: " + distance +
                ", rangeTime: " + rangeTime +
                ", speedLimit: " + speedLimit;
    }
}
