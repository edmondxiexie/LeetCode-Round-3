import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode Question 1 to 20
 * @author Edmond
 */
public class Question_1_20 {

    /**
     * 1. Two Sum.
     * @param nums number array
     * @param target target sum
     * @return two indexes
     */
    public static int[] twoSum(int[] nums, int target) {
        if (nums == null) {
            return null;
        }
        // <num, index>
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int remain = target - nums[i];
            if (map.containsKey(remain) && map.get(remain) != i) {
                result[0] = i;
                result[1] = map.get(remain);
                return result;
            }
        }
        return result;
    }

    /**
     * 2. Add Two Numbers.
     * @param l1 list 1
     * @param l2 list 2
     * @return list sum
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum;
            if (l1 == null) {
                sum = l2.val + carry;
                l2 = l2.next;
            } else if (l2 == null) {
                sum = l1.val + carry;
                l1 = l1.next;
            } else {
                sum = l1.val + l2.val + carry;
                l1 = l1.next;
                l2 = l2.next;
            }
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
        }
        if (carry != 0) {
            cur.next = new ListNode(carry);
        }
        return dummy.next;
    }

    /**
     * 3. Longest Substring Without Repeating Characters.
     * @param s String
     * @return longest length of substring
     */
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int chars = 128;

        // record the last index of existing characters or -1 for not existing char
        int[] record = new int[chars];
        Arrays.fill(record, -1);
        int[] dp = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            int curLen;
            if (record[cur] != -1) {
                curLen = Math.min(dp[i] + 1, i - record[cur]);
                record[cur] = i;
            } else {
                curLen = dp[i] + 1;
                record[cur] = i;
            }
            dp[i + 1] = curLen;
            max = Math.max(max, curLen);
        }
        return max;
    }

    /**
     * 4. Median of Two Sorted Arrays.
     * @param nums1 number array 1
     * @param nums2 number array 2
     * @return median number
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        // make sure nums1 is longer than nums2
        if (len1 < len2) {
            return findMedianSortedArrays(nums2, nums1);
        }

        if (len2 == 0) {
            return (nums1[(len1 - 1) / 2] + nums1[(len1) / 2]) / 2.0;
        }

        // lower bound of nums2
        int start = 0;

        // upper bound of nums1
        int end = len2 * 2;

        while (start <= end) {

            // the cut position of nums2
            int cut2 = start + (end - start) / 2;

            // the cut position of nums1
            int cut1 = len1 + len2 - cut2;

            // left and right side of cut 1
            int l1Index = (cut1 == 0) ? -1 : (cut1 - 1) / 2;
            int r1Index = cut1 / 2;

            // left and right side of cut 2
            int l2Index = (cut2 == 0) ? -1 : (cut2 - 1) / 2;
            int r2Index = cut2 / 2;

            int l1 = (l1Index < 0) ? Integer.MIN_VALUE : nums1[l1Index];
            int r1 = (r1Index >= len1) ? Integer.MAX_VALUE : nums1[r1Index];
            int l2 = (l2Index < 0) ? Integer.MIN_VALUE : nums2[l2Index];
            int r2 = (r2Index >= len2) ? Integer.MAX_VALUE : nums2[r2Index];

            // all the left elements should less than all the right elements
            // l1 < r1 && l2 < r2 && l1 < r2 && l2 < r1
            // we can guarantee l1 < r1 && l2 < r2
            if (l2 > r1) {
                end = cut2 - 1;
            } else if (r2 < l1) {
                start = cut2 + 1;
            } else {
                return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
            }
        }
        return -1;
    }

    /**
     * 5. Longest Palindromic Substring.
     * @param s string
     * @return longest palindrome string
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            String cur1 = longestPalindromeFromMid(s, i, i);
            String cur2 = longestPalindromeFromMid(s, i, i + 1);
            result = (cur1.length() > result.length()) ? cur1 : result;
            result = (cur2.length() > result.length()) ? cur2 : result;
        }
        return result;
    }

    /**
     * Helper function: longest Pal sub from mid two indexes.
     *
     * case 1: Exactly mid -> left = right.
     * (abdbc, 2) -> bdb
     *
     * case 2: Left side of mid -> right = left + 1.
     * (abbc, 1) -> bb
     *
     * @param s string
     * @param left left index
     * @param right right index
     * @return longest pal sub string
     */
    private String longestPalindromeFromMid(String s, int left, int right) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    /**
     * 11. Container With Most Water.
     * @param height height
     * @return max area
     */
    public int maxArea(int[] height) {
        int max = 0;
        if (height == null || height.length == 0) {
            return max;
        }

        // start from two sides to mid
        // In every step, move the shorter board towards mid, because this board is no longer helpful
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            if (height[left] <= height[right]) {
                int curArea = height[left] * (right - left);
                max = Math.max(curArea, max);
                left++;
            } else {
                int curArea = height[right] * (right - left);
                max = Math.max(curArea, max);
                right--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
//        int[] nums1 = {1, 2};
////        int[] nums2 = {3, 4};
//        int[] nums1 = {100000};
//        int[] nums2 = {100001};
//        System.out.println(findMedianSortedArrays(nums1, nums2));
        int[] nums = {3, 2, 4};
        System.out.println(Arrays.toString(twoSum(nums, 6)));
    }
}
