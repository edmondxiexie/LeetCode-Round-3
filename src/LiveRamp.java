import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Edmond on 3/4/17.
 */
public class LiveRamp {
    public String solution(int A, int B, int C, int D) {
        int[] digits = new int[4];
        digits[0] = A;
        digits[1] = B;
        digits[2] = C;
        digits[3] = D;

        // check valid inputs
        for (int d : digits) {
            if (d < 0 || d > 9) {
                return "NOT POSSIBLE";
            }
        }
        List<Integer> times = new ArrayList<>();
        boolean[] visited = new boolean[digits.length];
        DFSGetValidTimes(digits, "", times, visited);
        if (times.size() == 0) {
            return "NOT POSSIBLE";
        }
        int max = 0;
        for (int t : times) {
            max = Math.max(max, t);
        }

        // conver int to String
        return buildTimeStr(max);
    }

    private void DFSGetValidTimes(int[] digits, String time, List<Integer> times, boolean[] visited) {
        if (time.length() == 4) {
            if (isValid(time)) {
                times.add(Integer.parseInt(time));
            }
            return;
        }
        for (int i = 0; i < digits.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                DFSGetValidTimes(digits, time + digits[i], times, visited);
                visited[i] = false;
            }
        }
    }

    private boolean isValid(String time) {
        int timeNum = Integer.parseInt(time);
        if (timeNum / 100 > 23) {
            return false;
        }
        if (timeNum % 100 > 59) {
            return false;
        }
        return true;
    }

    private String buildTimeStr(int time) {
        String str = "";
        for (int i = 0; i < 4; i++) {
            str = time % 10 + str;
            time = time / 10;
            if (i == 1) {
                str = ":" + str;
            }
        }
        return str;
    }
//    public int MaxTime(int[] digits) {
//        if (digits == null || digits.length != 4) {
//            return -1;
//        }
//        for (int d : digits) {
//            if (d < 0 || d > 9) {
//                return -1;
//            }
//        }
//        List<Integer> times = new ArrayList<>();
//        boolean[] visited = new boolean[digits.length];
//        DFS(digits, "", times, visited);
//        if (times.size() == 0) {
//            return -1;
//        }
//        int max = 0;
//        for (int t : times) {
//            max = Math.max(max, t);
//        }
//        return max;
//    }
//
//    private void DFS(int[] digits, String s, List<Integer> times, boolean[] visited) {
//        if (s.length() == 4) {
//            if (isValid(s)) {
//                times.add(Integer.parseInt(s));
//            }
//            return;
//        }
//        for (int i = 0; i < digits.length; i++) {
//            if (!visited[i]) {
//                visited[i] = true;
//                DFS(digits, s + digits[i], times, visited);
//                visited[i] = false;
//            }
//        }
//    }
//
//    private boolean isValid(String s) {
//        int time = Integer.parseInt(s);
//        if (time / 100 > 23) {
//            return false;
//        }
//        if (time % 100 > 60) {
//            return false;
//        }
//        return true;
//    }

    public int[] changeToSort(int[] nums) {
        int[] sort = new int[nums.length];
        System.arraycopy(nums, 0, sort, 0, nums.length);
        Arrays.sort(sort);
        int start = 0;
        int end = nums.length - 1;
        while (start < end && nums[start] == sort[start]) {
            start++;
        }
        while (start < end && nums[end] == sort[end]) {
            end--;
        }
        return new int[0];
    }

    public static void main(String[] args) {
        LiveRamp lr = new LiveRamp();
//        int[] times = {1,8,3,2};
//        int[] times = {0,0,0,0};
        int[] times = {-1,0,0,0};

        System.out.print(lr.solution(1, 8, 3, 2));
    }
}
