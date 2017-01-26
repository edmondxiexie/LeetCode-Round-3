import java.util.*;

public class Question_061_080 {

    /**
     * 71. Simplify Path.
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        Stack<String> stack=new Stack<> ();
        while (path.length() > 0){
            int start = path.indexOf("/"); //第一个斜杠的位置
            path = path.substring(start+1); //Path一进被截取
            int end = path.indexOf("/");//第二个斜杠的位置
            if (end == -1)
                end = path.length();

            String part = path.substring(0, end);
            path = path.substring(end);

            // System.out.println(part+"  "+path);
            if (part.equals(".") || part.equals(""))
                continue;
            if (part.equals("..")) {
                if (!stack.isEmpty())
                    stack.pop();
            }

            else stack.push("/"+part);
        }
        String result = "";
        while (!stack.isEmpty()){
            result = stack.pop() + result;
        }
        if (result.length() == 0)
            result = "/";
        return result;
    }

    /**
     * 76. Minimum Window Substring.
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> target = new HashMap<>();
        for (char c : t.toCharArray()) {
            if (target.containsKey(c)) {
                target.put(c, target.get(c) + 1);
            } else {
                target.put(c, 1);
            }
        }
        String res = "";
        int minLeft = -1;
        int minRight = -1;
        int minLen = Integer.MAX_VALUE;
        Map<Character, Integer> found = new HashMap<>();
        int left = -1;
        int right = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            if (target.containsKey(c)) {
                if (left == -1) {
                    left = right;
                }
                if (!found.containsKey(c)) {
                    found.put(c, 1);
                } else {
                    found.put(c, found.get(c) + 1);
                }
                while (checkWindow(target, found)) {
                    if (right - left + 1 < minLen) {
                        minLen = right - left + 1;
                        res = s.substring(left, right + 1);

                    }
                    if (found.containsKey(s.charAt(left))) {
                        found.put(s.charAt(left), found.get(s.charAt(left)) - 1);
                    }
                    left++;
                }
            }
            right++;
        }
        return res;
    }

    private boolean checkWindow(Map<Character, Integer> target, Map<Character, Integer> found) {
        for (char ch : target.keySet()) {
            if (!found.containsKey(ch) || found.get(ch) < target.get(ch)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 77. Combinations.
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        if (k <= 0 || n <= 0) {
            return result;
        }
        int start = 0;
        DFSCombine(result, list, nums, start, k);
        return result;
    }

    private void DFSCombine(List<List<Integer>> result, List<Integer> list, int[] nums, int start, int k) {
        if (list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            DFSCombine(result, list, nums, i + 1, k);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 78. Subsets.
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        int start = 0;
        DFSSubsets(nums, result, list, start);
        return result;
    }

    private void DFSSubsets(int[] nums, List<List<Integer>> result, List<Integer> list, int start) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        } else {
            result.add(new ArrayList<>(list));
        }

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            DFSSubsets(nums, result, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        Question_061_080 q = new Question_061_080();
        System.out.println(q.minWindow("a", "aa"));
    }
}
