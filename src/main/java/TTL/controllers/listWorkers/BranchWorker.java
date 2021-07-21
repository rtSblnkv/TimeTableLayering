package TTL.controllers.listWorkers;

import TTL.models.Branch;
import TTL.models.Node;

import java.util.HashMap;
import java.util.List;

public class BranchWorker implements Worker{

    private List<Branch> branches;

    public BranchWorker(){}

    public BranchWorker(List<Branch> branches) {
        this.branches = branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public HashMap<String, Node> toBranchNodeHashMap(NodeWorker nw)
    {
        HashMap<String, Node> branchNodes = new HashMap<>();
        branches.forEach(branch ->
        {
            Node branchNode = nw.getNodeByCoordinates(branch.getLatitude(),branch.getLongtitude());
            branchNodes.put(branch.getBranchCode(),branchNode);
        });
        return branchNodes;
    }

    @Override
    public HashMap<String, Branch> toHashMap() {
        HashMap<String,Branch> branchesHashMap = new HashMap<>();
        branches.forEach(branch -> branchesHashMap.put(branch.getBranchCode(),branch));
        return branchesHashMap;
    }
}
