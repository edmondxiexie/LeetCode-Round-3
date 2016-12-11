import java.util.HashMap;
import java.util.Map;

public class Question_341_360 {

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

    public static void main(String[] args) {
        Question_341_360 m = new Question_341_360();
        m.numberOfPatterns(1, 4);
    }
}
