import java.util.*;

public class Question_341_360 {

    /**
     * 342. Power of Four.
     * @param num
     * @return
     */
    public boolean isPowerOfFour(int num) {
        return (Math.log10(num) / Math.log10(4)) % 1 == 0;
    }

    /**
     * 343. Integer Break.
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2] * 2, dp[i - 3] * 3);
        }
        return dp[n];
    }

    /**
     * 349. Intersection of Two Arrays.
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p1 = 0;
        int p2 = 0;
        List<Integer> list = new ArrayList<>();
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums2[p2] < nums1[p1]) {
                p2++;
            } else {
                list.add(nums1[p1]);
                p1++;
                p2++;
                while (p1 < nums1.length && nums1[p1] == nums1[p1 - 1]) {
                    p1++;
                }
                while (p2 < nums2.length && nums2[p2] == nums2[p2 - 1]) {
                    p2++;
                }
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 350. Intersection of Two Arrays II.
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p1 = 0;
        int p2 = 0;
        List<Integer> list = new ArrayList<>();
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums2[p2] < nums1[p1]) {
                p2++;
            } else {
                list.add(nums1[p1]);
                p1++;
                p2++;
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 351. Android Unlock Patterns.
     * @param m
     * @param n
     * @return
     */
    int patterns = 0;

    public int numberOfPatterns(int m, int n) {
        if (m > n) {
            return patterns;
        }
        boolean[] keypad = new boolean[10];
        int step = 0;
        for (int i = 1; i <= 9; i++) {
            keypad[i] = true;
            DFSNumberOfPatterns(keypad, i, step + 1, m, n);
            keypad[i] = false;
        }
        return patterns;
    }

    private void DFSNumberOfPatterns(boolean[] keypad, int from, int step, int lower, int upper) {
        if (step == upper) {
            patterns++;
            return;
        }
        if (step >= lower) {
            patterns++;
        }
        for (int to = 1; to <= 9; to++) {
            if (isValidPath(keypad, from, to)) {

                keypad[to] = true;
                DFSNumberOfPatterns(keypad, to, step + 1, lower, upper);
                keypad[to] = false;
            }
        }
    }

    private boolean isValidPath(boolean[] keypad, int from, int to) {
        int s = Math.min(from, to);
        int l = Math.max(from, to);
        if (s == l) {
            return false;
        }
        if (keypad[to]) {
            return false;
        }
        if ((s == 1 && l == 9) || (s == 3 && l == 7)) {
            return keypad[5];
        }
        if (s <= 3 && l == s + 6) {
            return keypad[s + 3];
        }
        if ((s == 1 || s == 4 || s == 7) && l == s + 2) {
            return keypad[s + 1];
        }
        return true;
    }

    /**
     * 354. Russian Doll Envelopes.
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) {
            return 0;
        }
        int[] dp = new int[envelopes.length];
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int max = 0;
        for (int i = 0; i < envelopes.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0]
                        && envelopes[i][1] > envelopes[j][1]
                        && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 356. Line Reflection.
     * @param points
     * @return
     */
    public boolean isReflected(int[][] points) {
        if (points.length <= 1) {
            return true;
        }
        int min = points[0][0];
        int max = points[0][0];
        for (int[] point : points) {
            min = Math.min(min, point[0]);
            max = Math.max(max, point[0]);
        }
        int sum = min + max;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] point : points) {
            if (!map.containsKey(point[0])) {
                map.put(point[0], new HashSet<>());
            }
            map.get(point[0]).add(point[1]);
        }
        for (int[] point : points) {
            int anotherX = sum - point[0];
            if (!map.containsKey(anotherX) || !map.get(anotherX).contains(point[1])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 359. Logger Rate Limiter.
     */
    public class Logger {
        Map<String, Integer> timeMap;

        /** Initialize your data structure here. */
        public Logger() {
            timeMap = new HashMap<>();
        }

        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
         If this method returns false, the message will not be printed.
         The timestamp is in seconds granularity. */
        public boolean shouldPrintMessage(int timestamp, String message) {
            if (!timeMap.containsKey(message)) {
                timeMap.put(message, timestamp);
                return true;
            }
            int lastTime = timeMap.get(message);
            if (timestamp - lastTime >= 10) {
                timeMap.put(message, timestamp);
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 360. Sort Transformed Array.
     * @param nums
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] result = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int index = (a >= 0) ? nums.length - 1 : 0;
        while (left <= right) {
            int leftVal = transform(nums[left], a, b, c);
            int rightVal = transform(nums[right], a, b, c);
            if (a >= 0) {
                if (leftVal > rightVal) {
                    result[index] = leftVal;
                    left++;
                } else {
                    result[index] = rightVal;
                    right--;
                }
                index--;
            } else {
                if (leftVal < rightVal) {
                    result[index] = leftVal;
                    left++;
                } else {
                    result[index] = rightVal;
                    right--;
                }
                index++;
            }
        }
        return result;
    }

    private int transform(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }


    public static void main(String[] args) {
        Question_341_360 m = new Question_341_360();
        m.numberOfPatterns(1, 4);
    }
}
