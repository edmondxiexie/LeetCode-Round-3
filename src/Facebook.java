import sun.rmi.runtime.Log;

import java.util.*;

/**
 * Created by Edmond on 7/4/17.
 */
public class Facebook {
    public int TaskSchedule(int[] tasks, int k) {
        Map<Integer, Integer> timeStamp = new HashMap<>();
        int time = 0;
        for (int i = 0; i < tasks.length; i++) {
            int task = tasks[i];
            if (!timeStamp.containsKey(task)) {
                timeStamp.put(task, time);
                time++;
            } else {
                int lastTime = timeStamp.get(task);
                while (time - lastTime < k + 1) {
                    time++;
                }
                timeStamp.put(task, time);
                time++;
            }
        }
        return time;
    }

    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> outMap = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        buildGraph(outMap, inDegree, words);
        System.out.println(outMap.toString());
        System.out.println(inDegree.toString());
        Queue<Character> queue = new LinkedList<>();
        String result = "";
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                queue.offer(c);
            }
        }
        while (!queue.isEmpty()) {
            char cur = queue.poll();
            result += cur;
            Set<Character> nexts = outMap.get(cur);
            for (char next : nexts) {
                inDegree.put(next, inDegree.get(next) - 1);
                if (inDegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }
        if (result.length() < outMap.size()) {
            return "";
        } else {
            return result;
        }
    }

    public void buildGraph(Map<Character, Set<Character>> outMap, Map<Character, Integer> inDegree, String[] words) {
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!inDegree.containsKey(c)) {
                    inDegree.put(c, 0);
                }
                if (!outMap.containsKey(c)) {
                    outMap.put(c, new HashSet<>());
                }
            }
        }

        Set<String> edges = new HashSet<>();
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            for (int j = 0; j < word1.length() && j < word2.length(); j++) {
                char from = word1.charAt(j);
                char to = word2.charAt(j);
                if (from == to) {
                    continue;
                }
                if (edges.contains(from + " " + to)) {
                    break;
                }
                outMap.get(from).add(to);
                inDegree.put(to, inDegree.get(to) + 1);
                edges.add(from + " " + to);
                break;
            }
        }
    }

    /**
     *
     * 68. Text Justification
     * @param words
     * @param maxWidth
     * @return
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length == 0 || maxWidth == 0) {
            result.add("");
            return result;
        }
        int slow = 0;
        while (slow < words.length) {
            int length = 0;
            int fast = slow;
            while (fast < words.length && length + words[fast].length() <= maxWidth) {
                length += words[fast].length() + 1;
                fast++;
            }
            length -= fast - slow;
            String tmp = "";
            int spaces = maxWidth - length;
            if (fast == words.length) {
                for (int i = slow; i < fast; i++) {
                    tmp += words[i];
                    if (spaces > 0) {
                        tmp += " ";
                    }
                    spaces--;
                }
                for (int k = 0; k < spaces; k++) {
                    tmp += " ";
                }
            } else {
                int avePadding = spaces;
                int extraPadding = 0;
                if (fast - slow - 1 > 0) {
                    avePadding = (maxWidth - length) / (fast - slow - 1);
                    extraPadding = (maxWidth - length) % (fast - slow - 1);
                }
                for (int i = slow; i < fast; i++) {
                    tmp += words[i];
                    int actualPadding = Math.min(spaces, avePadding);
                    if (extraPadding > 0) {
                        actualPadding++;
                        extraPadding--;
                    }
                    for (int k = 0; k < actualPadding; k++) {
                        tmp += " ";
                        spaces--;
                    }
                }
            }
            result.add(tmp);
            slow = fast;
        }
        return result;
    }

    /**
     * 391. Perfect Rectangle.
     * @param rectangles
     * @return
     */
    public boolean isRectangleCover(int[][] rectangles) {
        return true;
    }

    public String rearrangeString(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(map.size(), new Comparator<Node>(){
            public int compare(Node n1, Node n2) {
                if (n1.val != n2.val) {
                    return n2.val - n1.val;
                } else {
                    return n1.key - n2.key;
                }
            }
        });
        for (char c : map.keySet()) {
            pq.offer(new Node(c, map.get(c)));
        }

        int remains = s.length();

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int size = Math.min(remains, k);
            List<Node> tmp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (pq.isEmpty()) {
                    return "";
                }
                Node cur = pq.poll();
                sb.append(cur.key + "");
                cur.val = cur.val - 1;
                if (cur.val > 0) {
                    tmp.add(cur);
                }
                remains--;
            }
            for (Node n : tmp) {
                pq.offer(n);
            }
        }
        return sb.toString();

    }

    public String rearrangeString2(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>(){
            public int compare(Node n1, Node n2) {
                if (n1.val != n2.val) {
                    return n2.val - n1.val;
                } else {
                    return n1.key - n2.key;
                }
            }
        });
        for (char c : map.keySet()) {
            pq.offer(new Node(c, map.get(c)));
        }

        int remains = s.length();

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int size = Math.max(pq.size(), k);
            List<Node> tmp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (pq.isEmpty()) {
                    if (tmp.isEmpty()) {
                        return sb.toString();
                    }
                    sb.append("_");
                    continue;
                }
                Node cur = pq.poll();
                sb.append(cur.key + "");
                cur.val = cur.val - 1;
                if (cur.val > 0) {
                    tmp.add(cur);
                }
                remains--;
            }
            for (Node n : tmp) {
                pq.offer(n);
            }
        }
        return sb.toString();

    }


    public class Node{
        public char key;
        public int val;
        public Node(char k, int v) {
            key = k;
            val = v;
        }
    }

    public void moveZeroes2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] != 0) {
                left++;
            }
            while (left < right && nums[right] == 0) {
                right--;
            }
            if (left < right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
            }
        }
    }

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length()) {
            return -1;
        }
        if (needle.length() == 0) {
            return 0;
        }
        int i = 0;
        int j = 0;
        int[] next = makeNext(needle);
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                if (j == needle.length() - 1) {
                    return i - needle.length() + 1;
                }
                i++;
                j++;
            } else {
                if (j == 0) {
                    i++;
                } else {
                    j = next[j - 1];
                }
            }
        }
        return -1;
    }

    private int[] makeNext(String needle){
        char[] chars = needle.toCharArray();
        int[] next = new int[chars.length];
        next[0] = 0;
        int slow = 0;
        int fast = 1;
        while (fast < chars.length) {
            if (chars[fast] == chars[slow]) {
                next[fast] = next[fast - 1] + 1;
                fast++;
                slow++;
            } else {
                while (chars[slow] != chars[fast] && slow != 0) {
                    slow = next[slow - 1];
                }
                if (chars[slow] == chars[fast]) {
                    next[fast] = slow + 1;
                    fast++;
                    slow++;
                } else {
                    next[fast] = 0;
                    fast++;
                }
            }
        }
        System.out.println(Arrays.toString(next));
        return next;
    }

    public Point globalOrigin;

    public Point kClosest(Point[] points, Point origin, int k) {
        globalOrigin = origin;
        PriorityQueue<Point> pq = new PriorityQueue<>(k, new PointComparator());
        for (Point point : points) {
            pq.offer(point);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    public class PointComparator implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {
            int dis1 = getDistance(o1);
            int dis2 = getDistance(o2);
            if (dis1 != dis2) {
                return - (dis1 - dis2);
            } else if (o1.x != o2.x) {
                return - (o1.x - o2.x);
            } else {
                return - (o1.y - o2.y);
            }
        }
    }

    public int getDistance(Point p) {
        int result = 0;
        result += (p.x - globalOrigin.x) * (p.x - globalOrigin.x);
        result += (p.y - globalOrigin.y) * (p.y - globalOrigin.y);
        return result;
    }

    public static class Point {
        public int x;
        public int y;
        public Point() {
            x = 0;
            y = 0;
        }
        public Point(int a, int b) {
            x = a;
            y = b;
        }
        @Override
        public String toString() {
            return this.x + " " + this.y;
        }
    }

    public Point kClosest2(Point[] points, Point origin, int k) {
        PointInfo[] pointInfos = new PointInfo[points.length];
        for (int i = 0; i < pointInfos.length; i++) {
            pointInfos[i] = new PointInfo(points[i], getDistanceTwoPoints(points[i], origin));
        }
        return kClosest2Helper(pointInfos, k, 0, pointInfos.length - 1);
    }

    private Point kClosest2Helper(PointInfo[] pointInfos, int k, int start, int end) {
        if (start == end) {
            return pointInfos[start].point;
        }
        int position = partition(pointInfos, start, end);
        if (position + 1 == k) {
            return pointInfos[position].point;
        } else if (position + 1 < k) {
            return kClosest2Helper(pointInfos, k, position + 1, end);
        } else {
            return kClosest2Helper(pointInfos, k, start, position - 1);
        }

    }

    private int partition(PointInfo[] pointInfos, int start, int end) {
        int left = start;
        int right = end;
        PointInfo pivot = pointInfos[0];
        while (left < right) {
            while (left < right && pointInfos[right].compareTo(pivot) >= 0) {
                right--;
            }
            pointInfos[left] = pointInfos[right];
            while (left < right && pointInfos[left].compareTo(pivot) < 0) {
                left++;
            }
            pointInfos[right] = pointInfos[left];
        }
        pointInfos[left] = pivot;
        return left;
    }

    private int getDistanceTwoPoints(Point point, Point origin) {
        int result = 0;
        result += (point.x - origin.x) * (point.x - origin.x);
        result += (point.y - origin.y) * (point.y - origin.y);
        return result;
    }

    public class PointInfo implements Comparable<PointInfo> {
        public Point point;
        public int distance;
        public PointInfo(Point point, int distance) {
            this.point = point;
            this.distance = distance;
        }

        @Override
        public int compareTo(PointInfo other) {
            if (this.distance != other.distance) {
                return this.distance - other.distance;
            } else if (this.point.x != other.point.x) {
                return this.point.x - other.point.x;
            } else {
                return this.point.y - other.point.y;
            }
        }
    }

    public class CharNumPair {
        public char c;
        public int n;
        public CharNumPair(char c, int n) {
            this.c = c;
            this.n = n;
        }
    }

    public int[] findTwoNum(int sum) {



        for (int r = 0; r < 4; r++){
            int h = (int)(sum / (11 * Math.pow(10, r)));
            int m = (int)(sum % (11 * Math.pow(10, r)));
            int e = (int)(m / Math.pow(10, r));
            int t = (int)(m / Math.pow(10, r)) / 2;
            int n1 = (int)(h * Math.pow(10, r + 1) + e * Math.pow(10, r) + t);
            int n2 = (int)(h * Math.pow(10, r) + t);

            System.out.println(n1 + " " + n2);
        }
        return null;
    }

    /**
     * 239. Sliding Window Maximum.
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k < 1) {
            k = 1;
        }
        if (nums.length < k - 1) {
            return new int[0];
        }
        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {

            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(nums[i]);
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    public List<Function> calculateLog(LogNode[] logs) {
        final int START = 1;
        final int END = 0;
        Stack<LogNode> funcStack = new Stack<>();
        Stack<Integer> inStack = new Stack<>();
        List<Function> result = new ArrayList<>();

        funcStack.push(logs[0]);
        inStack.push(0);
        int index = 1;
        while (!funcStack.isEmpty()) {
            LogNode cur = logs[index];
            if (cur.type == START) {
                funcStack.push(cur);
                inStack.push(0);
            } else {
                LogNode lastNode = funcStack.pop();
                int lastInner = inStack.pop();
                result.add(new Function(cur.id, cur.time - lastNode.time - lastInner, cur.time - lastNode.time));
                if (!inStack.isEmpty()) {
                    int curInner = inStack.pop();
                    curInner += cur.time - lastNode.time;
                    inStack.push(curInner);
                }
            }
            index++;
        }
        System.out.println(result.toString());
        return result;
    }

    public class Function {
        public int id;
        public int inclusive;
        public int exclusive;
        public Function(int id, int inclusive, int exclusive) {
            this.id = id;
            this.inclusive = inclusive;
            this.exclusive = exclusive;
        }
        public String toString() {
            return "" + id + " " + inclusive + " " + exclusive;
        }
    }

    public static class LogNode {
        public int id;
        public int type;
        public int time;
        public LogNode(int id, int type, int time) {
            this.id = id;
            this.type = type;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        Facebook f = new Facebook();
        String haystack = "ababcaababcaabc";
        String needle = "ababcaabc";
        Point p1 = new Point(4, 6);
        Point p2 = new Point(4, 7);
        Point p3 = new Point(4, 4);
        Point p4 = new Point(2, 5);
        Point p5 = new Point(1, 1);
        Point[] points = {p1, p2, p3, p4, p5};
        Point origin = new Point(0, 0);
//        Point p0 = f.kClosest(points, origin, 3);
//        System.out.println(p0);
//        Point p01 = f.kClosest2(points, origin, 3);
//        System.out.println(p01);
//        String s = "aabbbcc";
//        int k = 3;
//        System.out.println(f.rearrangeString2(s, k));
//        f.findTwoNum(3802);
        LogNode ln1 = new LogNode(1, 1, 1);
        LogNode ln2 = new LogNode(2, 1, 2);
        LogNode ln3 = new LogNode(2, 0, 3);
        LogNode ln4 = new LogNode(3, 1, 4);
        LogNode ln5 = new LogNode(3, 0, 6);
        LogNode ln6 = new LogNode(1, 0, 9);
        LogNode[] logs = {ln1, ln2, ln3, ln4, ln5, ln6};


//        f.calculateLog(logs);
        String needle2 = "abcabdrgabcabcab";

        System.out.println(Arrays.toString(f.makeNext(needle2)));
    }
}
