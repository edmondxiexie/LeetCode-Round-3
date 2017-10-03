package GOnSite;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by Edmond on 9/28/17.
 */
public class Question_9_28 {

    public String nextClosestTime(String time) {
        Set<Character> digits = new HashSet<>();
        for (char c : time.toCharArray()) {
            if (c >= '0' && c <= '9') {
                digits.add(c);
            }
        }
        Comparator<String> timeComparator = new TimeComparator();
        PriorityQueue<String> pq = new PriorityQueue<>(timeComparator);
        char[] times = new char[5];
        DFS(digits, pq, times, 0);
        String first = pq.peek();
        while (!pq.isEmpty() && timeComparator.compare(pq.peek(), time) <= 0) {
            pq.poll();
        }
        if (pq.isEmpty()) {
            return first;
        }
        return pq.peek();

    }

    private void DFS(Set<Character> digits, PriorityQueue<String> pq, char[] times, int start) {
        if (start == 2) {
            times[2] = ':';
            DFS(digits, pq, times, start + 1);
            return;
        }
        if (start == 5) {
            if (isValid(times)) {
                pq.offer(new String(times));
            }
            return;
        }
        for (char c : digits) {
            times[start] = c;
            DFS(digits, pq, times, start + 1);
        }
    }

    private boolean isValid(char[] times) {
        int hour = (times[0] - '0') * 10 + (times[1] - '0');
        int min = (times[3] - '0') * 10 + (times[4] - '0');
        return hour >= 0 && hour < 24 && min >= 0 && min < 60;
    }

    public class TimeComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            String[] timeStr1 = o1.split(":");
            String[] timeStr2 = o2.split(":");
            int hour1 = Integer.parseInt(timeStr1[0]);
            int hour2 = Integer.parseInt(timeStr2[0]);
            int min1 = Integer.parseInt(timeStr1[1]);
            int min2 = Integer.parseInt(timeStr2[1]);

            if (hour1 != hour2) {
                return hour1 - hour2;
            } else {
                return min1 - min2;
            }

        }
    }

    public static void main(String[] args) {
        Question_9_28 q = new Question_9_28();
        System.out.println(q.nextClosestTime("23:59"));
//        System.out.println(('1' - '0') * 10 + '1');
    }
}
