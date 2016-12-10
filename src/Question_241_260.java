import java.util.*;

public class Question_241_260 {

    /**
     * 242. Valid Anagram.
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] chars = new int[128];
        for (char c : s.toCharArray()) {
            chars[c]++;
        }
        for (char c : t.toCharArray()) {
            chars[c]--;
            if (chars[c] < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 246. Strobogrammatic Number.
     * @param num
     * @return
     */
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) {
            return true;
        }
        int left = 0;
        int right = num.length() - 1;
        while (left <= right) {
            if (!isStroboPair(num.charAt(left), num.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 247. Strobogrammatic Number II.
     * @param n
     * @return
     */
    public List<String> findStrobogrammatic(int n) {
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put('0', '0');
        pairs.put('8', '8');
        pairs.put('6', '9');
        pairs.put('9', '6');
        pairs.put('1', '1');
        List<String> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        int left = 0;
        int right = n - 1;
        char[] word = new char[n];
        DFSFindStrobogrammatic(n, left, right, result, word, pairs);
        return result;
    }

    private void DFSFindStrobogrammatic(int n, int left, int right, List<String> result, char[] word,
                                        Map<Character, Character> pairs) {
        if (left > right) {
            result.add(new String(word));
            return;
        }
        if (left == right) {
            word[left] = '0';
            result.add(new String(word));
            word[left] = '1';
            result.add(new String(word));
            word[left] = '8';
            result.add(new String(word));
            return;
        }
        for (Character c : pairs.keySet()) {
            if (c == '0' && left == 0) {
                continue;
            }
            word[left] = c;
            word[right] = pairs.get(c);
            DFSFindStrobogrammatic(n, left + 1, right - 1, result, word, pairs);
        }
    }

    public class Interval {
        int start;
        int end;
        Interval() {
            start = 0;
            end = 0;
        }
        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    /**
     * 252. Meeting Rooms.
     * @param intervals
     * @return
     */
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        for (int i = 0; i < intervals.length - 1; i++) {
            int curEnd = intervals[i].end;
            int nextStart = intervals[i + 1].start;
            if (curEnd > nextStart) {
                return false;
            }
        }
        return true;
    }

    public class timeNode {
        int type;
        int time;
        public timeNode(int type, int time) {
            this.time = time;
            this.type = type;
        }
    }

    /**
     * 253. Meeting Rooms II.
     * @param intervals
     * @return
     */
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        List<timeNode> nodes = new ArrayList<>();
        for (Interval e : intervals) {
            nodes.add(new timeNode(1, e.start));
            nodes.add(new timeNode(0, e.end));
        }
        Collections.sort(nodes, new Comparator<timeNode>() {
            @Override
            public int compare(timeNode o1, timeNode o2) {
                if (o1.time == o2.time) {
                    return o1.type - o2.type;
                } else {
                    return o1.time - o2.time;
                }
            }
        });
        int max = 0;
        int rooms = 0;
        for (timeNode node : nodes) {
            if (node.type == 1) {
                rooms++;
            } else {
                rooms--;
            }
            max = Math.max(max, rooms);
        }
        return max;
    }

    /**
     * 259. 3Sum Smaller.
     * @param nums
     * @param target
     * @return
     */
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int sum = nums[i];
            int start = i + 1;
            solveThreeSumSmaller(nums, sum, target, start);
        }
        return count;
    }

    private void solveThreeSumSmaller(int[] nums, int sum, int target, int start) {
        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right && sum + nums[left] + nums[right] >= target) {
                right--;
            }
            if (left < right) {
                count += right - left;
            }
            left++;
        }
    }


    int count = 0;


    private boolean isStroboPair(char c1, char c2) {
        Map<Character, Character> map = new HashMap<>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        return map.containsKey(c1) && map.get(c1) == c2;

    }

    public static void main(String[] args) {
        int n = 1908;
        String str = Integer.toString(n);
        System.out.println(str);
    }
}
