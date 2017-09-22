package GOnSite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Edmond on 9/21/17.
 */
public class Question_9_21 {
    /**
     * 315. Count of Smaller Numbers After Self.
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        int[] count = new int[nums.length];
        int[] oriIndex = new int[nums.length];
        for (int i = 0; i < oriIndex.length; i++) {
            oriIndex[i] = i;
        }
        mergeSort(nums, count, oriIndex, 0, nums.length - 1);
        List<Integer> result = new ArrayList<>();
        for (int c : count) {
            result.add(c);
        }
        return result;
    }

    private void mergeSort(int[] nums, int[] count, int[] oriIndex, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(nums, count, oriIndex, start, mid);
        mergeSort(nums, count, oriIndex, mid + 1, end);
        merge(nums, count, oriIndex, start, mid, mid + 1, end);
    }

    private void merge(int[] nums, int[] count, int[] oriIndex, int start1, int end1, int start2, int end2) {
        int[] tmp = new int[end2 - start1 + 1];
        int i = 0;
        int cur1 = start1;
        int cur2 = start2;
        int rightCount = 0;
        while (i < tmp.length) {
            if (cur1 > end1) {
                tmp[i] = oriIndex[cur2];
                cur2++;
            } else if (cur2 > end2) {
                tmp[i] = oriIndex[cur1];
                count[oriIndex[cur1]] += rightCount;
                cur1++;
            } else if (nums[oriIndex[cur1]] > nums[oriIndex[cur2]]) {
                rightCount++;
                tmp[i] = oriIndex[cur2];
                cur2++;
            } else {
                tmp[i] = oriIndex[cur1];
                count[oriIndex[cur1]] += rightCount;
                cur1++;
            }
            i++;
        }
        for (int k = 0; k < tmp.length; k++) {
            oriIndex[k + start1] = tmp[k];
        }
    }

    /**
     * 465. Optimal Account Balancing.
     */
    public int min = Integer.MAX_VALUE;

    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] tran : transactions) {

            if (!map.containsKey(tran[0])) {
                map.put(tran[0], 0);
            }
            map.put(tran[0], map.get(tran[0]) - tran[2]);

            if (!map.containsKey(tran[1])) {
                map.put(tran[1], 0);
            }
            map.put(tran[1], map.get(tran[1]) + tran[2]);
        }
        List<Integer> debts = new ArrayList<>();
        for (int val : map.values()) {
            if (val != 0) {
                debts.add(val);
            }
        }

        DFS(debts, 0, 0);

        return min;
    }

    public void DFS(List<Integer> debts, int start, int round) {
        while (start < debts.size() && debts.get(start) == 0) {
            start++;
        }
        if (start == debts.size()) {
            min = Math.min(min, round);
            return;
        }
        for (int i = start + 1; i < debts.size(); i++) {
            int cur = debts.get(start);

            int next = debts.get(i);
            if ((cur > 0 && next < 0) || (cur < 0 && next > 0)) {
                debts.set(i, debts.get(i) + cur);
                DFS(debts, start + 1, round + 1);
                debts.set(i, debts.get(i) - cur);
            }
        }
    }

    public static void main(String[] args) {
        Question_9_21 q = new Question_9_21();
        int[] nums = {5, 2, 4, 6, 1, 3};
//        System.out.println(q.countSmaller(nums).toString());
        int[][] trans = {{1,8,1},{1,13,21},{2,8,10},{3,9,20},{4,10,61},{5,11,61},{6,12,59},{7,13,60}};
        System.out.println(q.minTransfers(trans));
    }
}
