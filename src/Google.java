import java.util.Stack;

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

    public static void main(String[] args) {
        String s = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        System.out.println(lengthLongestPath(s));
    }
}

