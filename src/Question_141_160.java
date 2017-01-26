import java.util.HashMap;
import java.util.Map;

public class Question_141_160 {

    /**
     * 156. Binary Tree Upside Down.
     * @param root
     * @return
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return root;
        }

        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;

        return newRoot;
    }

    /**
     * 159. Longest Substring with At Most Two Distinct Characters.
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> found = new HashMap<>();
        int max = 0;
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            char ch = s.charAt(right);
            if (!found.containsKey(ch) && found.size() == 2) {
                found.put(ch, 1);
                while (found.size() > 2) {
                    char leftChar = s.charAt(left);
                    if (found.get(leftChar) == 1) {
                        found.remove(leftChar);
                    } else {
                        found.put(leftChar, found.get(leftChar) - 1);
                    }
                    left++;
                }
            } else {
                if (found.containsKey(ch)) {
                    found.put(ch, found.get(ch) + 1);
                } else {
                    found.put(ch, 1);
                }
            }
            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }
}
