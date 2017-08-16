package Onsite;

/**
 * Created by Edmond on 8/14/17.
 */
public class ArrayPrefixSum {
    /**
     * 122. Best Time to Buy and Sell Stock II follow up
     * @param prices
     * @return
     */
    public int maxProfitWithFee(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[] hold = new int[prices.length];
        int[] empty = new int[prices.length];
        hold[0] = -prices[0] - fee;
        empty[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            hold[i] = Math.max(hold[i - 1], empty[i - 1] - prices[i] - fee);
            empty[i] = Math.max(empty[i - 1], hold[i - 1] + prices[i] - fee);
        }
        return Math.max(hold[prices.length - 1], empty[prices.length - 1]);
    }
}
