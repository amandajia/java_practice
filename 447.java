// https://leetcode.com/problems/number-of-boomerangs/
public class Solution {
    int dis(int[] p1, int [] p2) {
        return (p2[0] - p1[0])*(p2[0] - p1[0]) + (p2[1] - p1[1])*(p2[1] - p1[1]);
    }
    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        int ret = 0;
        for(int i=0; i < points.length; ++i) {
            Map<Integer, Integer> table = new HashMap<>();
            for(int j= 0; j < points.length; ++j) {
                if(j == i)  continue;
                int d = dis(points[i] , points[j]);
                table.putIfAbsent(d, 0);
                int v = table.get(d);
                ret += 2 * v;
                table.put(d, v+1);
            }
        }
        return ret;
    }
}
