package PureStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Edmond on 9/5/17.
 */
public class OA {
    public static final int START = 0;
    public static final int END = 1;

    public boolean checkLocks(String[] events) {


        List<Log> logs = new ArrayList<>();
        for (String event : events) {
            logs.add(getLog(event));
        }
        Stack<Log> stack = new Stack<>();
        for (Log log : logs) {
            if (log.type == START) {
                stack.push(log);
            } else {
                if (stack.isEmpty() || stack.peek().id != log.id) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public Log getLog(String event) {
        String[] strs = event.split(" ");
        int id = Integer.parseInt(strs[1]);
        int type = (strs[0].equals("ACQUIRE")) ? START : END;
        Log log = new Log(id, type);
        return log;
    }

    public static class Log {
        public int id;
        public int type;
        public Log(int id, int type) {
            this.id = id;
            this.type = type;
        }
    }

    public static void main(String[] args) {
        OA oa = new OA();
        Log log1 = new Log(364, START);
        Log log2 = new Log(84, START);
        Log log3 = new Log(84, END);
        Log log4 = new Log(1337, START);
        Log log5 = new Log(1337, END);
        Log log6 = new Log(364, END);
        List<Log> list = new ArrayList<>();
        list.add(log1);
        list.add(log2);
        list.add(log3);
        list.add(log4);
        list.add(log5);
        list.add(log6);

        Log log7 = new Log(364, START);
        Log log8 = new Log(84, START);
        Log log9 = new Log(364, END);
        Log log10 = new Log(84, END);
        List<Log> list2 = new ArrayList<>();
        list2.add(log7);
        list2.add(log8);
        list2.add(log9);
        list2.add(log10);

        String[] strings = {"ACQUIRE 364", "ACQUIRE 84", "ACQUIRE 1337", "RELEASE 1337", "RELEASE 364"};

//        System.out.println(oa.checkLocks(strings));
        System.out.println((-5) % 3);
    }

}
