/**
 * Created by Edmond on 12/23/16.
 */
public class Question_301_320 {
    public class NumArray {

        private int[] sums;
        private int[] nums;

        public NumArray(int[] nums) {
            sums = new int[nums.length];
            this.nums = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                this.nums[i] = nums[i];
                if (i == 0) {
                    sums[i] = nums[i];
                } else {
                    sums[i] = sums[i - 1] + nums[i];
                }
            }
        }

        public int sumRange(int i, int j) {
            return sums[j] - sums[i] + nums[i];
        }
    }
}
