package Onsite;

/**
 * Created by Edmond on 8/12/17.
 */
public class TwoPointer {
    /**
     * 283 Move zero，follow up不在意顺序，移动次数最小 × 5, follow up
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int count = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] != 0) {
                left++;
            }
            while (left < right && nums[right] == 0) {
                right--;
            }
            if (left < right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
                count++;

            }
        }
        System.out.println(count);
    }



    public static void main(String[] args) {
        TwoPointer tp = new TwoPointer();
        int[] nums = {0, 1, 0, 3, 12, 5, 0, 0, 5, 6};
        tp.moveZeroes(nums);
    }
}
