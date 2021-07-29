package TTL.exception_handlers;

public class InvalidNodeException extends RuntimeException {

    private double lat;
    private double lon;

    public InvalidNodeException(double lat,double lon,String errMessage)
    {
        super(errMessage);
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

}
