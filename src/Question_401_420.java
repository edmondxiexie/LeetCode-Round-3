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
//        Question_401_420 n = new Question_401_420();
//        n.readBinaryWatch(2);
        validWordAbbreviation("internationalization", "i12iz4n");
    }







}
