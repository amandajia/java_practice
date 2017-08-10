/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.

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
    public boolean canAttendMeetings(Interval[] intervals) {
        PriorityQueue<Interval> pq = new PriorityQueue<Interval>(new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;        
            }
        });
        for(Interval i: intervals) {
            pq.add(i);
        }
        int last = Integer.MIN_VALUE;
        while(!pq.isEmpty()) {
            Interval i = pq.poll();
            if(i.start < last) {
                return false;
            }
            last = i.end;
        }
        return true;
    }
}
