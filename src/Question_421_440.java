import java.util.*;

public class Question_421_440 {

    /**
     * 219. Contains Duplicate II.
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        // <num>
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.size() > k) {
                set.remove(nums[i - k - 1]);
            }
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }
        return false;
    }

    /**
     * 421. Maximum XOR of Two Numbers in an Array.
     * @param nums
     * @return
     */
    public static int findMaximumXOR(int[] nums) {
        int max = 0;
        int mask = 0;
        for (int i = 31; i >= 0; i--) {
            //mask: 1111000..00
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                int prefix = num & mask;
                set.add(prefix);
            }

            // guess a max number
            int guess = max | (1 << i);
            System.out.println(mask+"  "+guess+"  "+max);
            for(int prefix : set){
                // a^b = c ->a^c=b,b^c=a
                if(set.contains(guess ^ prefix)) {
                    max = guess;
                    break;
                }
            }
        }
        return max;
    }

    /**
     * 422. Valid Word Square.
     * @param words
     * @return
     */
    public boolean validWordSquare(List<String> words) {
        int rows = words.size();
        int cols = 0;
        for (String word : words) {
            cols = Math.max(cols, word.length());
        }
        if (rows != cols) {
            return false;
        }
        char[][] matrix = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j >= words.get(i).length()) {
                    matrix[i][j] = '$';
                } else {
                    matrix[i][j] = words.get(i).charAt(j);
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {3, 10, 5, 25, 2, 8};
        findMaximumXOR(nums);
    }
}
