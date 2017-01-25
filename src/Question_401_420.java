import java.util.*;

public class Question_401_420 {

    /**
     * 401. Binary Watch.
     * @param num
     * @return
     */
    public List<String> readBinaryWatch(int num) {
        List<String> result = new ArrayList<>();
        if (num > 10) {
            return result;
        }
        int[] hours = {1, 2, 4, 8};
        int[] mins = {1, 2, 4, 8, 16, 32};
        for (int h = 0; h <= 4 && h <= num; h++) {
            int m = num - h;
            if (h < 0 || m < 0) {
                continue;
            }
            Set<String> hourSet = new HashSet<>();
            Set<String> minSet = new HashSet<>();
            findBinaryWatch(hours, hourSet, 0, 0, h);
            findBinaryWatch(mins, minSet, 0, 0, m);
            for (String hour : hourSet) {
                for (String min : minSet) {
                    String time = "";
                    if (Integer.parseInt(hour) > 12 || Integer.parseInt(min) > 60) {
                        continue;
                    }
                    time += hour + ":";
                    time += (min.length() == 1) ? "0" + min : min;
                    if (time.equals("12:00")) {
                        continue;
                    }
                    result.add(time);
                }
            }
        }
        return result;
    }

    private void findBinaryWatch(int[] nums, Set<String> set, int start, int sum, int n) {
        if (n == 0) {
            set.add(sum + "");
            return;
        }
        for (int i = start; i < nums.length; i++) {
            findBinaryWatch(nums, set, i + 1, sum + nums[i], n - 1);
        }
    }

    /**
     * 402. Remove K Digits.
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        while (i < num.length()) {
            char c = num.charAt(i);
            if (count < k && !stack.isEmpty() && stack.peek() > (c - '0')) {
                stack.pop();
                count++;
            } else {
                stack.push(c - '0');
                i++;
            }

        }
        while (count < k) {
            stack.pop();
            count++;
        }
        String res = "";
        while (!stack.isEmpty()) {
            res = stack.pop() + res;
        }
        int zero = 0;
        while (zero < res.length() && res.charAt(zero) == '0') {
            zero++;
        }
        res = res.substring(zero);
        if (res.length() == 0) {
            return "0";
        } else {
            return res;
        }
    }

    /**
     * 404. Sum of Left Leaves.
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        DFSSumOfLeftLeaves(root, false);
        return sumOfLeft;
    }

    private int sumOfLeft = 0;

    private void DFSSumOfLeftLeaves(TreeNode root, boolean isLeft) {
        if (root.left == null && root.right == null && isLeft) {
            sumOfLeft += root.val;
            return;
        }
        if (root.left != null) {
            DFSSumOfLeftLeaves(root.left, true);
        }
        if (root.right != null) {
            DFSSumOfLeftLeaves(root.right, false);
        }
    }

    /**
     * 405. Convert a Number to Hexadecimal.
     * @param num
     * @return
     */
    public String toHex(int num) {
        if (num == 0) return "0";
        StringBuilder res = new StringBuilder();

        while (num != 0) {
            int digit = num & 0xf;
            res.append(digit < 10 ? (char)(digit + '0') : (char)(digit - 10 + 'a'));
            num >>>= 4;
        }

        return res.reverse().toString();
    }

    /**
     * 406. Queue Reconstruction by Height.
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        // people <height, k>
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return - (o1[0] - o2[0]);
                }
            }
        });
        List<int[]> result = new ArrayList<>();
        for (int[] e : people) {
            result.add(e[1], e);
        }
        return result.toArray(new int[people.length][]);
    }

    /**
     * 408. Valid Word Abbreviation.
     * @param word
     * @param abbr
     * @return
     */
    public static boolean validWordAbbreviation(String word, String abbr) {
        int wordIndex = 0;
        int abbrIndex = 0;
        while (abbrIndex < abbr.length() && wordIndex < word.length()) {
            if (abbr.charAt(abbrIndex) > '0' && abbr.charAt(abbrIndex) <= '9') {
                int end = abbrIndex + 1;
                while (end < abbr.length() && abbr.charAt(end) >= '0' && abbr.charAt(end) <= '9') {
                    end++;
                }
                int num = Integer.parseInt(abbr.substring(abbrIndex, end));
                for (int i = 0; i < num; i++) {
                    wordIndex++;
                }
                abbrIndex = end;
            } else {
                if (abbr.charAt(abbrIndex) != word.charAt(wordIndex)) {
                    return false;
                }
                abbrIndex++;
                wordIndex++;
            }
        }
        return (wordIndex == word.length() && abbrIndex == abbr.length());
    }

