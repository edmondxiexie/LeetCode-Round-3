import java.util.ArrayList;
import java.util.List;

public class Question_361_380 {

    /**
     * 366. Find Leaves of Binary Tree.
     * @param root
     * @return
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        DFSFindLeaves(root, result);
        return result;
    }

    private int DFSFindLeaves(TreeNode root, List<List<Integer>> result) {
        if (root == null) {
            return -1;
        }
        int leftDepth = DFSFindLeaves(root.left, result);
        int rightDepth = DFSFindLeaves(root.right, result);
        int depth = 1 + Math.max(leftDepth, rightDepth);
        if (depth >= result.size()) {
            result.add(new ArrayList<>());
        }
        result.get(depth).add(root.val);
        return depth;
    }
}
