import java.util.*;

public class Question_201_220 {

    /**
     * 204. Count Primes.
     * @param n
     * @return
     */
    public static int countPrimes(int n) {
        int count = 0;
        boolean[] record = new boolean[n];
        Arrays.fill(record, true);
        for (int i = 2; i < n; i++) {
            if (!record[i]) {
                continue;
            }
            count++;
            for (int j = i; j < n; j += i) {
                record[j] = false;
            }
        }
        return count;
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }
        for (int i = 3; i < Math.pow(n, 0.5) + 1; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 205. Isomorphic Strings.
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> pairs = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (pairs.containsKey(sChar)) {
                if (tChar != pairs.get(sChar)) {
                    return false;
                }
            } else {
                if (pairs.containsValue(tChar)) {
                    return false;
                }
                pairs.put(sChar, tChar);
            }
        }
        return true;
    }

    /**
     * 207. Course Schedule.
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return true;
    }

    /**
     * 216. Combination Sum III.
     * @param k
     * @param n
     * @return
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        DFSCombinationSum3(res, list, k, n, 1);
        return res;
    }

    private static void DFSCombinationSum3(List<List<Integer>> res, List<Integer> list, int k, int target, int start) {
        if (list.size() == k - 1 && target >= start && target <= 9) {
            list.add(target);
            res.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        for (int n = start; n < 9; n++) {
            list.add(n);
            DFSCombinationSum3(res, list, k, target - n, n + 1);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 219. Contains Duplicate II.
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        // <num>
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.size() > k) {
                set.remove(nums[i - k - 1]);
            }
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }
        return false;
    }

    /**
     * 218. The Skyline Problem.
     * @param
     * @return
     */
    public class BuildingNode {
        // start : 1, end : 0
        public int type;
        public int height;
        public int pos;
        public BuildingNode(int t, int p, int h) {
            type = t;
            pos = p;
            height = h;
        }
    }

    public List<int[]> getSkyline(int[][] buildings) {
        List<BuildingNode> nodes = new ArrayList<>();
        for (int[] building : buildings) {
            nodes.add(new BuildingNode(1, building[0], building[2]));
            nodes.add(new BuildingNode(0, building[1], building[2]));
        }
        Collections.sort(nodes, new Comparator<BuildingNode>() {
            @Override
            public int compare(BuildingNode o1, BuildingNode o2) {
                if (o1.pos != o2.pos) {
                    return o1.pos - o2.pos;
                } else if (o1.type != o2.type) {
                    return o2.type - o1.type;
                } else {
                    if (o1.type == 1) {
                        return o2.height - o1.height;
                    } else {
                        return o1.height - o2.height;
                    }
                }
            }
        });

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        List<int[]> result = new ArrayList<>();
        int pre = 0;
        for (int i = 0; i < nodes.size(); i++) {
            BuildingNode curNode = nodes.get(i);
            if (curNode.type == 1) {
                maxHeap.offer(curNode.height);
                if (maxHeap.peek() != pre) {
                    result.add(new int[]{curNode.pos, maxHeap.peek()});
                    pre = maxHeap.peek();
                }
            } else {
                maxHeap.remove(curNode.height);
                if (maxHeap.isEmpty()) {
                    pre = 0;
                    result.add(new int[]{curNode.pos, 0});
                } else if (maxHeap.peek() != pre) {
                    result.add(new int[]{curNode.pos, maxHeap.peek()});
                    pre = maxHeap.peek();
                }
            }
        }
        return result;
    }

    /**
     * 220. Contains Duplicate III.
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (t < 0) {
            return false;
        }
        SortedSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.size() > k) {
                set.remove((long)nums[i - k - 1]);
            }
            int cur = nums[i];
            SortedSet<Long> subSet = set.subSet((long)cur - t, (long)cur + t + 1);
            if (!subSet.isEmpty()) {
                return true;
            }
            set.add((long)cur);
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(countPrimes(6));
        boolean[] m = new boolean[10];
//        System.out.println(m[0]);
        System.out.println(combinationSum3(3, 7));
    }
}
