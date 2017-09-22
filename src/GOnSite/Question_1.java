package GOnSite;

import java.util.*;

/**
 * Created by Edmond on 9/10/17.
 */
public class Question_1 {

    /**
     * 425. Word Squares
     */

    private TrieNode root;
    private int cols;

    public List<List<String>> wordSquares(String[] words) {
        root = new TrieNode();
        cols = words[0].length();
        for (String word : words) {
            add(word);
        }
        List<List<String>> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for (String word : words) {
            list.add(word);
            if (word.length() <= 1) {
                result.add(new ArrayList<>(list));
            } else {
                searchPrefix(result, list, word.charAt(1) + "");
            }
            list.remove(list.size() - 1);
        }
        //
        for (List<String> l : result) {
            System.out.println(l.toString());
            System.out.println();
        }
        return result;
    }

    private void searchPrefix(List<List<String>> result, List<String> list, String prefix) {
        if (prefix.length() == cols) {
            result.add(new ArrayList<>(list));
            return;
        }
        List<String> nexts = search(prefix);
        for (String next : nexts) {
            list.add(next);
            if (list.size() < cols) {
                String nextPrefix = "";
                for (int i = 0; i < list.size(); i++) {
                    nextPrefix += list.get(i).charAt(list.size());
                }
                searchPrefix(result, list, nextPrefix);
            } else {
                result.add(new ArrayList<>(list));
            }
            list.remove(list.size() - 1);
        }
    }

    public class TrieNode {
        public boolean isWord;
        public Map<Character, TrieNode> children;
        public TrieNode() {
            isWord = false;
            children = new HashMap<>();
        }
    }

