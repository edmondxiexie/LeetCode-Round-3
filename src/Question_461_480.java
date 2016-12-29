import java.util.Arrays;

/**
 * Created by Edmond on 12/9/16.
 */
public class Question_461_480 {

    /**
     * 461. Hamming Distance.
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        int tmp = x ^ y;
        int count = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            count += tmp & mask;
            tmp = tmp >> 1;
        }
        return count;
    }

    /**
     * 462. Minimum Moves to Equal Array Elements II.
     * @param nums
     * @return
     */
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int total = 0;
        while (left < right) {
            total += nums[right] - nums[left];
            left++;
            right--;
        }
        return total;
    }

    /**
     * 463. Island Perimeter.
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int neighboor = countSurrounding(grid, i, j);
                    perimeter += (4 - neighboor);
                }
            }
        }
        return perimeter;
    }

    private int countSurrounding(int[][] grid, int row, int col) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int count = 0;
        for (int i = 0; i < dx.length; i++) {
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];
            if (nextRow >= 0 && nextRow < grid.length
                && nextCol >= 0 && nextCol < grid[0].length
                && grid[nextRow][nextCol] == 1) {
                count++;
            }
        }
        return count;
    }
}
