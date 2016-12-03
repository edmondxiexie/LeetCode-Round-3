public class Question_281_300 {

    /**
     * 287. Find the Duplicate Number.
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        // Similar to find loop in linkedlist.
        if (nums.length <= 1) {
            return -1;
        }
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] nums = {4,3,1,4,2};
        System.out.println(findDuplicate(nums));
    }
}
