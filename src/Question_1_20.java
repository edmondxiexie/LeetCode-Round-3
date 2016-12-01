import java.util.*;

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
     * 6. ZigZag Conversion.
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        String result = "";
        for (int row = 0; row < numRows; row++) {
            int dif = (numRows - 1) * 2;
            for (int i = row; i < s.length(); i += dif) {
                result += s.charAt(i);
                int mid = i + (dif - row * 2);
                if (row != 0 && row != numRows - 1 && mid < s.length()) {
                    result += s.charAt(mid);
                }
            }
        }
        return result;
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

    /**
     * 13. Roman to Integer.
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int len = s.length();
        int result = 0;
        for (int i = 0; i < len; i++) {
            int first = map.get(s.charAt(i));
            if (i + 1 == len) {
                result += first;
                return result;
            }
            int second = map.get(s.charAt(i + 1));
            if (first < second) {
                result += second - first;
                i++;
            } else {
                result += first;
            }
        }
        return result;
    }


    /**
     * 14. Longest Common Prefix.
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0 || strs[0].length() == 0) {
            return "";
        }
        int i = 0;

        String result = "";
        while (i < strs[0].length()) {
            char common = strs[0].charAt(i);
            for (String str : strs) {
                if (i >= str.length()) {
                    return result;
                }
                char prefix = str.charAt(i);
                if (prefix != common) {
                    return result;
                }
            }
            result += strs[0].charAt(i);
            i++;
        }
        return result;
    }

    /**
     * 15. 3Sum.
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        if (nums == null || nums.length < 3) {
            return new ArrayList<>(result);
        }
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        int start = 0;
        int target = 0;
        solveThreeSum(nums, result, list, start, target);
        return new ArrayList<>(result);
    }

    private void solveThreeSum(int[] nums, Set<List<Integer>> result,
                               List<Integer> list, int start, int target) {
        int remaining = 3 - list.size();
        if (nums.length - start < remaining) {
            return;
        }
        if (remaining == 2) {
            twoSum(nums, result, list, start, target);
            return;
        }
        if (start >= nums.length) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int newTarget = target - nums[i];
            list.add(nums[i]);
            solveThreeSum(nums, result, list, i + 1, newTarget);
            list.remove(list.size() - 1);
        }
    }

    private void twoSum(int[] nums, Set<List<Integer>> result,
                        List<Integer> list, int start, int target) {
        int end = nums.length - 1;
        while (start < end) {
            int first = nums[start];
            int second = nums[end];
            if (first + second == target) {
                list.add(first);
                list.add(second);
                if (!result.contains(list)) {
                    result.add(new ArrayList<>(list));
                }
                list.remove(list.size() - 1);
                list.remove(list.size() - 1);
                start++;
                end--;
            } else if (first + second < target) {
                start++;
            } else {
                end--;
            }
        }
    }

    /**
     * 16. 3Sum Closest.
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        int result = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        int sum = 0;

        // level 1
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            sum += nums[i];


            // level 2
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                sum += nums[j];

                // level 3
                for (int k = j + 1; k < nums.length; k++) {
                    if (k > j + 1 && nums[k] == nums[k - 1]) {
                        continue;
                    }
                    sum += nums[k];

                    int dif = Math.abs(sum - target);
                    if (dif < Math.abs(result - target)) {
                        result = sum;
                    }
                    if (sum >= target) {
                        sum -= nums[k];
                        break;
                    }
                    sum -= nums[k];
                }
                sum -= nums[j];
            }
            sum -= nums[i];
        }
        return result;
    }

    /**
     * 17. Letter Combinations of a Phone Number.
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        Queue<String> cur = new LinkedList<>();
        Queue<String> next = new LinkedList<>();
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() < 1) {
            return result;
        }
        String[] strs = getStrs(digits.substring(0, 1));
        digits = digits.substring(1);
        for (String str : strs) {
            cur.offer(str);
        }

        while (digits.length() > 0) {
            strs = getStrs(digits.substring(0, 1));
            digits = digits.substring(1);
            while (!cur.isEmpty()) {
                String first = cur.poll();
                for (String str : strs) {
                    next.offer(first + str);
                }
            }
            Queue<String> tmp = cur;
            cur = next;
            next = tmp;
        }
        while (!cur.isEmpty()) {
            result.add(cur.poll());
        }
        return result;
    }

    private static String[] getStrs(String digit) {
        int digitNum = Integer.parseInt(digit);
        int start = (digitNum - 2) * 3;
        int nums = 3;
        if (digit.equals("7")) {
            nums = 4;
        }
        if (digit.equals("8")) {
            nums = 3;
            start++;
        }
        if (digit.equals("9")) {
            nums = 4;
            start++;
        }

        String[] strs = new String[nums];
        for (int i = 0; i < nums; i++) {
            strs[i] = Character.toString((char) (start + i + 'a'));
        }
        return strs;
    }

    /**
     * 18. 4Sum.
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length == 0){
            return Arrays.asList();
        }
        Arrays.sort(nums);
        return nSum(nums, 0, 4, target);
    }

    private static List<List<Integer>> nSum(int[] nums, int start, int n, int target){
        List<List<Integer>> result = new LinkedList<>();
        // target is too small or too large so that there won't be solutions.
        if (target < nums[start]*n || target > nums[nums.length - 1]*n){
            return result;
        }

        for (int i = start, end = nums.length - n + 1; i < end; ++i){
            // avoid duplicated solutions
            if (i > start && nums[i - 1] == nums[i]){
                continue;
            }

            if (n == 2){
                int required = target - nums[i];

                while(nums[end] > required){
                    end--;
                }
                if (nums[end] < required){
                    continue;
                }
                if (i >= end){
                    break;
                }

                // duplicated solution
                if (end + 1 < nums.length && nums[end+1] == nums[end]){
                    continue;
                }

                // get two sum
                result.add(new LinkedList<Integer>(Arrays.asList(nums[i], nums[end])));
                continue;
            }

            for (List<Integer> list : nSum(nums, i + 1, n - 1, target - nums[i])){
                list.add(nums[i]);
                result.add(list);
            }

        }
        return result;
    }

    /**
     * 19. Remove Nth Node From End of List.
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    /**
     * 20. Valid Parentheses.
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == '(' || cur == '[' || cur == '{') {
                stack.push(cur);
            }
            if (cur == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
            if (cur == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    return false;
                }
            }
            if (cur == '}') {
                if (stack.isEmpty() || stack.pop() != '{') {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        //        System.out.println(threeSum(nums));
        //        int[] nums1 = {1, 2};
        ////        int[] nums2 = {3, 4};
        //        int[] nums1 = {100000};
        //        int[] nums2 = {100001};
        //        System.out.println(findMedianSortedArrays(nums1, nums2));
        //        int[] nums = {3, 2, 4};
        //        System.out.println(Arrays.toString(twoSum(nums, 6)));
        //        System.out.println(romanToInt("MMMDLXXXVI"));
        //        int[] nums1 = {-493,-482,-482,-456,-427,-405,-392,-385,-351,-269,-259,-251,-235,-235,-202,-201,-194,-189,-187,-186,-180,-177,-175,-156,-150,-147,-140,-122,-112,-112,-105,-98,-49,-38,-35,-34,-18,20,52,53,57,76,124,126,128,132,142,147,157,180,207,227,274,296,311,334,336,337,339,349,354,363,372,378,383,413,431,471,474,481,492};
        //        System.out.println(fourSum(nums1, 6189));
        //        System.out.println(letterCombinations("7").toString());
        System.out.println(convert("A", 1));
    }
}