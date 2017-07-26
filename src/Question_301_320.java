import java.net.SocketPermission;
import java.util.*;

/**
 * Created by Edmond on 12/23/16.
 */
public class Question_301_320 {
    /**
     * 305. Number of Islands II
     */
    public List<Integer> numIslands2(int m, int n, Point[] operators) {
        List<Integer> result = new ArrayList<>();
        if (operators == null || operators.length == 0) {
            return result;
        }
        UnionFind uf = new UnionFind(n, m);
        int count = 0;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int[][] islands = new int[n][m];
        for (Point point : operators) {
            int x = point.x;
            int y = point.y;
            if (islands[x][y] == 0) {
                count++;
                islands[x][y] = 1;
                int id = getId(x, y, m);
                for (int i = 0; i < dx.length; i++) {
                    int newX = x + dx[i];
                    int newY = y + dy[i];
                    if (newX >= 0 && newX < n && newY >= 0 && newY < m
                    && islands[newX][newY] == 1) {
                        int newId = getId(newX, newY, m);
                        System.out.println(uf.find(id));
                        System.out.println(uf.find(newId));
                        System.out.println();
                        if (uf.find(id) != uf.find(newId)) {
                            count--;
                            uf.union(id, newId);
                        }
                    }
                }
            }
            result.add(count);
        }
        return result;
    }


    class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }


    private int getId(int row, int col, int cols) {
        return row*cols + col;
    }

    public class UnionFind {
        private Map<Integer, Integer> roots = new HashMap<>();

        public UnionFind(int n, int m) {
            for(int i = 0 ; i < n; i++) {
                for(int j = 0 ; j < m; j++) {
                    int id = getId(i, j, m);
                    roots.put(id, id);
                }
            }
        }
        public int find(int id) {
            int root = roots.get(id);
            while (root != roots.get(root)) {
                root = roots.get(root);
            }

            int cur = id;

            while (root != roots.get(cur)) {
                int tmp = roots.get(cur);
                roots.put(cur, root);
                cur = tmp;
            }
            return root;
        }

        public void union(int id1, int id2) {
            int root1 = find(id1);
            int root2 = find(id2);
            if (root1 != root2) {
                roots.put(root1, root2);
            }
        }
    }

    public void test() {
        int[][] nums = {{0,0},{1,1},{1,0},{0,1}};
        Point[] points = new Point[4];
        for (int i = 0; i < 4; i++) {
            points[i] = new Point(nums[i][0], nums[i][1]);
        }
        numIslands2(2, 2, points);
    }


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
     * 310. Minimum Height Trees.
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new HashSet<>());
            }
            graph.get(edge[0]).add(edge[1]);

            if (!graph.containsKey(edge[1])) {
                graph.put(edge[1], new HashSet<>());
            }
            graph.get(edge[1]).add(edge[0]);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!graph.containsKey(i)) {
                result.add(i);
            }
        }
        if (!result.isEmpty()) {
            return result;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int node : graph.keySet()) {
            if (graph.get(node).size() == 1) {
                queue.offer(node);
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            result = new ArrayList<>(queue);
            for (int i = 0; i < size; i++) {
                int tmp = queue.poll();
                for (int next : graph.get(tmp)) {
                    graph.get(next).remove(tmp);
                    if (graph.get(next).size() == 1) {
                        queue.offer(next);
                    }
                }
            }
        }
        return result;
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

    /**
     * 319. Bulb Switcher.
     * @param n
     * @return
     */
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }

    /**
     * 320. Generalized Abbreviation.
     * @param word
     * @return
     */
    public List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<>();
        DFSGenerateAbbr(result, "", 0, word);
        return result;
    }

    private void DFSGenerateAbbr(List<String> result, String cur, int start, String word) {
        result.add(cur + word.substring(start));
        if (start == word.length())
            return;

        // 定义新的起始位置
        int i = 0;

        // 除了最开始，起始位置都要与之前结尾位置隔一个
        if (start > 0) {
            i = start + 1;
        }

        for (; i < word.length(); i++) {
            String prefix = cur + word.substring(start, i);
            // 以ith字符开头，依次替换j个字母成数字。
            for (int j = 1; j <= word.length() - i; j++) {
                DFSGenerateAbbr(result,  prefix + j, i + j, word);
                //i+j,表示从上一个start开始替换j个字母后开始的字母位置。
            }
        }
    }

    public static void main(String[] args) {
        Question_301_320 c = new Question_301_320();
//        System.out.println(c.isAdditiveNumber("198019823962"));
//        System.out.println(c.nthSuperUglyNumber(12, nums));
//        c.test();
        String a = "ab";
        String b = "aac";
        List<String> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        Collections.sort(list);
        System.out.println(list);
    }
}
