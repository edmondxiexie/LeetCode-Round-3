import java.util.Arrays;

/**
 * Created by Edmond on 12/9/16.
 */
public class Question_461_480 {

    /**
     * 462. Minimum Moves to Equal Array Elements II.
     * @param nums
     * @return
     */
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int total = 0;
        while (left < right) {
            total += nums[right] - nums[left];
            left++;
            right--;
        }
        return total;
    }
}
