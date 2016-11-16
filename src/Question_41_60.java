import java.util.Stack;

/**
 * LeetCode Question 41 to 60
 * @author Edmond
 */
public class Question_41_60 {

    /**
     * 42. Trapping Rain Water.
     * @param height height
     * @return amount of water
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int sumWater = 0;
        int i = 0;
        Stack<Integer> stack = new Stack<>();

        // Always keep the current height is the smallest height in the stack
        while (i < height.length) {
            int curHeight = height[i];
            if (stack.isEmpty() || curHeight < height[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                // There must be a "U" shape in the stack
                int botHeight = height[stack.pop()];
                int curWater = 0;
                if (!stack.isEmpty()) {

                    // min height
                    int minHeight = Math.min(height[stack.peek()], height[i]) - botHeight;

                    int width = i - stack.peek() - 1;
                    curWater = minHeight * width;
                }
                sumWater += curWater;
            }
        }
        return sumWater;
    }
}
