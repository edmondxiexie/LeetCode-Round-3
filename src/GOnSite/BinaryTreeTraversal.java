package GOnSite;

import java.util.*;

/**
 * Created by Edmond on 10/3/17.
 */
public class BinaryTreeTraversal {

    /**
     * ### In-order Traversal Recursion
     */
    public List<Integer> inorderRecursion(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderRecursion(root, result);
        return result;
    }

    private void inorderRecursion(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorderRecursion(root.left, result);
        result.add(root.val);
        inorderRecursion(root.right, result);
    }

    /**
     * ### In-order Traversal Iteration
     */
    public List<Integer> inorderIteration(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            result.add(cur.val);
            cur = cur.right;
        }
        return result;
    }

    /**
     * ### Pre-order Traversal Recursion
     */
    public List<Integer> preorderRecursion(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderRecursion(root, result);
        return result;
    }

    private void preorderRecursion(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorderRecursion(root.left, result);
        preorderRecursion(root.right, result);
    }

    /**
     * ### Pre-order Traversal Iteration
     */
    public List<Integer> preorderIteration(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return result;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            result.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return result;
    }

    /**
     * ### Post-order Traversal Recursion
     */
    public List<Integer> postorderRecursion(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderRecursion(root, result);
        return result;
    }

    private void postorderRecursion(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        postorderRecursion(root.left, result);
        postorderRecursion(root.right, result);
        result.add(root.val);
    }

    /**
     * ### Post-order Traversal Iteration
     */
    public List<Integer> postorderIteration(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> visited = new HashSet<>();
        if (root == null) {
            return result;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            if (visited.contains(cur)) {
                cur = stack.pop();
                result.add(cur.val);
            } else {
                visited.add(cur);
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
        return result;
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        BinaryTreeTraversal b = new BinaryTreeTraversal();

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);

        n2.right = n3;
        n2.left = n1;
        n3.right = n4;

        System.out.println(b.inorderRecursion(n2).toString());
        System.out.println(b.inorderIteration(n2).toString());
        System.out.println();

        System.out.println(b.preorderRecursion(n2).toString());
        System.out.println(b.preorderIteration(n2).toString());
        System.out.println();

        System.out.println(b.postorderRecursion(n2).toString());
        System.out.println(b.postorderIteration(n2).toString());


    }
}
