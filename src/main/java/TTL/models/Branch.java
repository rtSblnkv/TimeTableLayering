package TTL.models;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * branches.csv Data Object
 */
@ToString(exclude = "branchName")
@NoArgsConstructor
@Getter @Setter
public class Branch implements Serializable {

    @CsvBindByName(column ="branch_code")
    private String branchCode;

    @CsvBindByName(column = "lat")
    private double latitude;

    @CsvBindByName(column = "lon")
    private double longtitude;

    @CsvBindByName(column ="branch_name")
    private String branchName;

}
