import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edmond on 8/23/17.
 */
public class Question_621_640 {

    /**
     * 621. Task Scheduler. Fixed order
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        int time = 0;
        int i = 0;
        while (i < tasks.length) {
            if (!map.containsKey(tasks[i])) {
                map.put(tasks[i], time);
                i++;
            } else {
                if (time - map.get(tasks[i]) > n) {
                    map.put(tasks[i], time);
                    i++;
                }
            }
            time++;
        }
        return time;
    }

    /**
     * 640. Solve the Equation.
     * @param equation
     * @return
     */
    public String solveEquation(String equation) {
        String[] equations = equation.split("=");

        int[] coeffLeft = new int[2];
        int[] coeffRight = new int[2];

        getCoefficient(equations[0], coeffLeft);
        getCoefficient(equations[1], coeffRight);

        int a = coeffLeft[0] - coeffRight[0];
        int b = coeffLeft[1] - coeffRight[1];

        System.out.println(a);
        System.out.println(b);

        if (a == 0) {
            if (b == 0) {
                return "Infinite solutions";
            } else {
                return "No solution";
            }
        } else {
            return "x=" + (-b / a);
        }
    }

    public void getCoefficient(String equation, int[] coeff) {
        int sign = 1;
        int tmp = 0;
        boolean isCo = false;
        for (int i = 0; i <= equation.length(); i++) {
            if (i == equation.length()) {
                coeff[1] += tmp * sign;
                break;
            }
            char cur = equation.charAt(i);
            if (cur == '+') {
                coeff[1] += tmp * sign;
                tmp = 0;
                sign = 1;
                isCo = false;
            } else if (cur == '-') {
                coeff[1] += tmp * sign;
                tmp = 0;
                sign = -1;
                isCo = false;
            } else if (cur == 'x') {
                if (isCo) {
                    coeff[0] += tmp * sign;
                    tmp = 0;
                    sign = 1;
                } else {
                    coeff[0] += sign;
                    sign = 1;
                }
                isCo = false;
            } else {
                isCo = true;
                tmp = tmp * 10 + (cur - '0');
            }
        }
    }

    public static void main(String[] args) {
        Question_621_640 q = new Question_621_640();
        int[] result = new int[2];
        String e = "1+x";
//        q.getCoefficient(e, result);
        char[] chars = {'1', '1', '2', '1'};
        System.out.println(q.leastInterval(chars, 2));

    }
}
