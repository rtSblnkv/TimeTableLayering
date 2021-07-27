package TTL.models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvIgnore;

import java.io.Serializable;
import java.util.Objects;

/**
 * edges.csv Data Object
 */
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

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public double getDistance() { return distance; }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getSpeedLimit() { return speedLimit; }

    public void setSpeedLimit(int speedLimit) { this.speedLimit = speedLimit; }

    @Override
    public String toString() {
        return "from: " + from +
                ", to: " + to +
                ", distance: " + distance +"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return from == edge.from &&
                to == edge.to &&
                Double.compare(edge.distance, distance) == 0 &&
                Double.compare(edge.speedLimit, speedLimit) == 0 &&
                street_type == edge.street_type &&
                Double.compare(edge.dist_on_limit, dist_on_limit) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, distance, speedLimit, street_type, dist_on_limit);
    }
}
