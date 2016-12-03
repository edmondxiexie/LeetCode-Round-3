import java.util.*;

public class Question_21_40 {
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
