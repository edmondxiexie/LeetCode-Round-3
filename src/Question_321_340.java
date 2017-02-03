import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Question_321_340 {

    /**
     * 321. Create Maximum Number.
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        for (int i = Math.max(k - nums2.length, 0); i <= Math.min(nums1.length, k); i++) {
            int[] res1 = get_max_sub_array(nums1, i);
            int[] res2 = get_max_sub_array(nums2, k - i);
            int[] res = new int[k];
            int pos1 = 0, pos2 = 0, tpos = 0;

            while (pos1 < res1.length || pos2 < res2.length) {
                res[tpos++] = greater(res1, pos1, res2, pos2) ? res1[pos1++] : res2[pos2++];
            }

            if (!greater(ans, 0, res, 0))
                ans = res;
        }
        return ans;
    }

    public boolean greater(int[] nums1, int start1, int[] nums2, int start2) {
        for (; start1 < nums1.length && start2 < nums2.length; start1++, start2++) {
            if (nums1[start1] > nums2[start2]) {
                return true;
            }
            if (nums1[start1] < nums2[start2]) {
                return false;
            }
        }
        return start1 != nums1.length;
    }

    public int[] get_max_sub_array(int[] nums, int k) {
        int[] res = new int[k];
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            while (len > 0 && len + nums.length - i > k && res[len - 1] < nums[i]) {
                len--;
            }
            if (len < k) {
                res[len] = nums[i];
                len++;
            }
        }
        return res;
    }

    /**
     * 322. Coin Change.
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int dp[] = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] != Integer.MAX_VALUE && i + coins[j] <= amount && dp[i] != Integer.MAX_VALUE) {
                    dp[i + coins[j]] = Math.min(dp[i + coins[j]], dp[i] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

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
     * 334. Increasing Triplet Subsequence.
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    if (dp[i] >= 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

//    /**
//     * 339. Nested List Weight Sum.
//     * @param nestedList
//     * @return
//     */
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
    public static void main(String[] args) {
        Question_321_340 c = new Question_321_340();
        int[] nums = {1,2,5,10};
        System.out.println(c.coinChange(nums, 48));
    }

}
