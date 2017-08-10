/*
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.


*/
public class Solution {
    public int minCost(int[][] costs) {
        if(costs == null || costs.length == 0)  return 0;
        int [] color = new int[3];
        color[0] = costs[0][0];
        color[1] = costs[0][1];
        color[2] = costs[0][2];
        for(int i = 1; i< costs.length; ++i) {
            int zero = costs[i][0] + Math.min(color[1], color[2]);
            int one  = costs[i][1] + Math.min(color[0], color[2]);
            color[2]  = costs[i][2] + Math.min(color[0], color[1]);
            color[0] = zero;
            color[1] = one;
        }
        return Math.min(color[0], Math.min(color[1], color[2]));
    }
}
