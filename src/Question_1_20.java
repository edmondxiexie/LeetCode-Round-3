import java.util.Map;

/**
 * Created by Edmond on 11/14/16.
 */
public class Question_1_20 {
    /**
     * 4. Median of Two Sorted Arrays
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        // make sure nums1 is longer than nums2
        if (len1 < len2) {
            return findMedianSortedArrays(nums2, nums1);
        }

        if (len2 == 0) {
            return (nums1[(len2 - 1) / 2] + nums1[(len2) / 2]) / 2;
        }

        // lower bound of nums2
        int start = 0;

        // upper bound of nums1
        int end = len2 * 2;

        while (start < end) {

            // the cut position of nums2
            int cut2 = start + (end - start) / 2;

            // the cut position of nums1
            int cut1 = len1 + len2 - cut2;

            // left and right side of cut 1
            int l1Index = (cut1 - 1) / 2;
            int r1Index = cut1 / 2;

            // left and right side of cut 2
            int l2Index = (cut2 - 1) / 2;
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
                return (Math.max(l1, r1) + Math.min(r1, r2)) / 2.0;
            }
        }
        return -1;
    }
}
