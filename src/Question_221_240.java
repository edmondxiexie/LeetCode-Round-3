import com.sun.tools.javac.util.ListBuffer;

import java.util.*;

public class Question_221_240 {

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

    public static void main(String[] args) {
//        System.out.println(computeArea(-1500000001, 0, -1500000000, 1, 1500000000, 0, 1500000001, 1));
        System.out.println(isPowerOfTwo(-2147483648));
    }
}
