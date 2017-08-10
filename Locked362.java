/*
Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

Example:
HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301); 
Follow up:
What if the number of hits per second could be very large? Does your design scale?

Credits:
*/
public class HitCounter {
    int [] hits;
    int [] time;
    int sum;
    int last;
    int lp;
    /** Initialize your data structure here. */
    public HitCounter() {
        hits = new int[300];
        time = new int[300];
        sum = 0;
        last = 0;
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int t) {
        int ts = t % 300;
        if(t == time[ts]) {
            hits[ts]++;
            sum ++;
        } else {
            sum = sum  + 1;
            if(time[ts]!= 0)
                sum = sum - hits[ts];
            hits[ts] = 1;
            time[ts] = t;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int t) {
        int it = last % 300;
        for(int i = 0; i<300 && i <= t - last; ++i, ++it) {
            it = it % 300;
            if(time[it]== 0) {
                continue;
            } 
            if(t - time[it] >= 300) {
                sum -= hits[it];
                time[it] = 0;
            }
        }
    
        last = t;
        return sum;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
