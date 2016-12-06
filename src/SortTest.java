import java.util.Arrays;

public class SortTest {

    /**
     * InsertionSort.
     * @param nums
     */
    public static void insertionSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        for (int i = 1; i < nums.length; i++) {
            int tmp = nums[i];
            int j = i;
            while (j >= 1 && nums[j - 1] > tmp) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = tmp;
        }
    }

    public static void mergeSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int start = 0;
        int end = nums.length - 1;
        mergeSort(nums, start, end);
    }

    private static void mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        merge(nums, start, mid, mid + 1, end);
    }

    private static void merge(int[] nums, int start1, int end1, int start2, int end2) {
        int[] tmp = new int[end2 - start1 + 1];
        int cur1 = start1;
        int cur2 = start2;
        for (int i = 0; i < tmp.length; i++) {
            if (cur1 > end1) {
                tmp[i] = nums[cur2];
                cur2++;
            } else if (cur2 > end2) {
                tmp[i] = nums[cur1];
                cur1++;
            } else if (nums[cur1] <= nums[cur2]) {
                tmp[i] = nums[cur1];
                cur1++;
            } else {
                tmp[i] = nums[cur2];
                cur2++;
            }
        }
        for (int i = 0; i < tmp.length; i++) {
            nums[start1 + i] = tmp[i];
        }
    }

    public static void quickSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int start = 0;
        int end = nums.length - 1;
        quickSort(nums, start, end);
    }

    private static void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = nums[end];
        int partition = partition(nums, start, end, pivot);
        quickSort(nums, start, partition - 1);
        quickSort(nums, partition + 1, end);
    }

    private static int partition(int[] nums, int start, int end, int pivot) {
        int left = start;
        int right = end - 1;
        while (left < right) {
            while (nums[left] < pivot) {
                left++;
            }
            while (right > start && nums[right] > pivot) {
                right--;
            }
            if (left < right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
            }
        }
        int tmp = nums[left];
        nums[left] = nums[end];
        nums[end] = tmp;
        return left;
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 3, 6, 2, 4, 0, 5};
        insertionSort(nums1);
        System.out.println(Arrays.toString(nums1));
        int[] nums2 = {1, 3, 6, 2, 4, 0, 5};
        mergeSort(nums2);
        System.out.println(Arrays.toString(nums2));
        int[] nums3 = {1, 3, 6, 2, 4, 0, 5};
        quickSort(nums3);
        System.out.println(Arrays.toString(nums3));
    }
}
