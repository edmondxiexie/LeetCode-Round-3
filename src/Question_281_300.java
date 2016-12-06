import java.util.Arrays;

public class Question_281_300 {

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

    public static void main(String[] args) {
        int[] nums = {4,3,1,4,2};
//        System.out.println(findDuplicate(nums));
        int[][] n = {{1}};
        gameOfLife(n);
        System.out.println(Arrays.toString(n));
    }
}
