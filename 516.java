public class Solution {
    public int longestPalindromeSubseq(String s) {
        if(s == null || s.length() == 0)    return 0;
        int [][] dp = new int[s.length() + 1][s.length() + 1];
        int max = 0;
        int l = s.length();
        for(int i = 1; i <dp.length; ++i ){
            for(int j = 1; j< dp.length; ++j) {
                dp[i][j] = Math.max(dp[i][j], Math.max(dp[i][j - 1], dp[i - 1][j]));
                if(i + j > l ) {
                    max = Math.max(max, dp[i][j] + 1);
                    break;
                }
                if(s.charAt(i - 1) == s.charAt(l - j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 2;
                }
                if (i + j == l) {
                    max = Math.max(max, Math.max(dp[i + 1][j + 1], dp[i][j] + 1));
                }
            }
        }
        return max;
    }
}
