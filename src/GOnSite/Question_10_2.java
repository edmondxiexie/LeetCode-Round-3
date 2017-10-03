package GOnSite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Edmond on 10/2/17.
 */
public class Question_10_2 {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        for (int len1 = 0; len1 <= k; len1++) {
            if (len1 == 2) {
                int flag = 1;
            }
            int len2 = k - len1;
            if (len1 <= nums1.length && len2 <= nums2.length) {
                int[] sub1 = getMaxK(nums1, len1);
                int[] sub2 = getMaxK(nums2, len2);

                int[] tmp = merge(sub1, sub2);
                if (isGreater(tmp, 0, res, 0)) {
                    res = tmp;
                }
            }
        }
        return res;
    }

    private int[] getMaxK(int[] nums, int k) {
        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            while (index > 0 && nums.length - i - 1 >= k - index && nums[i] > res[index - 1]) {
                index--;
            }
            if (index < k) {
                res[index] = nums[i];
                index++;
            }
        }
        return res;
    }

    private int[] merge(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length + nums2.length];
        int i1 = 0, i2 = 0, index = 0;

        while (i1 < nums1.length || i2 < nums2.length) {
            res[index++] = isGreater(nums1, i1, nums2, i2) ? nums1[i1++] : nums2[i2++];
        }
        return res;
    }

    private boolean isGreater(int[] nums1, int start1, int[] nums2, int start2) {
        for (; start1 < nums1.length && start2 < nums2.length; start1++, start2++) {
            if (nums1[start1] > nums2[start2]) return true;
            if (nums1[start1] < nums2[start2]) return false;
        }
        return start1 != nums1.length;
    }

    public static class Point {
        public int x;
        public int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    int[] fathers;

    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        fathers = new int[n * m];
        for (int i = 0; i < fathers.length; i++) {
            fathers[i] = i;
        }
        int count = 0;
        int[][] matrix = new int[n][m];
        List<Integer> result = new ArrayList<>();
        if (operators == null || operators.length == 0) {
            return result;
        }
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (Point point : operators) {
            int x = point.x;
            int y = point.y;
            int idA = x * m + y;
            if (matrix[x][y] == 1) {
                continue;
            }
            matrix[x][y] = 1;
            count++;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int idB = nx * m + ny;
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && matrix[nx][ny] == 1) {
                    if (!isConnected(idA, idB)) {
                        union(idA, idB);
                        count--;
                    }
                }
            }
            result.add(count);
        }
        return result;
    }

    private int find(int id) {
        if (fathers[id] == id) {
            return id;
        }
        return fathers[id] = find(fathers[id]);
    }

    private boolean isConnected(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        return rootA == rootB;
    }

    private void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            fathers[rootA] = rootB;
        }
    }

    private void output(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Lintcode Wildcard Matching
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 2; i < dp[0].length; i++) {
            if (p.charAt(i - 1) == '*' && dp[0][i - 1]) {
                dp[0][i] = true;
            }
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    if (dp[i - 1][j - 1] || dp[i][j - 1]) {
                        dp[i][j] = true;
                    } else if (i > 1 && s.charAt(i - 1) == s.charAt(i - 2) && dp[i - 1][j]) {
                        dp[i][j] = true;
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        // 3 16
        Question_10_2 q = new Question_10_2();
        int[] nums1 = {6,7};
        int[] nums2 = {6,0,4};
//        System.out.println(Arrays.toString(q.maxNumber(nums1, nums2, 5)));
//        String s = "[[5,5],[0,7],[1,13],[5,14],[7,15],[3,15],[2,10],[4,6],[4,9],[1,2],[3,13],[4,8],[9,15],[6,4],[6,11],[9,11],[0,16],[3,6],[3,14],[6,1],[7,13],[2,0],[6,0],[6,9],[7,1],[7,4],[5,15],[0,1],[1,16],[6,16],[5,6],[3,12],[9,1],[7,2],[8,2],[6,2],[6,8],[4,7],[1,15],[3,0],[5,10],[9,9],[8,1],[8,4],[9,2],[3,11],[6,6],[2,16],[9,5],[2,4],[9,13],[3,16],[7,14],[2,3],[8,13],[7,10],[9,4],[8,16],[4,12],[3,2],[1,0],[1,9]]";
//        s = s.replace("[", "{");
//        s = s.replace("]", "}");
//        System.out.println(s);
//        int[][] p = {{5,5},{0,7},{1,13},{5,14},{7,15},{3,15},{2,10},{4,6},{4,9},{1,2},{3,13},{4,8},{9,15},{6,4},{6,11},{9,11},{0,16},{3,6},{3,14},{6,1},{7,13},{2,0},{6,0},{6,9},{7,1},{7,4},{5,15},{0,1},{1,16},{6,16},{5,6},{3,12},{9,1},{7,2},{8,2},{6,2},{6,8},{4,7},{1,15},{3,0},{5,10},{9,9},{8,1},{8,4},{9,2},{3,11},{6,6},{2,16},{9,5},{2,4},{9,13},{3,16},{7,14},{2,3},{8,13},{7,10},{9,4},{8,16},{4,12},{3,2},{1,0},{1,9}};
//        Point[] points = new Point[p.length];
//        for (int i = 0; i < points.length; i++) {
//            points[i] = new Point(p[i][0], p[i][1]);
//        }
//        q.numIslands2(10, 17, points);
        String s = "bbabacccbcbbcaaab";
        String p = "*a*b??b*b";
        System.out.println(q.isMatch(s, p));
    }


}
