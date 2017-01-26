import java.util.Stack;

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
}
