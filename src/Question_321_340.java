import java.util.HashMap;
import java.util.Map;

public class Question_321_340 {

    /**
     * 337. House Robber III.
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        Map<TreeNode, Integer> visited = new HashMap<>();
        return rob(root, visited);
    }

    private int rob(TreeNode root, Map<TreeNode, Integer> visited) {
        // Base case;
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        if (visited.containsKey(root)) {
            return visited.get(root);
        }
        int child = 0;
        int subchild = 0;
        if (root.left != null) {
            child += rob(root.left, visited);
            subchild += rob(root.left.left, visited) + rob(root.left.right, visited);
        }
        if (root.right != null) {
            child += rob(root.right, visited);
            subchild += rob(root.right.left, visited) + rob(root.right.right, visited);
        }
        int val = Math.max(child, subchild + root.val);
        visited.put(root, val);
        return val;
    }

    /**
     * 339. Nested List Weight Sum.
     * @param nestedList
     * @return
     */
//    public int depthSum(List<NestedInteger> nestedList) {
//        int level = 1;
//        return depthSum(nestedList, level);
//    }
//
//    private int depthSum(List<NestedInteger> nestedList, int level) {
//        int sum = 0;
//        for (NestedInteger n : nestedList) {
//            if (n.isInteger()) {
//                sum += n.getInteger() * level;
//            } else {
//                sum += depthSum(n.getList(), level + 1);
//            }
//        }
//        return sum;
//    }

}
