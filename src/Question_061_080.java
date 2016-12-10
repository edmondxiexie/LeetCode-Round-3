import java.util.ArrayList;
import java.util.List;

public class Question_061_080 {
    /**
     * 77. Combinations.
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        if (k <= 0 || n <= 0) {
            return result;
        }
        int start = 0;
        DFSCombine(result, list, nums, start, k);
        return result;
    }

    private void DFSCombine(List<List<Integer>> result, List<Integer> list, int[] nums, int start, int k) {
        if (list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            DFSCombine(result, list, nums, i + 1, k);
            list.remove(list.size() - 1);
        }
    }
}
