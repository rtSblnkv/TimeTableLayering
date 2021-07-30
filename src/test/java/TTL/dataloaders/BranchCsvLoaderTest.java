package TTL.dataloaders;

import TTL.exception_handlers.UploadDataException;
import TTL.models.Branch;
import TTL.models.Edge;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class BranchCsvLoaderTest {

    @Test
    public void csvToList() {
        CsvLoader loader = new BranchCsvLoader();
        List<Branch> branchesActual = loader.csvToList("C:\\Users\\роппг\\IdeaProjects\\TimeTableLayering\\src\\main\\resources\\branchesTest.csv");

        Branch branch = new Branch();
        branch.setBranchCode("NS");
        branch.setBranchName("Nickolson");
        branch.setLatitude(-37.7738026);
        branch.setLongtitude(144.9836466);
        List<Branch> branchesExpected = new ArrayList<>(List.of(branch));

        Assert.assertEquals(branchesExpected,branchesActual);
    }

    @Test(expected = UploadDataException.class)
    public void csvToListCatchUploadDataException() {
        CsvLoader loader = new BranchCsvLoader();
        List<Branch> branches = loader.csvToList("C:\\Users\\роппг\\IdeaProjects\\TimeTableLayering\\src\\main\\resources\\br.csv");

    }

}