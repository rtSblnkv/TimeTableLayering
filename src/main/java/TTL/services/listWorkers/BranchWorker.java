package TTL.services.listWorkers;

import TTL.models.Branch;
import TTL.models.Node;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

/**
 * Class which operates with list of branches
 */
@NoArgsConstructor
@AllArgsConstructor
public class BranchWorker implements Worker{

    @Setter
    private List<Branch> branches;

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
