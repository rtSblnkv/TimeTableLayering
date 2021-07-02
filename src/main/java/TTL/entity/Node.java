package TTL.entity;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;

public class Node implements Serializable {
    @CsvBindByName(column="node")
    private long id;
    @CsvBindByName(column="lat")
    private double latitude;
    @CsvBindByName(column="lon")
    private double longtitude;

    public Node(){}

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

    @Override
    public String toString(){
        return "id: " + id +
                " latitude: " + latitude +
                " longtitude: " + longtitude;
    }
}
