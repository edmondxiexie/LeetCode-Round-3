import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Question_241_260 {

    /**
     * 246. Strobogrammatic Number.
     * @param num
     * @return
     */
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) {
            return true;
        }
        int left = 0;
        int right = num.length() - 1;
        while (left <= right) {
            if (!isStroboPair(num.charAt(left), num.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 259. 3Sum Smaller.
     * @param nums
     * @param target
     * @return
     */
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int sum = nums[i];
            int start = i + 1;
            solveThreeSumSmaller(nums, sum, target, start);
        }
        return count;
    }

    private void solveThreeSumSmaller(int[] nums, int sum, int target, int start) {
        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right && sum + nums[left] + nums[right] >= target) {
                right--;
            }
            if (left < right) {
                count += right - left;
            }
            left++;
        }
    }


    int count = 0;


    private boolean isStroboPair(char c1, char c2) {
        Map<Character, Character> map = new HashMap<>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        return map.containsKey(c1) && map.get(c1) == c2;

    }

    public static void main(String[] args) {
        int n = 1908;
        String str = Integer.toString(n);
        System.out.println(str);
    }
}
