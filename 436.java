/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
 //https://leetcode.com/problems/find-right-interval/
public class Solution {
    long convert(Interval i) {
        return ((i.start & 0x00000000FFFFFFFFL)<<32) | (i.end & 0x00000000FFFFFFFFL); 
    }
    public int[] findRightInterval(Interval[] intervals) {
        Map<Long, Integer> findIndex = new HashMap<>();
        TreeMap<Integer, Long> findInterval = new TreeMap<>();
        for(int i = 0; i< intervals.length; ++i) {
            long cur = convert(intervals[i]);
            findIndex.put(cur, i);
            findInterval.put(intervals[i].start, cur);
        }
        int [] ret = new int[intervals.length];
        for(int i = 0; i< intervals.length; ++i) {
            Map.Entry<Integer, Long> f = findInterval.ceilingEntry(intervals[i].end);
            if(f == null) 
                ret[i] = -1;
            else
                ret[i] = findIndex.get(f.getValue());
            
        }
        return ret;
    }
}
