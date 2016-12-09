import java.util.*;

/**
 * Created by Edmond on 11/29/16.
 */
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
        System.out.println(countPrimes(6));
        boolean[] m = new boolean[10];
        System.out.println(m[0]);

    }
}
