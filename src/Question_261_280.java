import java.util.*;
import java.util.regex.Matcher;

public class Question_261_280 {

    /**
     * 266. Palindrome Permutation.
     * @param s
     * @return
     */
    public boolean canPermutePalindrome(String s) {
        int[] chars = new int[128];
        for (char c : s.toCharArray()) {
            chars[c]++;
        }
        boolean oddAppear = false;
        for (int n : chars) {
            if (n % 2 == 1) {
                if (!oddAppear) {
                    oddAppear = true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 267. Palindrome Permutation II.
     * @param s
     * @return
     */
    public static List<String> generatePalindromes(String s) {
        int[] chars = new int[128];
        for (char c : s.toCharArray()) {
            chars[c]++;
        }
        Set<String> result = new HashSet<>();
        char[] strArray = new char[s.length()];
        int left = 0;
        int right = s.length() - 1;
        int start = 0;
        solveGeneratePal(result, chars, start, strArray, left, right);
        return new ArrayList<>(result);
    }

    private static void solveGeneratePal(Set<String> result, int[] chars, int start, char[] strArray, int left, int right) {
        if (left > right) {
            result.add(new String(strArray));
            return;
        }

        if (left == right) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == 1) {
                    strArray[left] = (char)(i);
                    result.add(new String(strArray));
                    return;
                }
            }
        }

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] <= 1) {
                continue;
            }
            char c = (char)(i);
            strArray[left] = c;
            strArray[right] = c;
            chars[i] -= 2;
            solveGeneratePal(result, chars, start, strArray, left + 1, right - 1);
            chars[i] += 2;

        }
    }

    /**
     * 270. Closest Binary Search Tree Value.
     * @param root
     * @param target
     * @return
     */
    public int closestValue(TreeNode root, double target) {
        boolean isLeft = false;
        boolean isRight = false;
        if (root == null) {
            return 0;
        }
        int last = root.val;
        TreeNode cur = root;
        while (cur != null) {
            if (target == cur.val) {
                return cur.val;
            } else if (target > cur.val) {
                if (isLeft) {
                    int tmp = closestValue(cur, target);
                    return (Math.abs(last - target) < Math.abs(tmp - target)) ? last : tmp;
                } else {
                    last = cur.val;
                    cur = cur.right;
                    isRight = true;
                }
            } else {
                if (isRight) {
                    int tmp = closestValue(cur, target);
                    return (Math.abs(last - target) < Math.abs(tmp - target)) ? last : tmp;
                } else {
                    last = cur.val;
                    cur = cur.left;
                    isLeft = true;
                }
            }
        }
        return last;
    }

    private int getMostSide(TreeNode cur, boolean isLeft) {
        int result = cur.val;
        if (isLeft) {
            while (cur != null) {
                result = cur.val;
                cur = cur.left;
            }
        } else {
            while (cur != null) {
                result = cur.val;
                cur = cur.right;
            }
        }
        return result;
    }

    /**
     * 271. Encode and Decode Strings.
     * @param strs
     * @return
     */
    // Encodes a list of strings to a single string.
    public static String encode(List<String> strs) {
        StringBuilder result = new StringBuilder();
        for (String str : strs) {
            int len = str.length();
            result.append(len);
            result.append('&');
            result.append(str);
        }
        return result.toString();
    }

    /**
     * 276. Paint Fence.
     * @param n
     * @param k
     * @return
     */
    public int numWays(int n, int k) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return k;
        }
        int[] dp = new int[n];
        dp[0] = k;
        dp[1] = dp[0] * k;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1);
        }
        return dp[n - 1];
    }

    // Decodes a single string to a list of strings.
    public static List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int flag = s.indexOf('&', i);
            int len = Integer.parseInt(s.substring(i, flag));
            String sub = s.substring(flag + 1, flag + 1 + len);
            result.add(sub);
            i = flag + 1 + len;
        }
        return result;
    }

    /**
     * 278. First Bad Version.
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        int result = start;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid)) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return result;
    }

    private boolean isBadVersion(int n) {
        return true;
    }

    /**
     * 280. Wiggle Sort.
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int i = 1;
        while (i + 1 < nums.length) {
            int tmp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = tmp;
            i += 2;
        }
    }

    public static void main(String[] args) {
        String[] s = {"hQ/ (-OlLQ&_N","oQ=X`dm-@&;","Ti{w","-t#pDD4~Q-W%TNNZ","%W7)VH<?rJ!nHAT_z","Z+8PSgg>(?","}6H^@,_?wo6pKUFW~","D","e6y!+"};
        List<String> strs = Arrays.asList(s);

//        strs.add("abc&");
//        strs.add("sgoaks///");
//        strs.add("edmond");
//        System.out.println(encode(strs));
//        System.out.println(decode(encode(strs)));
        System.out.println(generatePalindromes("aab"));
    }
}
