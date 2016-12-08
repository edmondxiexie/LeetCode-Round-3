import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Question_241_260 {

    /**
     * 246. Strobogrammatic Number.
     * @param num
     * @return
     */
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) {
            return true;
        }
        int left = 0;
        int right = num.length() - 1;
        while (left <= right) {
            if (!isStroboPair(num.charAt(left), num.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    public class Interval {
        int start;
        int end;
        Interval() {
            start = 0;
            end = 0;
        }
        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    /**
     * 252. Meeting Rooms.
     * @param intervals
     * @return
     */
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        for (int i = 0; i < intervals.length - 1; i++) {
            int curEnd = intervals[i].end;
            int nextStart = intervals[i + 1].start;
            if (curEnd > nextStart) {
                return false;
            }
        }
        return true;
    }

    /**
     * 253. Meeting Rooms II.
     * @param intervals
     * @return
     */
    public int minMeetingRooms(Interval[] intervals) {

    }

    /**
     * 259. 3Sum Smaller.
     * @param nums
     * @param target
     * @return
     */
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int sum = nums[i];
            int start = i + 1;
            solveThreeSumSmaller(nums, sum, target, start);
        }
        return count;
    }

    private void solveThreeSumSmaller(int[] nums, int sum, int target, int start) {
        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right && sum + nums[left] + nums[right] >= target) {
                right--;
            }
            if (left < right) {
                count += right - left;
            }
            left++;
        }
    }


    int count = 0;


    private boolean isStroboPair(char c1, char c2) {
        Map<Character, Character> map = new HashMap<>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        return map.containsKey(c1) && map.get(c1) == c2;

    }

    public static void main(String[] args) {
        int n = 1908;
        String str = Integer.toString(n);
        System.out.println(str);
    }
}
