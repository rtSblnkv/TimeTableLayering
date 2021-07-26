package TTL.controllers.listWorkers;

import TTL.models.Branch;
import TTL.models.Node;

import java.util.HashMap;
import java.util.List;

public class BranchWorker implements Worker{

    private List<Branch> branches;

    /**
     * Empty constructor
     */
    public BranchWorker(){}

    /**
     * Constructor.Initializes branches
     * @param branches - List of branches
     */
    public BranchWorker(List<Branch> branches) {
        this.branches = branches;
    }

    /**
     * Sets branches
     * @param branches - List of branches
     */
    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    /**
     * Converts List of branches into HashMap
     * @param nw - handler of nodes list
     * @return HashMap of ranch and its computed Node
     */
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

    /**
     * Converts List of branches into HashMap (BranchCode, Branch)
     * @return HashMap (BranchCode, Branch)
     */
    @Override
    public HashMap<String, Branch> toHashMap() {
        HashMap<String,Branch> branchesHashMap = new HashMap<>();
        branches.forEach(branch -> branchesHashMap.put(branch.getBranchCode(),branch));
        return branchesHashMap;
    }
}
