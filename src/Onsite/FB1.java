package Onsite;

import java.util.*;

/**
 * Created by Edmond on 8/6/17.
 */
public class FB1 {
    /**
    组合问题的变种，给定的数组是一个无重复质数的数组，求所有无重复的乘积
     */
    public List<Integer> primeProduction(int[] primes) {
        List<Integer> result = new ArrayList<>();
        if (primes == null || primes.length == 0) {
            return result;
        }
        primeProductionDFS(primes, result, 0, 1, 0);
        Collections.sort(result);
        return result;
    }

    private void primeProductionDFS(int[] primes, List<Integer> result, int start, int pro, int nums) {
        if (start == primes.length && nums >= 2) {
            result.add(pro);
            return;
        }
        if (start < primes.length && nums >= 2) {
            result.add(pro);
        }
        for (int i = start; i < primes.length; i++) {
            primeProductionDFS(primes, result, i + 1, pro * primes[i], nums + 1);
        }
    }

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

    /**
     * 122. Best Time to Buy and Sell Stock II follow up
     * @param prices
     * @return
     */
    public int maxProfitWithFee(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[] hold = new int[prices.length];
        int[] empty = new int[prices.length];
        hold[0] = -prices[0] - fee;
        empty[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            hold[i] = Math.max(hold[i - 1], empty[i - 1] - prices[i] - fee);
            empty[i] = Math.max(empty[i - 1], hold[i - 1] + prices[i] - fee);
        }
        return Math.max(hold[prices.length - 1], empty[prices.length - 1]);
    }

    public void shuffle(int[] nums) {
        Random random = new Random();
        for (int i = nums.length - 1; i >= 0; i--) {
            int ran = random.nextInt(i + 1);
            int tmp = nums[i];
            nums[i] = nums[ran];
            nums[ran] = tmp;
        }
    }



    public static void main(String[] args) {
        FB1 fb1 = new FB1();
//        int[] primes = {2, 3, 5};
        Interval i1 = new Interval(1, 4);
        Interval i2 = new Interval(7, 9);
        Interval i3 = new Interval(12, 15);
        List<Interval> intervals = new ArrayList<>();
        intervals.add(i1);
        intervals.add(i2);
        intervals.add(i3);
//        System.out.println(fb1.findInterval(intervals, 8));
        int[] prices = {1, 2, 3, 5, 1, 4, 8};
//        System.out.println(fb1.maxProfitWithFee(prices, 1));
        int[] r1 = {1, -3, 3};
        int[] r2 = {0, -2, 0};
        int[] r3 = {-3, -3, -3};
        int[][] dungeon = {r1, r2, r3};
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        fb1.shuffle(nums);
        System.out.println(Arrays.toString(nums));
    }
}
