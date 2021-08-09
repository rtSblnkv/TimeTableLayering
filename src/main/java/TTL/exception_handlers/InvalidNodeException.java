package TTL.exception_handlers;

import lombok.Getter;

/**
 * Incorrect node exception handler
 * Thrown when can't find node with
 * that coordinates or id in list of nodes
 * params - latitude and longtitude of node which is not find
 */
@Getter
public class InvalidNodeException extends RuntimeException {

    private final double lat;
    private final double lon;

    public InvalidNodeException(double lat, double lon, String errMessage) {
        super(errMessage);
        this.lat = lat;
        this.lon = lon;
    }
}
