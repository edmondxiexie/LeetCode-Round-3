package Onsite;

/**
 * Created by Edmond on 8/14/17.
 */
public class MathSection {
    /**
     * 29 divide 2 integer,follow up,求余数 * 2.
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 1) {
            return dividend;
        }
        if ((divisor == -1 && dividend == Integer.MIN_VALUE) || divisor == 0) {
            return Integer.MAX_VALUE;
        }
        int sign = 1;
        if ((divisor < 0 && dividend > 0) || (divisor > 0 && dividend < 0)) {
            sign = -1;
        }
        long dividendLong = Math.abs((long)dividend);
        long divisorLong = Math.abs((long)divisor);
        int power = 0;
        long tmp = divisorLong;
        while ((tmp << 1) <= dividendLong && (tmp << 1) > 0) {
            tmp = tmp << 1;
            power++;
        }
        int result = 0;
        while (dividendLong >= divisorLong) {
            while (dividendLong < tmp) {
                tmp = tmp >> 1;
                power--;
            }
            result += (1 << power);
            dividendLong -= tmp;
        }
        if (sign == 1) {
            return (int)dividendLong;
        } else {
            return -(int)dividendLong;
        }
    }

    public static void main(String[] args) {
        MathSection ms = new MathSection();
        System.out.println(ms.divide(-35, 4));
        System.out.println(7 / -3);
    }
}
