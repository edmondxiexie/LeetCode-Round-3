import java.lang.reflect.Array;
import java.util.*;

public class Question_421_440 {



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

    /**
     * 423. Reconstruct Original Digits from English.
     * @param s
     * @return
     */
    public String originalDigits(String s) {
        int[] count = new int[10];
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == 'z') count[0]++;
            if (c == 'w') count[2]++;
            if (c == 'x') count[6]++;
            if (c == 's') count[7]++; //7-6
            if (c == 'g') count[8]++;
            if (c == 'u') count[4]++;
            if (c == 'f') count[5]++; //5-4
            if (c == 'h') count[3]++; //3-8
            if (c == 'i') count[9]++; //9-8-5-6
            if (c == 'o') count[1]++; //1-0-2-4
        }
        count[7] -= count[6];
        count[5] -= count[4];
        count[3] -= count[8];
        count[9] = count[9] - count[8] - count[5] - count[6];
        count[1] = count[1] - count[0] - count[2] - count[4];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 9; i++){
            for (int j = 0; j < count[i]; j++){
                sb.append(i);
            }
        }
        return sb.toString();
    }

    /**
     * 424. Longest Repeating Character Replacement.
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] letters = new int[26];
        int maxLen = 0;
        int maxLenLetter = 0;
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            letters[cur - 'A']++;
            maxLenLetter = Math.max(maxLenLetter, letters[cur - 'A']);
            len++;
            if (len - maxLenLetter > k) {
                int leftBound = i - len + 1;
                char removeLetter = s.charAt(leftBound);
                letters[removeLetter - 'A']--;
                len--;
                for (int e : letters) {
                    maxLenLetter = Math.max(maxLenLetter, e);
                }
            }
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {3, 10, 5, 25, 2, 8};
        findMaximumXOR(nums);
    }
}
