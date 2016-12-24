import java.util.*;

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

    /**
     * 396. Rotate Function.
     * @param A
     * @return
     */
    public int maxRotateFunction(int[] A) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            nums.add(i);
        }
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            int sum = 0;
            for (int j = 0; j < A.length; j++) {
                sum += A[j] * nums.get(j);
            }
            max = (i == 0) ? sum : Math.max(max, sum);
            int num = nums.get(0);
            nums.remove(0);
            nums.add(num);
        }
        return max;
    }

    /**
     * 399. Evaluate Division.
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        //<p1, <p2, value>>
        Map<String, Map<String, Double>> mainMap = new HashMap<>();
        Set<String> strSet = new HashSet<>();
        for (int i = 0; i < equations.length; i++) {
            String p1 = equations[i][0];
            String p2 = equations[i][1];
            strSet.add(p1);
            strSet.add(p2);
            Map<String, Double> subMap;

            // 1 正序
            if (mainMap.containsKey(p1)) {
                subMap = mainMap.get(p1);
            } else {
                subMap = new HashMap<>();
            }
            subMap.put(p2, values[i]);
            mainMap.put(p1, subMap);

            // 2 倒序
            if (mainMap.containsKey(p2)) {
                subMap = mainMap.get(p2);
            } else {
                subMap = new HashMap<>();
            }
            subMap.put(p1, 1.0 / values[i]);
            mainMap.put(p2, subMap);
        }

        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            Map<String, Boolean> visited = new HashMap<>();
            for (String s : strSet) {
                visited.put(s, false);
            }
            String start = queries[i][0];
            String end = queries[i][1];
            if (strSet.contains(start) && start.equals(end)) {
                result[i] = 1.0;
                continue;
            }

            result[i] = DFSCalcEquation(mainMap, visited, 1.0, start, end);
        }
        return result;
    }

    private double DFSCalcEquation(Map<String, Map<String, Double>> mainMap,
                                   Map<String, Boolean> visited, double res, String start, String end) {
        if (mainMap.containsKey(start) && !visited.get(start)) {
            visited.put(start, true);
            Map<String, Double> subMap = mainMap.get(start);
            if (subMap.containsKey(end)) {
                return res * subMap.get(end);
            }
            for (String q2 : subMap.keySet()) {
                double nextRes = DFSCalcEquation(mainMap, visited, res * subMap.get(q2),
                                                    q2, end);
                if (nextRes != -1.0) {
                    return nextRes;
                }
            }
        }
        return -1.0;
    }


    //
//    private Map<String, Map<String, Double>> map = new HashMap<>();//邻接表
//
//    /**
//     * 399. Evaluate Division.
//     * @param equations
//     * @param values
//     * @param query
//     * @return
//     */
//    public double[] calcEquation(String[][] equations, double[] values, String[][] query) {
//        Set<String> set = new HashSet<String>();//记录表达式中出现的字符串
//        for (int i = 0; i < equations.length; i++) {//建图
//            set.add(equations[i][0]);
//            set.add(equations[i][1]);
//            Map<String, Double> m;
//            if (map.containsKey(equations[i][0])) {
//                m = map.get(equations[i][0]);
//            } else {
//                m = new HashMap<String, Double>();
//            }
//            m.put(equations[i][1], values[i]);
//            map.put(equations[i][0], m);
//
//            if (map.containsKey(equations[i][1])) {
//                m = map.get(equations[i][1]);
//            } else {
//                m = new HashMap<String, Double>();
//            }
//            m.put(equations[i][0], 1.0 / values[i]);
//            map.put(equations[i][1], m);
//
//        }
//
//        double result[] = new double[query.length];
//        for (int i = 0; i < query.length; i++) {
//
//            //初始化visited标记
//            Iterator<String> it = set.iterator();
//            Map<String, Boolean> visited = new HashMap<String, Boolean>();
//            while (it.hasNext()) {
//                visited.put(it.next(), false);
//            }
//
//            if (query[i][0].equals(query[i][1]) && set.contains(query[i][0])) {
//                result[i] = 1;
//                continue;
//            }
//            //dfs
//            double res = dfs(query[i][0], query[i][1], 1, visited);
//            result[i] = res;
//        }
//        return result;
//    }
//
//    public double dfs(String s, String t, double res, Map<String, Boolean> visited) {
//        if (map.containsKey(s) && !visited.get(s)) {
//            visited.put(s, true);
//            Map<String, Double> m = map.get(s);
//            if (m.containsKey(t)) {
//                return res * m.get(t);
//            } else {
//                Iterator<String> keys = m.keySet().iterator();
//                while (keys.hasNext()) {
//                    String key = keys.next();
//                    double state = dfs(key, t, res * m.get(key), visited);
//                    if (state != -1.0) {
//                        return state;
//                    }
//                }
//            }
//        } else {
//            return -1.0;
//        }
//        return -1.0;
//    }
}
