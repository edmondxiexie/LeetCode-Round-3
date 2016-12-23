import java.util.*;

public class Question_281_300 {

    static final int INF = Integer.MAX_VALUE;

    /**
     * 286. Walls and Gates.
     * @param rooms
     */
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0
            || rooms[0] == null || rooms[0].length == 0) {
            return;
        }
        int rows = rooms.length;
        int cols = rooms[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rooms[i][j] == 0) {
                    DFSWallsAndGates(rooms, i, j, 0, i, j);
                }
            }
        }
    }

    private void DFSWallsAndGates(int[][] rooms, int row, int col, int cur, int startRow, int startCol) {

        if (row < 0 || row >= rooms.length
            || col < 0 || col >= rooms[0].length) {
            return;
        }
        if (rooms[row][col] == -1) {
            return;
        }
        if (rooms[row][col] == 0 && (row != startRow || col != startCol)) {
            return;
        }
        if (rooms[row][col] == INF) {
            rooms[row][col] = cur;
        } else if (rooms[row][col] > 0) {
            int oldDis = rooms[row][col];
            if (oldDis < cur) {
                return;
            } else {
                rooms[row][col] = cur;
            }
        }

        int[] dx = {0, 0, -1, +1};
        int[] dy = {+1, -1, 0, 0};
        for (int i = 0; i < 4; i++) {
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];
            DFSWallsAndGates(rooms, nextRow, nextCol, cur + 1, startRow, startCol);
        }
    }

    /**
     * 287. Find the Duplicate Number.
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        // Similar to find loop in linkedlist.
        if (nums.length <= 1) {
            return -1;
        }
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }

    /**
     * 289. Game of Life.
     * @param board
     */
    public static void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = getLife(board, i, j);
            }
        }
        board = result;
    }

    private static int getLife(int[][] board, int row, int col) {
        int startRow = row - 1;
        int startCol = col - 1;
        int lives = 0;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (i < 0 || i >= board.length
                    || j < 0 || j >= board[0].length) {
                    continue;
                }
                if (i == row && j == col) {
                    continue;
                }
                if (board[i][j] == 1) {
                    lives++;
                }
            }
        }
        if (board[row][col] == 1) {
            if (lives < 2) {
                return 0;
            } else if (lives == 2 || lives == 3) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (lives == 3) {
                return 1;
            }
        }
        return board[row][col];
    }

    /**
     * 290. Word Pattern.
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        String[] strs = str.split(" ");
        if (pattern.length() != strs.length) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (!map.containsKey(c)) {
                if (map.containsValue(strs[i])) {
                    return false;
                }
                map.put(c, strs[i]);
            } else {
                if (!map.get(c).equals(strs[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 293. Flip Game.
     * @param s
     * @return
     */
    public List<String> generatePossibleNextMoves(String s) {
        List<String> result = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length() - 1; i++) {
            if (chars[i] == chars[i + 1] && chars[i] == '+') {
                chars[i] = '-';
                chars[i + 1] = '-';
                result.add(new String(chars));
                chars[i] = '+';
                chars[i + 1] = '+';
            }
        }
        return result;
    }

    /**
     * 294. Flip Game II.
     * @param s
     * @return
     */
    public boolean canWin(String s) {
        if (s.length() < 2) {
            return false;
        }
        boolean cluster = false;
        int start = -1;
        int end = -1;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1) && s.charAt(i) == '+') {
                if (i != end && cluster) {
                    return false;
                }
                if (!cluster) {
                    cluster = true;
                    start = i;
                }
                end = i + 1;
            }
        }
        int len = end - start + 1;
        return len >= 2 && len <= 4;
    }

    /**
     * 295. Find Median from Data Stream.
     */
    public static class MedianFinder {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        // Adds a number into the data structure.
        public void addNum(int num) {
            if (minHeap.isEmpty()) {
                minHeap.offer(num);
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(num);
                if (maxHeap.peek() < minHeap.peek()) {
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(minHeap.poll());
                }
            } else {
                minHeap.offer(num);
                if (maxHeap.peek() < minHeap.peek()) {
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(minHeap.poll());
                }
            }
        }

        // Returns the median of current data stream
        public double findMedian() {
            if (maxHeap.size() == 0) {
                return minHeap.peek();
            }
            if (minHeap.size() == maxHeap.size()) {
                double first = minHeap.peek();
                double second = maxHeap.peek();
                return first + (second - first) / 2;
            } else {
                return minHeap.peek();
            }
        }
    };

    /**
     * 299. Bulls and Cows.
     * @param secret
     * @param guess
     * @return
     */
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < Math.min(secret.length(), guess.length()); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            }
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : secret.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (char c : guess.toCharArray()) {
            if (map.containsKey(c) && map.get(c) > 0) {
                cows++;
                map.put(c, map.get(c) - 1);
            }
        }
        return bulls + "A" + (cows - bulls) + "B";
    }

    public static void main(String[] args) {
        int[] nums = {4,3,1,4,2};
//        System.out.println(findDuplicate(nums));
        int[][] n = {{1}};
//        gameOfLife(n);
//        System.out.println(Arrays.toString(n));
        MedianFinder m = new MedianFinder();
        m.addNum(1);
        m.addNum(2);
        m.findMedian();
        m.addNum(3);
        m.findMedian();
    }
}
