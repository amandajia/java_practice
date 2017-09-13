public class Solution {
    public int maxProfit(int k, int[] prices) {
        if(prices == null || prices.length < 2)    return 0;
        if(k> prices.length) {
            int sum = 0;
            for(int i = 1; i < prices.length; ++i) {
                if(prices[i] > prices[i - 1])
                    sum+= prices[i] - prices[i - 1];
            }
            return sum;
        }
        int [][] dp = new int [k + 1][prices.length + 1];
        for(int i = 1; i<k+ 1; ++i) {
            int maxVal = Integer.MIN_VALUE;
            for(int j = 2; j< prices.length + 1; ++j) {
                maxVal = Math.max(maxVal, dp[i-1][j-2] - prices[j-2]);
                dp[i][j] = Math.max(dp[i][j - 1], prices[j - 1] + maxVal);
            }
        }
        return dp[k][prices.length];
    }
}
