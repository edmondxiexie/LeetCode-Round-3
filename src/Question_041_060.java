import java.util.*;

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

    /**
     * 44. Wildcard Matching.
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int i = 0;
        int j = 0;
        int star = -1;
        int lastI = -1;

        while (i < s.length()) {
            if (j < p.length()
                    && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j))) {
                i++;
                j++;
            } else if (j < p.length() && p.charAt(j) == '*') {
                star = j;
                lastI = i;
                j++;
            } else if (star != -1) {
                j = star + 1;
                i = lastI + 1;
                lastI++;
            } else {
                return false;
            }
        }
        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }
        return j == p.length();
    }

    /**
     * 45. Jump Game II.
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums == null || nums.length == 1) {
            return 0;
        }
        int start = 0;
        int end = 0;
        int jumps = 0;
        while (end < nums.length - 1) {
            jumps++;
            int farthest = end;
            for (int i = start; i <= end; i++) {
                if (nums[i] + i > farthest) {
                    farthest = nums[i] + i;
                }
            }
            start = end + 1;
            end = farthest;
        }
        return jumps;
    }

    /**
     * 46. Permutations.
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        boolean[] visited = new boolean[nums.length];
        DFSPermute(nums, result, list, visited);
        return result;
    }

    private void DFSPermute(int[] nums, List<List<Integer>> result, List<Integer> list, boolean[] visited) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            list.add(nums[i]);
            visited[i] = true;
            DFSPermute(nums, result, list, visited);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }

    /**
     * 47. Permutations II.
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return new ArrayList<>(result);
        }
        boolean[] visited = new boolean[nums.length];
        DFSPermute(nums, result, list, visited);
        return new ArrayList<>(result);
    }

    private void DFSPermute(int[] nums, Set<List<Integer>> result, List<Integer> list, boolean[] visited) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            list.add(nums[i]);
            visited[i] = true;
            DFSPermute(nums, result, list, visited);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
//        Map<Character, Integer> map = new HashMap<>();
//        String s = "she sells seashells on the seashore";
//        for (char e : s.toCharArray()) {
//            if (map.containsKey(e)) {
//                map.put(e, map.get(e) + 1);
//            } else {
//                map.put(e, 1);
//            }
//        }
//        int count = 0;
//        for (char e : map.keySet()) {
//            count += map.get(e);
//            System.out.println(e + "  " + map.get(e));
//        }
//        System.out.println("count: " + count);
//        System.out.println(s.length());
//
//        Map<Character, String> map1 = new HashMap<>();
//        Map<Character, String> map2 = new HashMap<>();
//        map1.put(' ', "1010");
//        map1.put('a', "0000");
//        map1.put('r', "0111");
//        map1.put('s', "1000");
//        map1.put('t', "1001");
//        map1.put('e', "0001");
//        map1.put('h', "0010");
//        map1.put('l', "0011");
//        map1.put('n', "0100");
//        map1.put('o', "0101");
//
//        map2.put('a', "11100");
//        map2.put('e', "010");
//        map2.put('h', "10");
//        map2.put('l', "0111");
//        map2.put('n', "11111");
//        map2.put('o', "110");
//        map2.put('r', "11110");
//        map2.put('s', "00");
//        map2.put('t', "11101");
//        map2.put(' ', "0110");
//
//        String s1 = "";
//        for (char e : s.toCharArray()) {
//            s1 += map1.get(e);
//        }
//
//        String s2 = "";
//        for (char e : s.toCharArray()) {
//            s2 += map2.get(e);
//        }
//
//        System.out.println(s1);
//        System.out.println(s2);
//        System.out.println(s1.length());
//        System.out.println(s2.length());
        Question_041_060 q = new Question_041_060();
        int[] nums = {2,3,1,1,4};
        System.out.println(q.jump(nums));
    }
}
