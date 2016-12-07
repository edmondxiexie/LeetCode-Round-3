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
}
