/*
Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

Example 1:
Given points = [[1,1],[-1,1]], return true.

Example 2:
Given points = [[1,1],[-1,-1]], return false.

Follow up:
Could you do better than O(n2)?

Credits:
Special thanks to @memoryless for adding this problem and creating all test cases.
*/

public class Solution {
    public boolean isReflected(int[][] points) {
        int maxX = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        Map<Integer, Set<Integer>> h = new HashMap<>();
        for(int[] p : points) {
            if(p[0] > maxX)
                maxX = p[0];
            if(p[0] < minX)
                minX = p[0];
            if(!h.containsKey(p[1])) {
                Set<Integer> nl = new HashSet<>();
                h.put(p[1], nl);
            }
            h.get(p[1]).add(p[0]);
        }
        
        int a = maxX + minX;
        for(Integer key : h.keySet()) {
            Set<Integer> xs = h.get(key);
            Set<Integer> find = new HashSet<>();
            for(Integer x : xs) {
                if(x * 2 == a)
                    continue;
                else if(find.contains(x)) {
                    find.remove(x);
                } else {
                    find.add(a - x);   
                }
            }
            if(!find.isEmpty())
                return false;
        }
        return true;
    }
}
