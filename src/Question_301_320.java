import java.net.SocketPermission;
import java.util.*;

/**
 * Created by Edmond on 12/23/16.
 */
public class Question_301_320 {
    public class NumArray {

        private int[] sums;
        private int[] nums;

        public NumArray(int[] nums) {
            sums = new int[nums.length];
            this.nums = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                this.nums[i] = nums[i];
                if (i == 0) {
                    sums[i] = nums[i];
                } else {
                    sums[i] = sums[i - 1] + nums[i];
                }
            }
        }

        public int sumRange(int i, int j) {
            return sums[j] - sums[i] + nums[i];
        }
    }

    /**
     * 306. Additive Number.
     * @param num
     * @return
     */
    public boolean isAdditiveNumber(String num) {
        for (int i = 1; i <= num.length() / 2 + 1; i++) {
            for (int j = i + 1; j <= num.length(); j++) {
                String str1 = num.substring(0, i);
                String str2 = num.substring(i, j);
                String str3 = strAdd(str1, str2);
                if (num.substring(j).startsWith(str3)) {
                    if (DFSIsAdditiveNumber(i, j, num)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean DFSIsAdditiveNumber(int i, int j, String num) {
        if (num.length() == j) {
            return true;
        }
        String str1 = num.substring(0, i);
        String str2 = num.substring(i, j);
        if (str1.startsWith("0") && !str1.equals("0")
                || str2.startsWith("0") && !str2.equals("0")) {
            return false;
        }

        String str3 = strAdd(str1, str2);
        if (num.substring(j).startsWith(str3)) {
            num = num.substring(i);
            int len = i;
            i = j - len;
            j = j + str3.length() - len;
            if (DFSIsAdditiveNumber(i, j, num)) {
                return true;
            }
        }
        return false;
    }

    public String strAdd(String n1, String n2) {
        StringBuilder res = new StringBuilder();
        int i1 = n1.length() - 1;
        int i2 = n2.length() - 1;
        int carry = 0;
        while (i1 >= 0 || i2 >= 0 || carry > 0) {
            if (i1 < 0 && i2 < 0) {
                res.append(carry % 10);
                carry = carry / 10;
            } else if (i1 < 0) {
                int num = n2.charAt(i2) - '0' + carry;
                res.append(num % 10);
                carry = num / 10;
                i2--;
            } else if (i2 < 0) {
                int num = n1.charAt(i1) - '0' + carry;
                res.append(num % 10);
                carry = num / 10;
                i1--;
            } else {
                int num = n1.charAt(i1) - '0' + n2.charAt(i2) - '0' + carry;
                res.append(num % 10);
                carry = num / 10;
                i1--;
                i2--;
            }
        }
        return res.reverse().toString();
    }

    /**
     * 311. Sparse Matrix Multiplication.
     * @param A
     * @param B
     * @return
     */
    public int[][] multiply(int[][] A, int[][] B) {
        if (A == null || B == null || A.length == 0 || A[0].length == 0 || B.length == 0 || B[0].length == 0) {
            return new int[][]{};
        }
        int rows = A.length;
        int cols = B[0].length;
        int[][] res = new int[rows][cols];
        Map<Integer, int[]> rowsInA = new HashMap<>();
        Map<Integer, int[]> colsInB = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] != 0) {
                    rowsInA.put(i, A[i]);
                    break;
                }
            }
        }

        for (int j = 0; j < B[0].length; j++) {
            for (int i = 0; i < B.length; i++) {
                if (B[i][j] != 0) {
                    int[] tmp = new int[B.length];
                    for (int k = 0; k < B.length; k++) {
                        tmp[k] = B[k][j];
                    }
                    colsInB.put(j, tmp);
                    break;
                }
            }
        }

        for (int i : rowsInA.keySet()) {
            for (int j : colsInB.keySet()) {
                for (int k = 0; k < A[0].length; k++) {
                    res[i][j] += rowsInA.get(i)[k] * colsInB.get(j)[k];
                }
            }
        }

        return res;
    }

    /**
     * 313. Super Ugly Number.
     * @param n
     * @param primes
     * @return
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        List<Integer> list = new ArrayList<>();
        list.add(1);
        int[] bases = new int[primes.length];
        while (list.size() < n) {
            int[] next = new int[primes.length];
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < primes.length; i++) {
                next[i] = list.get(bases[i]) * primes[i];
                min = Math.min(min, next[i]);
            }
            for (int i = 0; i < next.length; i++) {
                if (next[i] == min) {
                        if (min != list.get(list.size() - 1)) {
                            list.add(min);
                        }
                    bases[i]++;
                    break;
                }
            }
        }
        return list.get(list.size() - 1);
    }

    /**
     * 314. Binary Tree Vertical Order Traversal.
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
         class TreeColNode {
            public TreeNode treeNode;
            public int col;

            public TreeColNode(TreeNode treeNode, int col) {
                this.treeNode = treeNode;
                this.col = col;
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeColNode> queue = new LinkedList<TreeColNode>();
        queue.offer(new TreeColNode(root, 0));
        int min = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            TreeColNode curNode = queue.poll();
            int curCol = curNode.col;
            if (!map.containsKey(curCol)) {
                map.put(curCol, new ArrayList<>());
            }
            map.get(curCol).add(curNode.treeNode.val);

            if (curNode.treeNode.left != null) {
                queue.offer(new TreeColNode(curNode.treeNode.left, curCol - 1));
                min = Math.min(min, curCol - 1);
            }
            if (curNode.treeNode.right != null) {
                queue.offer(new TreeColNode(curNode.treeNode.right, curCol + 1));
                max = Math.max(max, curCol + 1);
            }
        }

        for (int i = min; i <= max; i++) {
            result.add(map.get(i));
        }

        return result;
    }

    /**
     * 318. Maximum Product of Word Lengths.
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        // elements[i] |= 1 << (words[i][j] – ‘a’);   //把words[i][j] 在26字母中的出现的次序变为1
        int[] letterAppeared = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                letterAppeared[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }

        int max = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((letterAppeared[i] & letterAppeared[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Question_301_320 c = new Question_301_320();
        System.out.println(c.isAdditiveNumber("198019823962"));
        int[] nums = {2, 7, 13, 19};
        System.out.println(c.nthSuperUglyNumber(12, nums));
    }
}
