/*
Given a list of points that form a polygon when joined sequentially, find if this polygon is convex (Convex polygon definition).

Note:

There are at least 3 and at most 10,000 points.
Coordinates are in the range -10,000 to 10,000.
You may assume the polygon formed by given points is always a simple polygon (Simple polygon definition). In other words, we ensure that exactly two edges intersect at each vertex, and that edges otherwise don't intersect each other.
Example 1:

[[0,0],[0,1],[1,1],[1,0]]

Answer: True

Explanation:
Example 2:

[[0,0],[0,10],[10,10],[10,0],[5,5]]

Answer: False

Explanation:
*/

public class Solution {
    int judge(List<Integer> p0, List<Integer> p1, List<Integer> p2) {
        return (p1.get(0) - p0.get(0)) * (p2.get(1) - p0.get(1)) - (p2.get(0) - p0.get(0)) * (p1.get(1) - p0.get(1)); 
        //(p1.x - p0.x)(p2.y - p0.y) - (p2.x - p0.x)(p1.y - p0.y);
    }
    
    int dis(List<Integer>  p1, List<Integer>  p2) {
        return ( p1.get(0) - p2.get(0) ) * ( p1.get(0) - p2.get(0) ) + ( p1.get(1) - p2.get(1) ) * ( p1.get(1) - p2.get(1) );
    }
    
    public boolean isConvex(List<List<Integer>> points) {
        if(points == null || points.size() < 3) return false;
        Integer root = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i <points.size(); ++i) {
            List<Integer> p = points.get(i);
            if(p.get(0) < min || p.get(0) == min && p.get(1) <= points.get(root).get(1)) {
                min = p.get(0);
                root = i;
            }
        }
        int count = 0;
        int first = root;
        while(count == 0 || first != root ) {
            int index = 0;
            if(first == index) {
                index ++;
            }
            List<Integer> same = new ArrayList<>();
            for(int i = 0; i< points.size(); ++i) {
                if(i == first || i == index )    continue;
                int dir = judge(points.get(first), points.get(index), points.get(i));
                if(dir > 0) {
                    index = i;
                    same = new ArrayList<>();
                    continue;
                }
                if(dir == 0) {
                    same.add(i);
                }
            }
            same.add(index);
            double fur = 0;
            for(int i: same) {
                if(dis(points.get(first), points.get(i)) > fur) {
                    fur = dis(points.get(first), points.get(i));
                    index = i;
                }
                count ++;
            }
           // System.out.println(first + " ! " + index + " : " + same);
            first = index;
        }
        return count == points.size();
    }
}
