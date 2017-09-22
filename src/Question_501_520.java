import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Edmond on 8/22/17.
 */
public class Question_501_520 {

    /**
     * 508. Most Frequent Subtree Sum.
     * @param root
     * @return
     */
    private int max = 1;

    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        getSum(root, map, list);
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public int getSum(TreeNode root, Map<Integer, Integer> map, List<Integer> list) {
        if (root == null) {
            return 0;
        }
        int leftRes = getSum(root.left, map, list);
        int rightRes = getSum(root.right, map, list);
        int sum = root.val + leftRes + rightRes;
        if (!map.containsKey(sum)) {
            map.put(sum, 1);
        } else {
            map.put(sum, map.get(sum) + 1);
        }
        if (list.isEmpty() || map.get(sum) > max) {
            list.clear();
            list.add(sum);
            max = map.get(sum);
        } else if (map.get(sum) == max) {
            list.add(sum);
        }
        return sum;
    }

    public static void main(String[] args) {
        Question_501_520 q = new Question_501_520();
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(-5);
        n1.left = n2;
        n1.right = n3;
        q.findFrequentTreeSum(n1);
    }
}
