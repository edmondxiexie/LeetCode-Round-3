package Onsite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edmond on 8/15/17.
 */
public class DPBackPack {
    /**
     * 300 longest increasing subsequence × 2 nlogn solution
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int n: nums) {
            if (list.size() == 0 || n > list.get(list.size()-1)) {
                list.add(n);
            } else {
                int start = 0;
                int end = list.size();
                while (start < end) {
                    int mid = (start+end)/2;
                    if (list.get(mid) < n) {
                        start = mid +1 ;
                    } else {
                        end = mid;
                    }
                }
                list.set(end,n);
            }
        }
        return list.size();
    }

    public static void main(String[] args) {
        DPBackPack dpp = new DPBackPack();
        int[] nums = {9, 1, 3, 7, 8, 5, 6, 7, 20};
        dpp.lengthOfLIS(nums);
    }
}
