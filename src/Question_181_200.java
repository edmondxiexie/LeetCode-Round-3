public class Question_181_200 {
    /**
     * 186. Reverse Words in a String II.
     * @param s
     */
    public void reverseWords(char[] s) {
        int left = 0;
        int right = s.length - 1;
        reverseWord(s, left, right);
        left = 0;
        right = 0;
        while (right <= s.length) {
            if (right == s.length || s[right] == ' ') {
                reverseWord(s, left, right - 1);
                left = right + 1;
                right++;
            }
            right++;
        }
    }

    private void reverseWord(char[] s, int start, int end) {
        while (start < end) {
            char tmp = s[start];
            s[start] = s[end];
            s[end] = tmp;
            start++;
            end--;
        }
    }

    /**
     * 198. House Robber.
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int last = nums[0];
        int cur = Math.max(last, nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int tmp = cur;
            cur = Math.max(last + nums[i], cur);
            last = tmp;
        }
        return cur;
    }
}
