\\https://leetcode.com/problems/maximum-vacation-days/#/description
public class Solution {
    public int maxVacationDays(int[][] flights, int[][] days) {
        if(flights == null || flights.length == 0)  return 0;
        int n = flights.length;
        int k = days[0].length;
        int [] ret = new int[n];
        ret[0] = days[0][0];
        for(int i = 1; i < n; ++i) {
            if(flights[0][i] > 0) {
                ret[i] = days[i][0];    
            } else {
                ret[i] = -1;
            }
        }
        
        for(int i = 1; i < k; ++i) {
            int [] next = new int[n];
            for(int j = 0; j< n; ++j) {
                int curMax = -1;
                for(int m =0; m< n; ++m) {
                    if(ret[m] < 0)  continue;
                    if(m == j || flights[m][j] > 0) {
                        curMax = Math.max(curMax, ret[m]);    
                    }
                }
                if(curMax >= 0)
                    next[j] = curMax +  days[j][i];
                else next[j] = -1;
            }
            ret = next;
        }
        
        int max = 0;
        for(int i = 0; i < n; ++i) {
            if(ret[i] > max)    max = ret[i];
        }
        return max;
    }
}
