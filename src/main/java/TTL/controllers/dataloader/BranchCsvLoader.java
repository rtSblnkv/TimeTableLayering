package TTL.controllers.dataloader;

import TTL.models.Branch;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class BranchCsvLoader implements CsvLoader {
    @Override
    public List<Branch> csvToList(String path) {
        List<Branch> data = null;
        try{
            data = new CsvToBeanBuilder(new FileReader(path))
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
