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
    boolean belongTo(Point x1, Point x2, Point x3) {
        if(x1.y == x2.y) {
            return x1.y == x3.y ;
        }
        if(((x1.x - x2.x) * (x3.y- x2.y)) != ((x2.x - x3.x) * (x2.y- x1.y)))
            return false;
        if(((x1.x * x2.y - x2.x * x1.y) * (x3.y- x2.y)) != ((x2.x * x3.y - x3.x * x2.y) * (x2.y- x1.y)))
            return false;
        return true;
    }
    List<Integer> con(Point p) {
        List<Integer> temp = new ArrayList<>();
        temp.add(p.x);
        temp.add(p.y);
        return temp;
    } 
    
    public int maxPoints(Point[] points) {
        if(points == null || points.length<3) return points.length;
        int n = points.length;
        Map<List<Integer>, Integer> weight = new HashMap<>();
        List<Point> p = new ArrayList<>(); 
        for(int i = 0; i< n; ++i) {
            List<Integer> temp = con(points[i]);
            if(weight.containsKey(temp)) {
                weight.put(temp, weight.get(temp) + 1);
            } else {
                weight.put(temp, 1);
                p.add(points[i]);
            }
        }
     
        int max = 0;
        n = p.size();
        if(n == 1)  return points.length;
        for(int i=0; i<n; ++i)
            for(int j = i+1; j<n; ++j) {
                int count = weight.get(con(p.get(i))) + weight.get(con(p.get(j)));
                
                for(int k = j + 1; k <n;++k) {
                    if(belongTo(p.get(i), p.get(j), p.get(k)))
                        count+= weight.get(con(p.get(k)));
                }
                if(count>max)
                    max = count;
            }
        return max;
    }
}
