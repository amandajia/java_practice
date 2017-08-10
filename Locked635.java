/*
You are given several logs that each log contains a unique id and timestamp. Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.

Design a log storage system to implement the following functions:

void Put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.


int[] Retrieve(String start, String end, String granularity): Return the id of logs whose timestamps are within the range from start to end. Start and end all have the same format as timestamp. However, granularity means the time level for consideration. For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.

Example 1:
put(1, "2017:01:01:23:59:59");
put(2, "2017:01:01:22:59:59");
put(3, "2016:01:01:00:00:00");
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
Note:
There will be at most 300 operations of Put or Retrieve.
Year ranges from [2000,2017]. Hour ranges from [00,23].
Output for Retrieve has no order required.

*/

public class LogSystem {
    TreeMap index = new TreeMap();
    String [] gap = new String[] {"Year", "Month", "Day", "Hour", "Minute", "Second"};
    private int tran(String t, String stop, boolean end) {
        String [] ts = t.split(":");
        int [] time = new int[6];
        int st = 0;
        if(stop == null) {
            st = 5;
        } else {
            for(; st < gap.length; ++st) {
                if(stop.equals(gap[st])) {
                    break;
                    }
                }
            
        }
        for(int i = 0; i<= st ; ++i) {
            time[i] = Integer.parseInt(ts[i]);
        }
        if(end) {
            time[st] ++;
            time[5] --;
            //System.out.println(t + stop + st + " : "  + time[st] + " : " + time[5]);
        }
        return (((((time[0] - 2000)*13 + time[1])*32 + time[2]) * 24 + time[3])* 60 + time[4]) * 60 + time[5];  
    }
    public LogSystem() {
        
    }
    
    public void put(int id, String timestamp) {
        index.put(tran(timestamp, null, false), id);
        //System.out.println(tran(timestamp, null, false) + "  : " + id);
    }
    
    public List<Integer> retrieve(String s, String e, String gra) {
        Integer start = tran(s, gra, false);
        Integer end = tran(e, gra, true);
        Map<Integer, Integer> all = index.subMap(start, true, end, true);
        //System.out.println(start +" ! " + end);
        List<Integer> ret = new ArrayList<>();
        ret.addAll( all.values());
        return ret;
    }
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */
