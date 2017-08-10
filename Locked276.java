/*
There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.
*/
public class Solution {
    public int numWays(int n, int k) {
        if(n <=0 || k == 0)   return 0;
        if(n == 1 )  return k;
        int s1 = k; 
        int s2 = 0;
        for(int i = 1; i<n; ++i) {
            int ns2 = s1;
            s1 = (s1 + s2)* (k - 1);
            s2 = ns2;
        }
        return s1 + s2;
    }
}
