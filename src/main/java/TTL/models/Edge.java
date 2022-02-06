package TTL.models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * edges.csv Data Object
 */
@Data
public class Edge implements Serializable {

    @CsvBindByName(column = "u")
    @Getter @Setter
    private long from;

    @CsvBindByName(column = "v")
    @Getter @Setter
    private long to;

    @CsvBindByName(column="distance(m)")
    @Getter @Setter
    private double distance;

    @CsvBindByName(column="speed(km/h)")
    @Getter @Setter
    private double speedLimit;

    @CsvBindByName(column="street_type")
    @Getter @Setter
    private int streetType; // 1 - общ 2 - ограничение для грузовиков

    @CsvIgnore
    private int trafficJamPoint;

    @CsvIgnore
    private boolean isAvailable;

    @CsvIgnore
    private double dist_on_limit;

}
