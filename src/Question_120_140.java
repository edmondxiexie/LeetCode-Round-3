import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edmond on 11/30/16.
 */
public class Question_120_140 {
    public static int longestConsecutive(int[] nums) {
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                int left = 0;
                int right = 0;
                if (map.containsKey(num - 1)) {
                    left = map.get(num - 1);
                }
                if (map.containsKey(num + 1)) {
                    right = map.get(num + 1);
                }
                int total = left + right + 1;
                max = Math.max(max, total);
                map.put(num, total);
                map.put(num - left, total);
                map.put(num + right, total);
            }
        }
        return max;
    }

    public static void main(String[] args) {

    }

}
