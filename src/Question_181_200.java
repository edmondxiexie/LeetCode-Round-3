public class Question_181_200 {
    /**
     * 198. House Robber.
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int last = nums[0];
        int cur = Math.max(last, nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int tmp = cur;
            cur = Math.max(last + nums[i], cur);
            last = tmp;
        }
        return cur;
    }
}
