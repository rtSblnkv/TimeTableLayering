package TTL.models;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

import java.io.Serializable;

/**
 * branches.csv Data Object
 */
@NoArgsConstructor
@Getter @Setter
@ToString(exclude = "branchName")
@EqualsAndHashCode
public class Branch implements Serializable {

    @CsvBindByName(column ="branch_code")
    private String branchCode;

    @CsvBindByName(column = "branch_lat")
    private double latitude;

    @CsvBindByName(column = "branch_lon")
    private double longtitude;

    @CsvBindByName(column ="branch_name")
    private String branchName;

}
