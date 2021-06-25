package TTL.entity;

import java.io.Serializable;

public class Branch implements Serializable {
    private Node location;
    private String branch_code;

    public Branch() {}

    public Branch(Node location, String branch_code) {
        this.location = location;
        this.branch_code = branch_code;
    }

    public Node getLocation() {
        return location;
    }

    public void setLocation(Node location) {
        this.location = location;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }
}
