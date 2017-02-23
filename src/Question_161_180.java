import java.util.*;

public class Question_161_180 {

    /**
     * 161. One Edit Distance.
     * @param s
     * @param t
     * @return
     */
    public boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) > 1) {
            return false;
        }
        if (s.equals(t)) {
            return false;
        }
        if (s.length() == 0 || t.length() == 0) {
            return true;
        }
        int i = 0;
        int j = 0;
        int diff = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                if (s.length() > t.length()) {
                    i++;
                } else if (s.length() < t.length()) {
                    j++;
                } else {
                    i++;
                    j++;
                }
                diff++;
                if (diff > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 163. Missing Ranges.
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();

        if (nums == null || lower > upper) {
            return result;
        }
        Arrays.sort(nums);
        int next = lower;
        int first = nums[0];
        int last = nums[nums.length - 1];
        if (first > upper || last < lower) {
            result.add(getRange(lower, upper));
            return result;
        }

        for (int n : nums) {
            if (n < lower) {
                continue;
            }
            if (n > upper) {
                String tmp = getRange(next, upper);
                if (tmp != null) {
                    result.add(tmp);
                }

                break;
            }
            if (n != next) {
                String tmp = getRange(next, n - 1);
                if (tmp != null) {
                    result.add(tmp);
                }
            }
            next = n + 1;
            if (next > upper) {
                break;
            }
        }

        if (last < upper) {
            String tmp = getRange(last + 1, upper);
            if (tmp != null) {
                result.add(tmp);
            }
        }

        return result;
    }

    private static String getRange(int start, int end) {
        if (start == end) {
            return start + "";
        } else if (start < end) {
            return start + "->" + end;
        } else {
            return null;
        }
    }

    /**
     * 164. Maximum Gap.
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int len = nums.length;
        int min = nums[0];
        int max = nums[0];
        for (int n : nums) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        if (min == max) {
            return 0;
        }
        double ave = (double)(max - min) / (len - 1);
        if (ave == 0) {
            ave = 1;
        }
        int[] minList = new int[len];
        int[] maxList = new int[len];
        Arrays.fill(minList, -1);
        Arrays.fill(maxList, -1);
        for (int n : nums) {
            int i = (int)((n - min) / ave);
            minList[i] = min(minList[i], n);
            maxList[i] = max(maxList[i], n);
        }
        int res = 0;
        int left = 0;
        int right = 1;
        while (right < len) {
            while (right < len && minList[right] == -1) {
                right++;
            }
            if (right >= len) {
                break;
            }
            res = Math.max(res, minList[right] - maxList[left]);
            left = right;
            right++;
        }
        return res;
    }

    private int min(int a, int b) {
        if (a == -1) {
            return b;
        }
        if (b == -1) {
            return a;
        }
        return (a < b) ? a : b;
    }

    private int max(int a, int b) {
        if (a == -1) {
            return b;
        }
        if (b == -1) {
            return a;
        }
        return (a > b) ? a : b;
    }

    /**
     * 169. Majority Element.
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;
        for (int n : nums) {
            if (count == 0) {
                candidate = n;
                count++;
            } else if (candidate == n) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }

    public static void main(String[] args) {
        Question_161_180 q = new Question_161_180();
        int[] nums = {1, 3, 5, 9};
        System.out.println(q.maximumGap(nums));


    }
}
