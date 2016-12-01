import java.util.Arrays;

/**
 * Created by Edmond on 11/29/16.
 */
public class Question_200_220 {

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

    public static void main(String[] args) {
        System.out.println(countPrimes(6));
        boolean[] m = new boolean[10];
        System.out.println(m[0]);

    }
}
