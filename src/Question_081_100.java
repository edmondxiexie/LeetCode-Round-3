import java.util.*;

public class Question_081_100 {
    /**
     * 84. Largest Rectangle in Histogram.
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        // <index>
        Stack<Integer> stack = new Stack<>();
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int max = 0;
        int i = 0;
        while (i <= heights.length) {
            int curHeight = (i == heights.length) ? 0 : heights[i];
            if (stack.isEmpty() || curHeight > heights[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int lastIndex = stack.pop();
                int lastHeight = heights[lastIndex];
                int leftBound = stack.isEmpty() ? -1 : stack.peek();
                int width = i - 1 - leftBound;
                int area = width * lastHeight;
                max = Math.max(max, area);
            }
        }
        return max;
    }

    /**
     * 86. Partition List.
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode large = dummy;
        ListNode small = dummy;
        ListNode cur = dummy.next;
        while (cur != null) {
            if (cur.val >= x) {
                large = cur;
                cur = cur.next;
            } else {
                if (large == dummy) {
                    small = cur;
                    cur = cur.next;
                } else {
                    large.next = cur.next;
                    cur.next = small.next;
                    small.next = cur;
                    small = small.next;
                    cur = cur.next;
                }
            }
        }
        return dummy.next;
    }

    /**
     * 87. Scramble String.
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        if(s1.length()!=s2.length()) {
            return false;
        }
        int[] letters = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) {
                return false;
            }
        }

        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i))
                    && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 89. Gray Code.
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        if (n == 0) {
            return result;
        }
        int max = (int)Math.pow(2, n) - 1;
        Set<Integer> set = new HashSet<>();
        set.add(0);
        return DFSGrayCode(result, 0, set, max, n);
    }

    private List<Integer> DFSGrayCode(List<Integer> result, int cur, Set<Integer> set, int max, int bits) {
        if (result.size() == max + 1) {
            return result;
        }
        for (int i = 0; i < bits; i++) {
            int mask = 1 << i;
            int bit = cur & mask;
            int next;
            if (bit == 0) {
                next = cur | mask;
            } else {
                next = cur & (~mask);
            }
            if (!set.contains(next)) {
                set.add(next);
                result.add(next);
                List<Integer> tmp = DFSGrayCode(result, next, set, max, bits);
                if (tmp != null) {
                    return result;
                }
                set.remove(next);
                result.remove(result.size() - 1);
            }
        }
        return null;
    }

    /**
     * 97. Interleaving String.
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        int rows = s1.length() + 1;
        int cols = s2.length() + 1;
        boolean[][] dp = new boolean[rows][cols];
        dp[0][0] = true;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!dp[i][j]) {
                    continue;
                }
                if (i + 1 < rows && s1.charAt(i) == s3.charAt(i + j)) {
                    dp[i + 1][j] = true;
                }
                if (j + 1 < cols && s2.charAt(j) == s3.charAt(i + j)) {
                    dp[i][j + 1] = true;
                }
            }
        }
        return dp[rows - 1][cols - 1];
    }

    public static void main(String[] args) {
        Question_081_100 q = new Question_081_100();
        System.out.println(q.grayCode(1));
    }
}