    /**
     * 409. Longest Palindrome.
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        int[] letters = new int[128];
        for (char c : s.toCharArray()) {
            letters[c]++;
        }
        int single = 0;
        int count = 0;
        for (int n : letters) {
            if (n == 0) {
                continue;
            } else if (n == 1) {
                single++;
            } else if (n % 2 == 0) {
                count += n;
            } else {
                if (single == 0) {
                    single++;
                }
                count += n - 1;
            }
        }
        if (single == 0) {
            return count;
        } else {
            return count + 1;
        }
    }

    /**
     * 412. Fizz Buzz.
     * @param n
     * @return
     */
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                result.add("FizzBuzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else {
                result.add(i + "");
            }
        }
        return result;
    }

    /**
     * 413. Arithmetic Slices.
     * @param A
     * @return
     */
    public static int numberOfArithmeticSlices(int[] A) {
        int[] dp = new int[A.length];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = A[i] - A[i - 1];
        }
        int result = 0;
        int count = 1;
        for (int i = 2; i < dp.length + 1; i++) {
            if (i == dp.length || dp[i] != dp[i - 1]) {
                int len = count + 1;
                if (len >= 3) {
                    result += (int)((len - 2 + 1) * (len - 2) / 2.0);
                }
                count = 1;
            } else {
                count++;
            }
        }
        return result;
    }

    /**
     * 414. Third Maximum Number.
     * @param nums
     * @return
     */
    public int thirdMax(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();
        for(int n : nums) {
            if(set.add(n)) {
                pq.offer(n);
                if(pq.size() > 3 ) pq.poll();
            }
        }
        if(pq.size() == 2) pq.poll();
        return pq.peek();
    }


    /**
     * 415. Add Strings.
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        int i1 = num1.length() - 1;
        int i2 = num2.length() - 1;
        int carry = 0;
        String res = "";
        while (i1 >= 0 || i2 >= 0 || carry > 0) {
            if (i1 < 0 && i2 < 0) {
                res = carry + res;
                carry = 0;
            } else if (i1 < 0) {
                int tmp = ((num2.charAt(i2) - '0')) + carry;
                res = tmp % 10 + res;
                carry = tmp / 10;
                i2--;
            } else if (i2 < 0) {
                int tmp = ((num1.charAt(i1) - '0')) + carry;
                res = tmp % 10 + res;
                carry = tmp / 10;
                i1--;
            } else {
                int tmp = (num1.charAt(i1) - '0' + (num2.charAt(i2) - '0')) + carry;
                res = tmp % 10 + res;
                carry = tmp / 10;
                i1--;
                i2--;
            }
        }
        return res;
    }

    /**
     * 417. Pacific Atlantic Water Flow.
     * @param matrix
     * @return
     */
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if (matrix.length == 0) {
            return res;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] p = new boolean[rows][cols];
        boolean[][] a = new boolean[rows][cols];

        boolean[][] visited;
        for (int i = 0; i < rows; i++) {
            p[i][0] = true;
            a[i][cols - 1] = true;
            visited = new boolean[rows][cols];
            DFSHelper(matrix, p, i, 0, visited);
            visited = new boolean[rows][cols];
            DFSHelper(matrix, a, i, cols - 1, visited);
        }

        for (int i = 0; i < cols; i++) {
            p[0][i] = true;
            a[rows - 1][i] = true;
            visited = new boolean[rows][cols];
            DFSHelper(matrix, p, 0, i, visited);
            visited = new boolean[rows][cols];
            DFSHelper(matrix, a, rows - 1, i, visited);
        }

        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++)
                if(a[i][j] && p[i][j])
                {
                    int[] temp = new int[2];
                    temp[0] = i;
                    temp[1] = j;
                    res.add(temp);
                }
        return res;
    }

    public void DFSHelper(int[][] matrix,boolean[][] map,int row, int col, boolean[][] visited) {

        if (visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        map[row][col] = true;
        int cur = matrix[row][col];

        if (row > 0 && matrix[row - 1][col] >= cur) {
            DFSHelper(matrix, map, row - 1, col, visited);
        }
        if (col > 0 && matrix[row][col-1] >= cur) {
            DFSHelper(matrix, map, row, col - 1, visited);
        }
        if (row < map.length-1 && matrix[row + 1][col] >= cur) {
            DFSHelper(matrix, map, row + 1, col, visited);
        }
        if (col < map[0].length-1 && matrix[row][col + 1] >= cur) {
            DFSHelper(matrix, map, row, col + 1, visited);
        }
    }

    /**
     * 419. Battleships in a Board.
     * @param board
     * @return
     */
    public int countBattleships(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return 0;
        }
        int rows = board.length;
        int cols = board[0].length;
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (i > 0 && board[i - 1][j] == 'X') {
                    continue;
                }
                if (j > 0 && board[i][j - 1] == 'X') {
                    continue;
                }
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Question_401_420 n = new Question_401_420();
//        n.readBinaryWatch(2);
//        validWordAbbreviation("internationalization", "i12iz4n");
//        int[] nums = {1,2,3,4};
//        System.out.println(numberOfArithmeticSlices(nums));
        System.out.println(n.removeKdigits("1432219", 3));
    }







}
