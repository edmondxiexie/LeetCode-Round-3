import java.util.HashMap;
import java.util.Map;
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

    public static void main(String[] args) {
        Map<Character, Integer> map = new HashMap<>();
        String s = "she sells seashells on the seashore";
        for (char e : s.toCharArray()) {
            if (map.containsKey(e)) {
                map.put(e, map.get(e) + 1);
            } else {
                map.put(e, 1);
            }
        }
        int count = 0;
        for (char e : map.keySet()) {
            count += map.get(e);
            System.out.println(e + "  " + map.get(e));
        }
        System.out.println("count: " + count);
        System.out.println(s.length());

        Map<Character, String> map1 = new HashMap<>();
        Map<Character, String> map2 = new HashMap<>();
        map1.put(' ', "1010");
        map1.put('a', "0000");
        map1.put('r', "0111");
        map1.put('s', "1000");
        map1.put('t', "1001");
        map1.put('e', "0001");
        map1.put('h', "0010");
        map1.put('l', "0011");
        map1.put('n', "0100");
        map1.put('o', "0101");

        map2.put('a', "11100");
        map2.put('e', "010");
        map2.put('h', "10");
        map2.put('l', "0111");
        map2.put('n', "11111");
        map2.put('o', "110");
        map2.put('r', "11110");
        map2.put('s', "00");
        map2.put('t', "11101");
        map2.put(' ', "0110");

        String s1 = "";
        for (char e : s.toCharArray()) {
            s1 += map1.get(e);
        }

        String s2 = "";
        for (char e : s.toCharArray()) {
            s2 += map2.get(e);
        }

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1.length());
        System.out.println(s2.length());
    }
}
