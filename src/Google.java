import java.util.*;

/**
 * Created by Edmond on 11/26/16.
 */
public class Google {
    /**
     * 388. Longest Absolute File Path.
     * @param input
     * @return
     */
    public static int lengthLongestPath(String input) {
        int max = 0;
        int sumLen = 0;
        String[] strs = input.split("\n");
        Stack<Integer> stack = new Stack<>();
        for (String str : strs) {
            int level = countLevel(str);
            while (level < stack.size()) {
                sumLen = sumLen - stack.pop();
            }
            int len = str.replaceAll("\t", "").length() + 1;
            sumLen = sumLen + len;
            if (str.contains(".")) {
                max = Math.max(max, sumLen - 1);
            }
            stack.push(len);
        }
        return max;
    }

    private static int countLevel(String s) {
        String trim = s.replaceAll("\t", "");
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

    public static void main(String[] args) {
        String s = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        System.out.println(lengthLongestPath(s));
    }
}

