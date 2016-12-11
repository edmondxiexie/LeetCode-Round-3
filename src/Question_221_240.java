import java.util.ArrayList;
import java.util.List;

public class Question_221_240 {

    /**
     * 228. Summary Ranges.
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int start = 0;
        int target = nums[0] + 1;
        for (int i = 1; i <= nums.length; i++) {
            if (i == nums.length) {
                if (start == i - 1) {
                    result.add(nums[start] + "");
                } else {
                    result.add(nums[start] + "->" + nums[i - 1]);
                }
                break;
            }
            if (nums[i] == target) {
                target = nums[i] + 1;
            } else {
                if (start == i - 1) {
                    result.add(nums[start] + "");
                } else {
                    result.add(nums[start] + "->" + nums[i - 1]);
                }
                start = i;
                target = nums[i] + 1;
            }
        }
        return result;
    }
}
