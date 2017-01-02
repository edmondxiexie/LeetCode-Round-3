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
        int[] nums = {-1, 0,1,3,50,75, 150};
        System.out.println(findMissingRanges(nums, -100, 4));

    }
}
