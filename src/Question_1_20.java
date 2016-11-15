import java.util.Map;

/**
 * LeetCode Question 1 to 20
 * @author Edmond
 */
public class Question_1_20 {

    /**
     * 1. Two Sum.
     * @param nums number array
     * @param target target sum
     * @return two indexes
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null) {
            return null;
        }
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            int newTarget = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == newTarget) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * 4. Median of Two Sorted Arrays.
     * @param nums1 number array 1
     * @param nums2 number array 2
     * @return median number
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        // make sure nums1 is longer than nums2
        if (len1 < len2) {
            return findMedianSortedArrays(nums2, nums1);
        }

        if (len2 == 0) {
            return (nums1[(len1 - 1) / 2] + nums1[(len1) / 2]) / 2.0;
        }

        // lower bound of nums2
        int start = 0;

        // upper bound of nums1
        int end = len2 * 2;

        while (start <= end) {

            // the cut position of nums2
            int cut2 = start + (end - start) / 2;

            // the cut position of nums1
            int cut1 = len1 + len2 - cut2;

            // left and right side of cut 1
            int l1Index = (cut1 == 0) ? -1 : (cut1 - 1) / 2;
            int r1Index = cut1 / 2;

            // left and right side of cut 2
            int l2Index = (cut2 == 0) ? -1 : (cut2 - 1) / 2;
            int r2Index = cut2 / 2;

            int l1 = (l1Index < 0) ? Integer.MIN_VALUE : nums1[l1Index];
            int r1 = (r1Index >= len1) ? Integer.MAX_VALUE : nums1[r1Index];
            int l2 = (l2Index < 0) ? Integer.MIN_VALUE : nums2[l2Index];
            int r2 = (r2Index >= len2) ? Integer.MAX_VALUE : nums2[r2Index];

            // all the left elements should less than all the right elements
            // l1 < r1 && l2 < r2 && l1 < r2 && l2 < r1
            // we can guarantee l1 < r1 && l2 < r2
            if (l2 > r1) {
                end = cut2 - 1;
            } else if (r2 < l1) {
                start = cut2 + 1;
            } else {
                return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[] nums1 = {1, 2};
//        int[] nums2 = {3, 4};
        int[] nums1 = {100000};
        int[] nums2 = {100001};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
