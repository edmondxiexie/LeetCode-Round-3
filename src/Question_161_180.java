import java.util.*;

public class Question_161_180 {
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

    public static void main(String[] args) {
        int[] nums = {-1, 0,1,3,50,75, 150};
        System.out.println(findMissingRanges(nums, -100, 4));

    }
}