    public void add(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new TrieNode());
            }
            cur = cur.children.get(c);
            if (i == word.length() - 1) {
                cur.isWord = true;
            }
        }
    }

    public List<String> search(String prefix) {
        TrieNode cur = root;
        List<String> suffixes = new ArrayList<>();
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!cur.children.containsKey(c)) {
                return new ArrayList<>();
            }
            cur = cur.children.get(c);
        }
        getSuffix(cur, suffixes, "");
        List<String> result = new ArrayList<>();
        for (String suffix : suffixes) {
            result.add(prefix + suffix);
        }
        return result;
    }

    public void getSuffix(TrieNode root, List<String> list, String tmp) {
        if (root.isWord) {
            list.add(tmp);
            return;
        }
        for (char c : root.children.keySet()) {
            getSuffix(root.children.get(c), list, tmp + c);
        }
    }

    /**
     * 394. Decode String.
     * @param s
     * @return
     */
    public String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        Stack<Integer> countStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        String curString = "";
        int curCount = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                curCount = curCount * 10 + (c - '0');
            } else if (c == '[') {
                countStack.push(curCount);
                curCount = 0;
                stringStack.push(curString);
                curString = "";
            } else if (Character.isAlphabetic(c)) {
                if (countStack.isEmpty()) {
                    result.append(c);
                } else {
                    curString += c;
                }
            } else if (c == ']') {
                int times = countStack.pop();
                if (countStack.isEmpty()) {
                    for (int i = 0; i < times; i++) {
                        result.append(curString);
                    }
                    curString = "";
                } else {
                    String tmp = "";
                    for (int i = 0; i < times; i++) {
                        tmp += curString;
                    }
                    curString = stringStack.pop() + tmp;
                }
            }
        }
        return result.toString();

    }

    /**
     * 305. Number of Islands II.
     * @param m
     * @param n
     * @param positions
     * @return
     */
    public int[][] matrix;

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        matrix = new int[m][n];
        int islands = 0;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        UnionFind uf = new UnionFind(m * n);
        List<Integer> result = new ArrayList<>();
        for (int[] position : positions) {
            int x = position[0];
            int y = position[1];
            if (matrix[x][y] == 0) {
                matrix[x][y] = 1;
                islands++;
                for (int i = 0; i < dx.length; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix[0].length
                            && matrix[nx][ny] == 1
                            && !uf.isConnect(getId(x, y), getId(nx, ny))) {
                        uf.union(getId(x, y), getId(nx, ny));
                        islands--;
                    }
                }
            }
            result.add(islands);
        }
        return result;
    }

    public int getId(int x, int y) {
        return x * matrix[0].length + y;
    }

    public class UnionFind {
        int[] roots;

        public UnionFind(int n) {
            roots = new int[n];
            for (int i = 0; i < n; i++) {
                roots[i] = i;
            }
        }

        public int find(int id) {
            if (id != roots[id]) {
                return roots[id] = find(roots[id]);
            }
            return id;
        }

        public void union(int id1, int id2) {
            int root1 = find(id1);
            int root2 = find(id2);
            if (root1 != root2) {
                roots[root1] = root2;
            }
        }

        public boolean isConnect(int id1, int id2) {
            int root1 = find(id1);
            int root2 = find(id2);
            return root1 == root2;
        }
    }

    /**
     * 391. Perfect Rectangle.
     * @param rectangles
     * @return
     */
    public boolean isRectangleCover(int[][] rectangles) {
        int sumArea = 0;
        int[] bigRect = new int[4];
        for (int i = 0; i < 4; i++) {
            bigRect[i] = rectangles[0][i];
        }
        Map<Point, Integer> map = new HashMap<>();
        for (int[] rect : rectangles) {
            int[] xs = {rect[0], rect[2]};
            int[] ys = {rect[1], rect[3]};
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    Point p = new Point(xs[i], ys[j]);
                    if (!map.containsKey(p)) {
                        map.put(p, 1);
                    } else {
                        map.put(p, map.get(p) + 1);
                    }
                }
            }
            sumArea += Math.abs(rect[3] - rect[1]) * Math.abs(rect[2] - rect[0]);
            bigRect[0] = Math.min(bigRect[0], rect[0]);
            bigRect[1] = Math.min(bigRect[1], rect[1]);
            bigRect[2] = Math.max(bigRect[2], rect[2]);
            bigRect[3] = Math.max(bigRect[3], rect[3]);
        }
        int bigArea = Math.abs(bigRect[3] - bigRect[1]) * Math.abs(bigRect[2] - bigRect[0]);
        if (bigArea != sumArea) {
            return false;
        }
        int[] xs = {bigRect[0], bigRect[2]};
        int[] ys = {bigRect[1], bigRect[3]};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Point p = new Point(xs[i], ys[j]);
                if (!map.containsKey(p) || map.get(p) != 1) {
                    return false;
                }
            }
        }
        int corners = 0;
        for (Integer n : map.values()) {
            if (n % 2 == 1) {
                corners++;
                if (corners > 4) {
                    return false;
                }
            }
        }
        return true;
    }

    public class Point {
        public int x;
        public int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return (x + "_" + y).hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof Point)) {
                return false;
            }
            return this.hashCode() == ((Point)obj).hashCode();
        }
    }

    /**
     * 471. Encode String with Shortest Length.
     * @param s
     * @return
     */
    public String encode(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        String[][] dp = new String[n][n];
        for (int len = 1; len <= n; len++) {
            if (len == 10) {
                int a = 1;
            }
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                String word = s.substring(i, j + 1);
                dp[i][j] = word;
                if (len <= 4) {
                    continue;
                }
                for (int k = i; k < j; k++) {
                    if (dp[i][k].length() + dp[k + 1][j].length() < dp[i][j].length()) {
                        dp[i][j] = dp[i][k] + dp[k + 1][j];
                    }
                }
                String tmp = word + word;
                int second = tmp.indexOf(word, 1);
                if (second > 0 && second < len) {
                    int times = len / (second);
                    String sub = dp[i][i + second - 1];
                    String encoded = times + "[" + sub + "]";
                    if (encoded.length() < dp[i][j].length()) {
                        dp[i][j] = encoded;
                    }
                }
            }
        }
        return dp[0][s.length() - 1];
    }


    /**
     * 163. Missing Ranges
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        if (nums.length == 0) {
            addRange(ans, lower, upper, lower, upper);
            return ans;
        }

        addRange(ans, lower, (long) nums[0] - 1, lower, upper);

        for (int i = 1; i < nums.length; i++) {
            addRange(ans, (long) nums[i - 1] + 1, (long) nums[i] - 1, lower, upper);
        }
        addRange(ans, (long) nums[nums.length - 1] + 1, upper, lower, upper);

        return ans;
    }

    public void addRange(List<String> ans, long start, long end, int lower, int upper) {
        if (start < lower) {
            start = lower;
        }
        if (end > upper) {
            end = upper;
        }

        if (start > end) {
            return;
        }
        if (start == end) {
            ans.add(start + "");
            return;
        }
        ans.add(start + "->" + end);
    }

    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            String shiftStr = getShift(str);
            if (!map.containsKey(shiftStr)) {
                map.put(shiftStr, new ArrayList<>());
            }
            map.get(shiftStr).add(str);
        }
        List<List<String>> result = new ArrayList<>();
        result.addAll(map.values());
        return result;
    }

    public String getShift(String str) {
        char[] chars = str.toCharArray();
        int shift = chars[0] - 'a';
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] - shift < 'a') {
                chars[i] = (char)(chars[i] + (26 - shift));
            } else {
                chars[i] = (char) (chars[i] - shift);
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        Question_1 q = new Question_1();
//        String[] words = {"area","lead","wall","lady","ball"};
//        q.wordSquares(words);
//        String[] words1 = {"a"};
//        q.wordSquares(words1);

        String str = "3[a2[c]]ef";
//        System.out.println(q.decodeString(str));
        int[][] pos = {{0,0}, {0,1}, {1,2}, {2,1}};
//        System.out.println(q.numIslands2(3, 3, pos).toString());
        int[][] rects = {{1,1,3,3}, {3,1,4,2}, {3,2,4,4}, {1,3,2,4}, {2,3,3,4}};
//        System.out.println(q.isRectangleCover(rects));
        String word = "abababababab";
//        System.out.println(word.indexOf("ababab", 1));
        String s = "aaaaaaaaaa";

//        System.out.println(q.encode(s));
        int[] nums = {0, 1, 3, 50, 75};
//        System.out.println(q.findMissingRanges(nums, 2, 99));
        String shift = "bcd";
        String[] strs = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        System.out.println((q.groupStrings(strs)).toString());
    }
}
