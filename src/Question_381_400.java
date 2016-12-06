import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Question_381_400 {

    /**
     * 394. Decode String.
     * @param s
     * @return
     */
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        String result = "";
        if (s == null || s.length() == 0) {
            return result;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                String str = "";
                while (stack.peek() != '[') {
                    str = stack.pop() + str;
                }
                stack.pop();
                String numStr = "";
                while (!stack.isEmpty()
                        && stack.peek() >= '0'
                        && stack.peek() <= '9') {
                    numStr = stack.pop() + numStr;
                }
                int num = Integer.parseInt(numStr);
                String tmp = "";
                for (int j = 0; j < num; j++) {
                    tmp += str;
                }
                if (stack.isEmpty()) {
                    result += tmp;
                } else {
                    for (Character c : tmp.toCharArray()) {
                        stack.push(c);
                    }
                }
            } else {
                stack.push(s.charAt(i));
            }
        }
        String tmp = "";
        while (!stack.isEmpty()) {
            tmp = stack.pop() + tmp;
        }
        return result + tmp;
    }
}
