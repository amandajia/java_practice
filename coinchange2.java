public class Solution {
    public int change(int amount, int[] coins) {
        if(amount == 0) return 1;
        if(amount < 0 || coins == null || coins.length ==0) return 0;
        
        int [] dp = new int[amount + 1];
        Arrays.sort(coins);
        for(int i = 0; i * coins[0] < dp.length; ++i) {
            dp[i * coins[0]] = 1;
        }
        for(int i = 1; i < coins.length; ++i) {
            int c = coins[i];
            int [] next = new int[amount + 1];
            for(int k = 0; k< dp.length; ++k) {
                next[k] = dp[k];
                for(int cc = 1; k - cc * c >=0; cc++) {
                    next[k] += dp[k - cc * c];    
                }
            }
            dp = next;
        }
        return dp[amount];
    }
}
