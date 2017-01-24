import java.util.*;

public class Question_021_040 {
    /**
     * 22. Generate Parentheses.
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        int left = 0;
        int right = 0;
        String str = "";
        solveGenerateParenthesis(n, result, left, right, str);
        return result;
    }

    private void solveGenerateParenthesis(int n, List<String> result, int left, int right, String str) {
        if (left < right) {
            return;
        }
        if (left == n && right == n) {
            result.add(str);
        }
        if (left < n) {
            solveGenerateParenthesis(n, result, left + 1, right, str + "(");
        }
        if (right < n) {
            solveGenerateParenthesis(n, result, left, right + 1, str + ")");
        }
    }

    /**
     * 23. Merge k Sorted Lists.
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        ListNode dummy = new ListNode(0);
        if (lists == null || lists.length == 0) {
            return dummy.next;
        }
        for (ListNode list : lists) {
            if (list != null) {
                queue.offer(list);
            }
        }
        ListNode cur = dummy;
        while (!queue.isEmpty()) {
            ListNode first = queue.poll();
            ListNode second = first.next;
            cur.next = first;
            cur = cur.next;
            if (second != null) {
                queue.offer(second);
            }
        }
        return dummy.next;
    }

    /**
     * 24. Swap Nodes in Pairs.
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        // d(last) -> 1(cur) -> 2(next) -> 3 -> 4
        // d(last) -> 2(next) -> 1(cur) -> 3 -> 4

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode last = dummy;

        while (last != null && last.next != null && last.next.next != null) {
            ListNode cur = last.next;
            ListNode next = cur.next;

            cur.next = next.next;
            next.next = cur;
            last.next = next;

            last = last.next.next;
        }
        return dummy.next;
    }

    /**
     * 25. Reverse Nodes in k-Group.
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // d(l) -> 1(c) -> 2(n) -> 3 -> 4
        // d(l) -> 2 -> 1(c) -> 3(n) -> 4
        // d(l) -> 3 -> 2 -> 1 -> 4
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode last = dummy;
        while (last != null) {
            ListNode check = last.next;
            for (int i = 0; i < k; i++) {
                if (check == null) {
                    return dummy.next;
                }
                check = check.next;
            }
            ListNode cur = last.next;
            ListNode next = cur.next;

            for (int i = 1; i < k; i++) {
                cur.next = next.next;
                next.next = last.next;
                last.next = next;
                next = cur.next;
            }

            for (int i = 0; i < k; i++) {
                last = last.next;
            }
        }
        return dummy.next;
    }

    /**
     * 26. Remove Duplicates from Sorted Array.
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int slow = -1;
        int fast = 0;
        while (fast < nums.length) {
            if (slow == -1 || nums[slow] != nums[fast]) {
                nums[slow + 1] = nums[fast];
                slow++;
                fast++;
            } else {
                fast++;
            }
        }
        return slow + 1;
    }

    /**
     * 27. Remove Element.
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] == val) {
                fast++;
            } else {
                nums[slow] = nums[fast];
                slow++;
                fast++;
            }
        }
        return slow;
    }

    /**
     * 28. Implement strStr().
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        for (int i = 0; i < haystack.length(); i++) {
            String tmp = haystack.substring(i);
            if (tmp.startsWith(needle)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 29. Divide Two Integers.
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 1) {
            return dividend;
        }
        if ((dividend == Integer.MIN_VALUE && divisor == -1) || divisor == 0) {
            return Integer.MAX_VALUE;
        }
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            sign = -1;
        }
        long first = Math.abs((long)dividend);
        long second = Math.abs((long)divisor);
        int power = 31;
        long secondPower = second << power;
        long result = 0;

        while (first >= second) {
            while (secondPower > first) {
                secondPower >>= 1;
                power--;
            }
            first -= secondPower;
            result += (1L << power);
        }
        if (sign > 0) {
            return (int)result;
        } else {
            return (int)(0 - result);
        }
    }

    /**
     * 30. Substring with Concatenation of All Words.
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        Map<String, Integer> toFind = new HashMap<>();
        Map<String, Integer> found = new HashMap<>();
        int size = words.length;
        int len = words[0].length();
        for (String word : words) {
            if (!toFind.containsKey(word)) {
                toFind.put(word, 1);
            } else {
                toFind.put(word, toFind.get(word) + 1);
            }
        }
        for (int i = 0; i <= s.length() - size * len; i++) {
            found.clear();
            int j;
            for (j = 0; j < size; j++) {
                int start = i + j * len;
                String sub = s.substring(start, start + len);
                if (!toFind.containsKey(sub)) {
                    break;
                }
                if (!found.containsKey(sub)) {
                    found.put(sub, 1);
                } else {
                    found.put(sub, found.get(sub) + 1);
                }
                if (found.get(sub) > toFind.get(sub)) {
                    break;
                }
            }
            if (j == size) {
                res.add(i);
            }
        }
        return res;
     }

    /**
     * 32. Longest Valid Parentheses.
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    continue;
                } else {
                    int start = stack.pop();
                    int end = i;
                    int curLen = end - start + 1;
                    if (start > 0) {
                        curLen += dp[start - 1];
                    }
                    dp[start] = curLen;
                    dp[end] = curLen;
                    max = Math.max(max, curLen);
                }
            }
        }
        return max;
    }

    /**
     * 39. Combination Sum.
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        int start = 0;
        DFSCombinationSum(candidates, target, result, list, start);
        return result;
    }

    private void DFSCombinationSum(int[] candidates, int target, List<List<Integer>> result,
                                   List<Integer> list, int start) {
        if (target == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = 0; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1]) {
                continue;
            }
            list.add(candidates[i]);
            DFSCombinationSum(candidates, target - candidates[i], result, list, i);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        queue.offer(new ListNode(6));
        queue.offer(new ListNode(3));
        queue.offer(new ListNode(9));
        System.out.println(queue.poll().val);
        System.out.println(queue.poll().val);
        System.out.println(queue.poll().val);
        System.out.println(queue.toString());

    }

}
