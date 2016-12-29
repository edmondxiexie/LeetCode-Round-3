import java.lang.management.MemoryType;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Edmond on 11/19/16.
 */
public class Question_441_460 {

    /**
     * 441. Arranging Coins.
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {
        int k = 1;
        while (n > k) {
            n -= k;
            k++;
        }
        return k;
    }

    /**
     * 447. Number of Boomerangs.
     * @param points
     * @return
     */
    public int numberOfBoomerangs(int[][] points) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                int d = getDistance(points[i], points[j]);
                if (map.containsKey(d)) {
                    map.put(d, map.get(d) + 1);
                } else {
                    map.put(d, 1);
                }
            }
            for (int n : map.values()) {
                res += n * (n - 1);
            }
            map.clear();
        }
        return res;
    }

    private int getDistance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];
        return dx * dx + dy * dy;
    }

    /**
     * 451. Sort Characters By Frequency
     * @param s string
     * @return sorted string
     */
    public String frequencySort(String s) {
        char[] chars = s.toCharArray();

        // key is character, value is count
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : chars) {
            if (countMap.containsKey(c)) {
                countMap.put(c, countMap.get(c) + 1);
            } else {
                countMap.put(c, 1);
            }
        }

        List<Character>[] listArray = new List[s.length() + 1];
        for (char c : countMap.keySet()) {
            int freq = countMap.get(c);
            if (listArray[freq] == null) {
                listArray[freq] = new ArrayList<>();
            }
            listArray[freq].add(c);
        }

        StringBuilder result = new StringBuilder();
        for (int i = listArray.length - 1; i > 0; i--) {
            if (listArray[i] != null) {
                for (char c : listArray[i]) {
                    for (int j = 0; j < i; j++) {
                        result.append(c);
                    }
                }
            }
        }

        return result.toString();






//        // index is character, value is count
//        int[] count = new int[128];
//        int max = 0;
//        for (char e : chars) {
//            count[e]++;
//            max = Math.max(max, count[e]);
//        }
//
//        Map<Integer, List<Character>> freq = new HashMap<>();
//
//        for (int i = 0; i < count.length; i++) {
//            char letter = (char)i;
//            if (count[i] > 0) {
//                if (freq.get(count[i]) == null) {
//                    List<Character> list = new ArrayList<>();
//                    list.add(letter);
//                    freq.put(count[i], list);
//                } else {
//                    List<Character> list = freq.get(count[i]);
//                    list.add(letter);
//                    freq.put(count[i], list);
//                }
//            }
//        }
//
//        Arrays.sort(count);
//        int last = 0;
//
//        String result = "";
//        for (int i = 0; i < count.length; i++) {
//            if (count[i] == last) {
//                last = count[i];
//                continue;
//            }
//            List<Character> cur = freq.get(count[i]);
//            for (char c : cur) {
//                for (int l = 0; l < count[i]; l++) {
//                    result = c + result;
//                }
//            }
//            last = count[i];
//        }
//        return result;
    }

    /**
     * 452. Minimum Number of Arrows to Burst Balloons.
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int intersections = 0;
        int last = 0;
        for (int i = 1; i < points.length; i++) {
            int curStart = points[i][0];
            int lastEnd = points[last][1];
            if (curStart < lastEnd) {
                intersections++;
                int curEnd = points[i][1];
                last = (curEnd < lastEnd) ? i : last;
            } else {
                last = i;
            }
        }
        return points.length - intersections;
    }

    /**
     * 453. Minimum Moves to Equal Array Elements.
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
        }
        int total = 0;
        for (int n : nums) {
            total += n - min;
        }
        return total;
    }

}
