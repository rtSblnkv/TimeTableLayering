package TTL.models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * edges.csv Data Object
 */
@ToString(exclude = {"speedLimit","street_type","dist_on_limit"})
@EqualsAndHashCode(exclude = {"speedLimit","street_type","dist_on_limit"})
@NoArgsConstructor
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

    @CsvIgnore
    private int street_type;

    @CsvIgnore
    private double dist_on_limit;

}
