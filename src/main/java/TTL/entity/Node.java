package TTL.entity;

import java.io.Serializable;

public class Node implements Serializable {
    private int id;
    private double latitude;
    private double longtitude;

    public Node(){}

    public Node(int id,double latitude,double longtitude)
    {
        this.id = id;
        this.latitude = latitude ;
        this.longtitude = longtitude;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id=id;
    }

    public double getLongitude()
    {
        return longtitude;
    }

    public void setLongitude(double longtitude)
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



}
