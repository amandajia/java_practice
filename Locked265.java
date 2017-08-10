/*
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?

*/

public class Solution {
    public int minCostII(int[][] costs) {
        if(costs == null || costs.length == 0)  return 0;
        int m = costs[0].length;
        if(m == 1) {
            int sum = 0;
            for(int[] line: costs) {
                sum += line[0];
            }
            return sum;
        }
        
        int [] last = new int[m];
        int min = (costs[0][0] > costs[0][1])? 1 : 0;
        last[0] = costs[0][0];
        int sec = (costs[0][0] > costs[0][1])? 0 : 1;
        last[1] = costs[0][1];
        for(int i = 2; i< m; ++i) {
            last[i] = costs[0][i];
            if(last[i] <= last[min]) {
                sec = min;
                min = i;
            } else if(last[i] < last[sec]) {
                sec = i;
            }
        }
        //System.out.println("Iteration : " + min + " : " + sec);
        for(int i = 1; i< costs.length; ++i) {
            int [] cur = new int[m];
            int nmin = -1;
            int vmin = Integer.MAX_VALUE;
            int nsec = -1;
            int vsec = Integer.MAX_VALUE;
            for(int j =0 ; j<m; ++j) {
                cur[j] = costs[i][j] + ((min == j)? last[sec]:last[min]);  
                if(cur[j] <= vmin) {
                    nsec = nmin;
                    vsec = vmin;
                    vmin = cur[j];
                    nmin = j;
                } else if(cur[j] < vsec) {
                    nsec = j;
                    vsec = cur[j];
                }
            }
            min = nmin;
            sec = nsec;
            //System.out.println("Iteration : " + vmin + " : " + vsec);
            last = cur;
        }
        return last[min];
    }
}
