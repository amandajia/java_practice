public class Solution {
    public int findLongestChain(int[][] pairs) {
        if(pairs == null || pairs.length == 0)  return 0;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p1, p2)->p1[1]==p2[1]?p2[0]-p1[0]:p1[1]-p2[1]);
        List<int[]> list = new ArrayList<>();
        for(int [] p: pairs) {
            pq.add(p);
        }
        while(!pq.isEmpty()) {
            int [] cur = pq.poll();
            if(!list.isEmpty() && list.get(list.size() - 1)[1] == cur[1])   continue;
            list.add(cur);
        }
        int [] dp = new int[list.size()+1];
        dp[0] = 0;
        for(int i = 1; i< dp.length; ++i) {
            int [] cur = list.get(i - 1);
            int k = i -2;
            for(; k >=0; --k) {
                if(list.get(k)[1] < cur[0]){
                    break;
                }
            }
            dp[i] = Math.max(dp[i - 1], 1 + dp[k + 1]);
        }
        //for(int i =1; i<dp.length; ++i) {
          //  System.out.println(list.get(i - 1)[0] + " : " + list.get(i-1)[1] + " - " + dp[i] );}
        return dp[list.size()];
    }
}
