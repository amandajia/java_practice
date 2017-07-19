// https://leetcode.com/problems/erect-the-fence/#/description
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    
    int judge(Point p0, Point p1, Point p2) {
        return (p1.x - p0.x) * (p2.y - p0.y) - (p2.x - p0.x) * (p1.y - p0.y); 
        //(p1.x - p0.x)(p2.y - p0.y) - (p2.x - p0.x)(p1.y - p0.y);
    }
    int dis(Point p1, Point p2) {
        return ( p1.x - p2.x ) * ( p1.x - p2.x ) + ( p1.y - p2.y ) * ( p1.y - p2.y );
    }
    
    public List<Point> outerTrees(Point[] points) {
        List<Point> ret = new ArrayList<>();
        if(points == null || points.length == 0)    return ret;
        if(points.length <= 3) {
            for(Point i : points)
                ret.add(i);
            return ret;
        }
        int min = 1000;
        int first = -1;
        for(int i =0; i< points.length; ++i) {
            Point p = points[i];
            if(p.x < min || p.x == min && p.y < points[first].y) {
                min = p.x;
                first = i;
            }
        }
        List<Integer> index = new ArrayList<>();
        index.add(first);
        while(index.size() ==  1 || first != index.get(0)) {
            int second = 0;
            if(second == first) {
                second ++;
            }
            List<Integer> on = new ArrayList<>();
            for(int j = 0; j < points.length; ++j) {
                if(first == j || second == j) continue;
                int cur = judge(points[first], points[second], points[j]);
                if(cur == 0) {
                    on.add(j);
                    continue;
                }
                if(cur < 0) {
                    second = j;
                    on.clear();
                }
            }
            on.add(second);
            int max = 0;
            int next = -1;
            for(int i : on) {
                if(i != index.get(0))
                    index.add(i);
                int cd = dis(points[first], points[i]);
                if( cd > max) {
                    max = cd;
                    next = i;
                }
            }
            first = next;
        }
        for(int i : index) {
            ret.add(points[i]);
        }
        return ret;
    }
}
