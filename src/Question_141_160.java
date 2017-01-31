import java.util.*;

public class Question_141_160 {

    /**
     * 145. Binary Tree Postorder Traversal.
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        Map<TreeNode, Boolean> finished = new HashMap<>();
        if (root == null) {
            return result;
        }
        stack.push(root);
        finished.put(root, false);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            if (finished.get(cur)) {
                result.add(stack.pop().val);
            } else {
                finished.put(cur, true);
                if (cur.right != null) {
                    stack.push(cur.right);
                    finished.put(cur.right, false);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                    finished.put(cur.left, false);
                }
            }
        }
        return result;
    }

    /**
     * 149. Max Points on a Line.
     * @param points
     * @return
     */
    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < points.length; i++) {
            int same = 1;
            int vertical = 0;
            int horizontal = 0;
            int curMax = 0;
            Map<Double, Integer> count = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    same++;
                } else if (points[i].x == points[j].x) {
                    vertical++;
                } else if (points[i].y == points[j].y) {
                    horizontal++;
                } else {
                    double k = (double) (points[j].y - points[i].y) / (points[j].x - points[i].x);
                    if (!count.containsKey(k)) {
                        count.put(k, 1);
                    } else {
                        count.put(k, count.get(k) + 1);
                    }
                    curMax = Math.max(curMax, count.get(k));
                }
            }
            max = Math.max(max, Math.max(curMax, vertical) + same);
            max = Math.max(max, horizontal + same);
        }
        return max;
    }

    class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }

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

    public void test() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(5, 3);
        Point p3 = new Point(-3, 3);

        Point[] nums = {p1, p2, p3};
        System.out.println(maxPoints(nums));
    }

    public int getRandom() {
        Random rand = new Random();
        int index = rand.nextInt(1);
        return index;
    }

    public static void main(String[] args) {
        Question_141_160 q = new Question_141_160();
        q.test();
        for (int i = 0; i < 10; i++) {
            System.out.println(q.getRandom());
        }
    }
}
