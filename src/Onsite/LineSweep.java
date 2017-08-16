package Onsite;

import java.util.*;

/**
 * Created by Edmond on 8/10/17.
 */
public class LineSweep {

    /**
     * Given k sorted array has n posts, find the shortest interval contains at least 1 post from each array ?
     * @param lists
     * @return
     * http://www.geeksforgeeks.org/find-smallest-range-containing-elements-from-k-lists/
     */
    public int[] findSmallestRange(int[][] lists) {
        PriorityQueue<Num> pq = new PriorityQueue<>(new Comparator<Num>() {
            @Override
            public int compare(Num o1, Num o2) {
                return o1.val - o2.val;
            }
        });
        Map<Integer, Integer> map = new HashMap<>();
        List<Num> list = new ArrayList<>();
        int[] result = new int[2];
        for (int i = 0; i < lists.length; i++) {
            for (int j = 0; j < lists[i].length; j++) {
                list.add(new Num(lists[i][j], j, i));
            }
        }
        Collections.sort(list, new Comparator<Num>() {
            @Override
            public int compare(Num o1, Num o2) {
                return o1.val - o2.val;
            }
        });
        int minRange = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        while (right < list.size()) {
            Num rightNum = list.get(right);
            if (!map.containsKey(rightNum.from)) {
                map.put(rightNum.from, 1);
            } else {
                map.put(rightNum.from, map.get(rightNum.from) + 1);
            }
            while (map.size() == lists.length) {
                if (list.get(right).val - list.get(left).val < minRange) {
                    minRange = list.get(right).val - list.get(left).val;
                    result[0] = list.get(left).val;
                    result[1] = list.get(right).val;
                }
                Num leftNum = list.get(left);
                map.put(leftNum.from, map.get(leftNum.from) - 1);
                if (map.get(leftNum.from) == 0) {
                    map.remove(leftNum.from);
                }
                left++;
            }
            right++;
        }
        return result;
    }

    public class Num {
        public int val;
        public int index;
        public int from;
        public Num(int v, int i, int f) {
            val = v;
            index = i;
            from = f;
        }
    }

    /**
     * 给一个区间的2d array，每一row表示一个接受方，给一个整数，求所有包含这个整数的接 受方
     * @param intervals
     * @param target
     * @return
     * 例子就是
            A想要接收1到4、7到9、12到15
            B想要接收2到8、10到12
            C想要接收5到6

            如果给一个数字8，应该返回A和B
            如果给个数字5，应该返回B和C
     */
    public Interval findInterval(List<Interval> intervals, int target) {
        int start = 0;
        int end = intervals.size() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (intervals.get(mid).start > target) {
                end = mid - 1;
            } else if (intervals.get(mid).end < target) {
                start = mid + 1;
            } else {
                return intervals.get(mid);
            }
        }
        return null;
    }

    public static class Interval {
        public int start;
        public int end;
        public Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return start + " " + end;
        }
    }

    public static void main(String[] args) {
        LineSweep ls = new LineSweep();
        int[] nums1 = {4, 7, 9, 12, 15};
        int[] nums2 = {0, 8, 10, 14, 20};
        int[] nums3 = {6, 12, 16, 30, 50};
        int[][] ns = {nums1, nums2, nums3};
        System.out.println(Arrays.toString(ls.findSmallestRange(ns)));
    }
}
