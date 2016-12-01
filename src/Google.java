import java.util.*;

/**
 * Created by Edmond on 11/26/16.
 */
public class Google {
    /**
     * 388. Longest Absolute File Path.
     * @param S
     * @return
     */
    public static int lengthLongestPath(String S) {
        if (S == null || S.length() == 0) {
            return 0;
        }
        int max = 0;
        int sumLen = 0;
        String[] strs = S.split("\n");
        Stack<Integer> stack = new Stack<>();
        for (String str : strs) {
            int level = countLevel(str);
            while (level < stack.size()) {
                sumLen = sumLen - stack.pop();
            }
            int len = str.replaceAll(" ", "").length() + 1;
            sumLen = sumLen + len;
            if (str.contains(".")) {
                //.jpeg .png .gif
                int dotIndex = str.indexOf(".");
                String extension = str.substring(dotIndex);
                if (extension.equals(".jpeg")
                        || extension.equals(".png")
                        || extension.equals(".gif")) {
                    max = Math.max(max, sumLen - len);
                }
            }
            stack.push(len);
        }
        return max;
    }

    private static int countLevel(String s) {
        String trim = s.replaceAll(" ", "");
        return s.length() - trim.length();
    }

    /**
     * 340. Longest Substring with At Most K Distinct Characters.
     * @param s
     * @param k
     * @return
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int max = 0;
        while (end < s.length()) {
            char c = s.charAt(end);
            map.put(c, end);
            while (map.size() > k) {
                char startChar =  s.charAt(start);
                if (map.get(startChar) != start) {
                    start++;
                } else {
                    map.remove(startChar);
                    start++;
                }
            }
            max = Math.max(max, end - start + 1);
            end++;
        }
        return max;
    }

    /**
     *
     * @param sentence
     * @param rows
     * @param cols
     * @return
     */
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int start = 0;
        int len = s.length();
        for (int i = 0; i < rows; i++) {
            start += len;
            if (s.charAt(start % len) == ' ') {
                start++;
            } else {
                while (start > 0 && s.charAt((start - 1) % len) != ' ') {
                    start--;
                }
            }
        }
        return start % len;
    }

    public static int solution(int X) {
        if (X == 0) {
            return 0;
        }
        int sign = X / Math.abs(X);
        String str = Integer.toString(Math.abs(X));
        int len = str.length();
        int max = Integer.MIN_VALUE;
        if (len <= 1) {
            return 0;
        }
        for (int i = 0; i < len - 1; i++) {
            int first = Integer.parseInt(str.substring(i, i + 1));
            int second = Integer.parseInt(str.substring(i + 1, i + 2));
            int ave = roundUpAve(first, second);
            int newNum = sign * Integer.parseInt(str.substring(0, i) + ave + str.substring(i + 2));
            max = Math.max(max, newNum);
        }
        return max;
    }

    public static int roundUpAve(int n1, int n2) {
        int ave = (n1 + n2) / 2;
        if (ave * 2 < n1 + n2) {
            ave++;
        }
        return ave;
    }

    /**
     * 346. Moving Average from Data Stream.
     */
    public static class MovingAverage {
        private Queue<Integer> queue;
        private int capacity;
        private double sum;

        /** Initialize your data structure here. */
        public MovingAverage(int size) {
            queue = new LinkedList<>();
            capacity = size;
            sum = 0;
        }

        public double next(int val) {
            queue.offer(val);
            sum += val;
            if (queue.size() > capacity) {
                sum -= queue.poll();
            }
            return sum / queue.size();
        }
    }

    /**
     * 361. Bomb Enemy.
     * @param grid
     * @return
     */
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int max = 0;
        int[][] rowsCount = new int[rows][cols];
        int[][] colsCount = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            countRows(grid, rowsCount, i);
        }
        for (int j = 0; j < cols; j++) {
            countCols(grid, colsCount, j);
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != '0') {
                    continue;
                }
                int count = rowsCount[i][j] + colsCount[i][j];
                if (grid[i][j] == 'E') {
                    count--;
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }

    private void countRows(char[][] grid, int[][] rowsCount, int i) {
        int cols = grid[0].length;
        int count = 0;

        int start = 0;
        int end = 0;
        while (end <= cols) {
            if (end == cols || grid[i][end] == 'W') {
                for (int k = start; k < end; k++) {
                    rowsCount[i][k] = count;
                }
                count = 0;
                start = end + 1;
            } else if (grid[i][end] == 'E') {
                count++;
            }
            end++;
        }
    }

    private void countCols(char[][] grid, int[][] colsCount, int j) {
        int cols = grid.length;
        int count = 0;

        int start = 0;
        int end = 0;
        while (end <= cols) {
            if (end == cols || grid[end][j] == 'W') {
                for (int k = start; k < end; k++) {
                    colsCount[k][j] = count;
                }
                count = 0;
                start = end + 1;
            } else if (grid[end][j] == 'E') {
                count++;
            }
            end++;
        }
    }

    /**
     * Merge Sort.
     * @param nums
     */
    public static void mergeSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int start = 0;
        int end = nums.length - 1;
        mergeSort(nums, start, end);
    }

    private static void mergeSort(int[] nums, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        merge(nums, start, mid, mid + 1, end);
    }

    private static void merge(int[] nums, int start1, int end1, int start2, int end2) {
        int cur1 = start1;
        int cur2 = start2;
        int[] tmpArray = new int[end2 - start1 + 1];
        for (int i = 0; i < tmpArray.length; i++) {
            if (cur1 > end1) {
                tmpArray[i] = nums[cur2];
                cur2++;
            } else if (cur2 > end2) {
                tmpArray[i] = nums[cur1];
                cur1++;
            } else if (nums[cur1] <= nums[cur2]) {
                tmpArray[i] = nums[cur1];
                cur1++;
            } else {
                tmpArray[i] = nums[cur2];
                cur2++;
            }
        }
        for (int i = 0; i < tmpArray.length; i++) {
            nums[start1 + i] = tmpArray[i];
        }
    }

    public static void main(String[] args) {
        String s = "dir\n\tsubdir1\n\t\tfile1.jpeg\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
//        System.out.println(lengthLongestPath(s));
//        System.out.println(roundUpAve(3, 2));
//        System.out.println(solution(-623315));
//        System.out.println(solution(-613));
//        System.out.println(solution(0));
        String s1 = "dir1\n\tdir11\n\tdir12\n\t\tpicture.jpeg\n\t\tdir121\n\t\tfile1.txt\ndir2\n\tfile2.gif";
//        System.out.println(lengthLongestPath(s1));
//
        String s2 = "dir1\n dir11\n dir12\n  picture.png\n  dir121\n  file1.txt\ndir2\n file2.gif";
        System.out.println(lengthLongestPath(s2));


        String s3 = "dir1\n dir11\n dir12\n  picture.txt\n  dir121\n  file1.txt\ndir2\n file2.doc";
        System.out.println(lengthLongestPath(s3));
        MovingAverage m = new MovingAverage(3);

        //        dir1
//            dir11
//            dir12
//                picture.jpeg
//                dir121
//                file1.txt
//        dir2
//            file2.gif
//        System.out.println(m.next(1));
//        System.out.println(m.next(10));
//        System.out.println(m.next(3));
//        System.out.println(m.next(5));

//        int[] nums = {5, 9, 8};

        int[] nums = {5, 9, 8, 3, 1, 1, 0, 6};
        mergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}

