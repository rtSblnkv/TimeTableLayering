package TTL.dataloaders;

import TTL.models.Branch;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class BranchCsvLoader implements CsvLoader {
    /**
     * Uploads data from branches.csv
     * @param path - path to branches.csv in resources directory
     * @return List of Branch type
     */
    @Override
    public List<Branch> csvToList(String path) {
        List<Branch> data = null;
        try(FileReader reader = new FileReader(path)){
            data = new CsvToBeanBuilder(reader)
                    .withType(Branch.class)
                    .build()
                    .parse();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        return data;
    }

}