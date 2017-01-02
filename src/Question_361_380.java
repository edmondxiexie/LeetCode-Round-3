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

    /**
     * 370. Range Addition.
     * @param length
     * @param updates
     * @return
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        for (int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int inc = update[2];
            res[start] += inc;
            if (end + 1 < length) {
                res[end + 1] -= inc;
            }
        }
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += res[i];
            res[i] = sum;
        }
        return res;
    }
}