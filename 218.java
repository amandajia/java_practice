public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> ret = new ArrayList<>();
        if(buildings == null || buildings.length == 0)  return ret;
        // List of time Series
        List<Integer> time = new ArrayList<>();
        // list of events, values is index of buildings.
        Map<Integer, List<Integer>> events = new HashMap<>();
        // heap of highest building, 
        PriorityQueue<int[]> pq = new PriorityQueue<int[]> (new Comparator<int[]>() {
            @Override
            public int compare(int[]o1, int[]o2) {
                return o2[2] - o1[2];
            }
        });

        for(int i =0; i<buildings.length; ++i) {
           int[] cur = buildings[i];
           if(!events.containsKey(cur[0])) {
               events.put(cur[0], new ArrayList<Integer>());
               time.add(cur[0]);
           } 
           if(!events.containsKey(cur[1])) {
               events.put(cur[1], new ArrayList<Integer>());
               time.add(cur[1]);
           } 
           events.get(cur[0]).add(i);
        }
        
        Collections.sort(time);
        for(int i=0; i< time.size(); ++i) {
            int cur = time.get(i);
            List<Integer> curEvent = events.get(cur);
            for(Integer j : curEvent) {
                pq.add(buildings[j]);
            }
            int h = 0;
            while(!pq.isEmpty()) {
                int[] curHigh = pq.peek();
                if(curHigh[1] <= cur) {
                    pq.poll();
                    continue;
                } 
                h = curHigh[2];
                break;
            }
            if(ret.size() == 0 || ret.get(ret.size() - 1)[1] != h) {
                ret.add(new int[]{cur, h});
            }
            
        }
        return ret;
    }
}
