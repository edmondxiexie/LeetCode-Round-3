import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * LeetCode Question 41 to 60
 * @author Edmond
 */
public class Question_041_060 {

    /**
     * 41. First Missing Positive.
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int max = 0;
        for (int n : nums) {
            max = Math.max(max, n);
        }
        if (max <= 0) {
            return 1;
        }
        int[] record = new int[max + 1];
        for (int n : nums) {
            if (n > 0) {
                record[n] = 1;
            }
        }
        for (int i = 1; i < record.length; i++) {
            if (record[i] == 0) {
                return i;
            }
        }
        return record.length;
    }

    /**
     * 42. Trapping Rain Water.
     * @param height height
     * @return amount of water
     */
    public int trap(int[] height) {
        // <index>
        Stack<Integer> stack = new Stack<>();
        if (height == null || height.length == 0) {
            return 0;
        }
        int sum = 0;
        int i = 0;
        while (i < height.length) {
            int curHeight = height[i];
            if (stack.isEmpty() || curHeight < height[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int bottom = height[stack.pop()];
                if (stack.isEmpty()) {
                    continue;
                }
                int leftHeight = height[stack.peek()];
                int width = i - stack.peek() - 1;
                int depth = Math.min(leftHeight, curHeight) - bottom;
                sum += width * depth;
            }
        }
        return sum;
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
