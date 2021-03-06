import com.sun.source.tree.Tree;

import java.util.*;

public class Question_221_240 {

    /**
     * 221. Maximal Square.
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        int max = 0;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                max = 1;
            }
        }
        for (int j = 0; j < cols; j++) {
            if (matrix[0][j] == '1') {
                dp[0][j] = 1;
                max = 1;
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }

    /**
     * 222. Count Complete Tree Nodes.
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = 1;
        int rightDepth = 1;
        TreeNode cur = root;
        while (cur.left != null) {
            cur = cur.left;
            leftDepth++;
        }
        cur = root;
        while (cur.right != null) {
            cur = cur.right;
            rightDepth++;
        }
        if (leftDepth == rightDepth) {
            return healperCountNodes(leftDepth);
        } else {
            return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }

    private int healperCountNodes(int depth) {
        int base = 1;
        int count = 0;
        while (depth > 0) {
            count += base;
            base *= 2;
            depth--;
        }
        return count;
    }


    /**
     * 223. Rectangle Area.
     * @param A
     * @param B
     * @param C
     * @param D
     * @param E
     * @param F
     * @param G
     * @param H
     * @return
     */
    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C - A) * (D - B);
        int area2 = (G - E) * (H - F);
        int w1 = Math.max(A, E);
        int w2 = Math.min(C, G);
        long w = (long)w2 - w1;
        int h1 = Math.max(B, F);
        int h2 = Math.min(D, H);
        long h = (long)h2 - h1;
        long overlap = (w > 0 && h > 0) ? w * h : 0;
        return area1 + area2 - (int)overlap;
    }

    /**
     * 225. Implement Stack using Queues.
     */
    class MyStack {
        private Queue<Integer> mainQueue = new LinkedList<>();
        private Queue<Integer> viceQueue = new LinkedList<>();
        private Queue<Integer> tmp = new LinkedList<>();

        // Push element x onto stack.
        public void push(int x) {
            mainQueue.offer(x);
        }

        // Removes the element on top of the stack.
        public void pop() {
            while (mainQueue.size() > 1) {
                viceQueue.offer(mainQueue.poll());
            }
            mainQueue.poll();
            tmp = mainQueue;
            mainQueue = viceQueue;
            viceQueue = tmp;
        }

        // Get the top element.
        public int top() {
            while (mainQueue.size() > 1) {
                viceQueue.offer(mainQueue.poll());
            }
            int result = mainQueue.peek();
            viceQueue.offer(mainQueue.poll());
            tmp = mainQueue;
            mainQueue = viceQueue;
            viceQueue = tmp;
            return result;
        }

        // Return whether the stack is empty.
        public boolean empty() {
            return mainQueue.isEmpty();
        }
    }

    /**
     * 226. Invert Binary Tree.
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode tmp;
        tmp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tmp);
        return root;
    }

    /**
     * 228. Summary Ranges.
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int start = 0;
        int target = nums[0] + 1;
        for (int i = 1; i <= nums.length; i++) {
            if (i == nums.length) {
                if (start == i - 1) {
                    result.add(nums[start] + "");
                } else {
                    result.add(nums[start] + "->" + nums[i - 1]);
                }
                break;
            }
            if (nums[i] == target) {
                target = nums[i] + 1;
            } else {
                if (start == i - 1) {
                    result.add(nums[start] + "");
                } else {
                    result.add(nums[start] + "->" + nums[i - 1]);
                }
                start = i;
                target = nums[i] + 1;
            }
        }
        return result;
    }

    /**
     * 229. Majority Element II.
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        // Boyer-Moore：A Linear Time Majority Vote Alogrithm
        List<Integer> res = new ArrayList<>();
        int candidate1 = 0;
        int candidate2 = 1;
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            int candidate3 = nums[i];
            if (candidate1 == candidate3) {
                count1++;
            } else if (candidate2 == candidate3) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = candidate3;
                count1++;
            } else if (count2 == 0) {
                candidate2 = candidate3;
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int n : nums) {
            if (n == candidate1) {
                count1++;
            }
            if (n == candidate2) {
                count2++;
            }
        }
        if (count1 > nums.length / 3) {
            res.add(candidate1);
        }
        if (count2 > nums.length / 3) {
            res.add(candidate2);
        }
        return res;
    }

    /**
     * 231. Power of Two.
     * @param n
     * @return
     */
    public static boolean isPowerOfTwo(int n) {
        int mask = 1;
        int count = 0;
        for (int i = 0; i < 31; i++) {
            count += n & mask;
            if (count > 1) {
                return false;
            }
            n = n >> 1;
        }
        if ((n & mask) == 1) {
            return false;
        }
        return count == 1;
    }

    /**
     * 232. Implement Queue using Stacks.
     */
    class MyQueue {
        Stack<Integer> mainStack = new Stack<>();
        Stack<Integer> viceStack = new Stack<>();
        Stack<Integer> tmp = new Stack<>();

        // Push element x to the back of queue.
        public void push(int x) {
            mainStack.push(x);
        }

        // Removes the element from in front of queue.
        public void pop() {
            while (mainStack.size() > 1) {
                viceStack.push(mainStack.pop());
            }
            mainStack.pop();
            while (!viceStack.isEmpty()) {
                mainStack.push(viceStack.pop());
            }
        }

        // Get the front element.
        public int peek() {
            while (mainStack.size() > 1) {
                viceStack.push(mainStack.pop());
            }
            int result = mainStack.peek();
            viceStack.push(mainStack.pop());
            while (!viceStack.isEmpty()) {
                mainStack.push(viceStack.pop());
            }
            return result;
        }

        // Return whether the queue is empty.
        public boolean empty() {
            return mainStack.size() == 0;
        }
    }

    /**
     * 235. Lowest Common Ancestor of a Binary Search Tree.
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode BSTLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode left = null;
        TreeNode right = null;
        if (p.val > q.val) {
            TreeNode tmp = p;
            p = q;
            q = tmp;
        }
        while (root != null) {
            if (root == p) {
                left = p;
                right = BSTSearch(root.right, q);
                break;
            }
            if (root == q) {
                right = q;
                left = BSTSearch(root.left, p);
                break;
            }
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                left = BSTSearch(root.left, p);
                right = BSTSearch(root.right, q);
                break;
            }
        }
        if (left != null && right != null) {
            return root;
        } else {
            return null;
        }
    }

    private TreeNode BSTSearch(TreeNode root, TreeNode n) {
        if (root == null) {
            return null;
        }
        while (root != null) {
            if (root == n) {
                return root;
            } else if (n.val < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return n;
    }

    /**
     * 236. Lowest Common Ancestor of a Binary Tree.
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        HelperNode res = helperLowestCommonAncestor(root, p, q);
        if (res.foundP && res.foundQ) {
            return res.node;
        } else {
            return null;
        }
    }

    /**
     * return HelperNode with common ancestor as node;
     * @param root
     * @param p
     * @param q
     * @return
     */
    private HelperNode helperLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new HelperNode(false, false, null);
        }

        HelperNode leftRes = helperLowestCommonAncestor(root.left, p, q);
        HelperNode rightRes = helperLowestCommonAncestor(root.right, p, q);

        boolean foundP = leftRes.foundP || rightRes.foundP || root == p;
        boolean foundQ = leftRes.foundQ || rightRes.foundQ || root == q;

        if (root == p || root == q) {
            return new HelperNode(foundP, foundQ, root);
        }
        if (leftRes.node != null && rightRes.node != null) {
            return new HelperNode(foundP, foundQ, root);
        }
        if (leftRes.node != null) {
            return new HelperNode(foundP, foundQ, leftRes.node);
        }
        if (rightRes.node != null) {
            return new HelperNode(foundP, foundQ, rightRes.node);
        }
        return new HelperNode(foundP, foundQ, null);
    }


    public class HelperNode {
        public boolean foundP;
        public boolean foundQ;
        public TreeNode node;
        public HelperNode(boolean p, boolean q, TreeNode n) {
            foundP = p;
            foundQ = q;
            node = n;
        }
    }

    /**
     * 238. Product of Array Except Self.
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, 1);
        for (int i = 1; i < res.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    /**
     * 239. Sliding Window Maximum.
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -(o1 - o2);
            }
        });
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - (k - 1)];
        int left = 0;
        for (int i = 0; i < k - 1; i++) {
            queue.offer(nums[i]);
        }
        while (left < nums.length - (k - 1)) {
            queue.add(nums[left + (k - 1)]);
            res[left] = queue.peek();
            queue.remove(nums[left]);
            left++;
        }
        return res;
    }

    /**
     * 240. Search a 2D Matrix II.
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int start = 0;
        int end = rows - 1;
        int startRow = rows;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid][cols - 1] == target) {
                return true;
            } else if (matrix[mid][cols - 1] < target) {
                start = mid + 1;
            } else {
                startRow = mid;
                end = mid - 1;
            }
        }
        if (matrix[start][cols - 1] == target) {
            return true;
        } else if (matrix[start][cols - 1] > target) {
            startRow = start;
        } else {
            startRow = end;
        }
        if (startRow == rows) {
            return false;
        }
        for (int i = startRow; i < rows; i++) {
            start = 0;
            end = cols - 1;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (matrix[i][mid] == target) {
                    return true;
                } else if (matrix[i][mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(computeArea(-1500000001, 0, -1500000000, 1, 1500000000, 0, 1500000001, 1));
//        System.out.println(isPowerOfTwo(-2147483648));
    }
}
