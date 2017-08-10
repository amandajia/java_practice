/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.

*/
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals == null || intervals.length == 0)  return 0;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0] == o2[0])? o1[1] - o2[1] : o1[0] - o2[0];        
            }
        });
        int ret = 0;
        for(Interval i: intervals) {
            pq.add(new int[]{i.start, 1});
            pq.add(new int[]{i.end, -1});
        }
        int cur = 0;
        while(!pq.isEmpty()) {
            cur += pq.poll()[1];
            ret = Math.max(cur, ret);
        }
        return ret;
    }
}
