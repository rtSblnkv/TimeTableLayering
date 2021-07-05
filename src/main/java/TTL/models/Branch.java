package TTL.models;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;

public class Branch implements Serializable {

    @CsvBindByName(column = "lon")
    private double longtitude;

    @CsvBindByName(column = "lat")
    private double latitude;

    private long nodeId;

    @CsvBindByName(column ="branch_code")
    private String branchCode;

    @CsvBindByName(column ="branch_name")
    private String branchName;

    public Branch() {}

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public double getLongtitude() { return longtitude; }

    public void setLongtitude(double longtitude) { this.longtitude = longtitude; }

    public double getLatitude() { return latitude; }

    public void setLatitude(double latitude) { this.latitude = latitude; }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() { return branchName; }

    public void setBranchName(String branchName) { this.branchName = branchName; }

    @Override
    public String toString()
    {
        return "Branch code: " + branchCode +
                ", name:" + branchName +
                "location: " + latitude + "." + longtitude;
    }

}
