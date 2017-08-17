public class Solution {
    public int newInteger(int n) {
        if(n < 9)  return n;
        long [] skip = new long [11];
        n -= 8;
        skip[0] = 1L;
        skip[1] = 9L;
        int wei = 2;
        while( n > 0) {
            long sum = 0L;
            for(int i = 1; i<wei; ++i)  sum += skip[i];
            skip[wei] = 8 * sum;
            if(n > skip[wei]) {
                n -= skip[wei];
            } else {
                break;
            }
            wei ++;
        }
        int ret = 0;
        int lead = 1;
        // System.out.println(wei + " : " + skip[2] + " : " + skip[3] + " : " + n);
        for(wei--; wei>= 0; --wei) {
            Long sum = 0L;
            for(int i  = 1; i<= wei; ++i) 
                sum+= skip[i];
            if(sum == 0)    sum = 1L;
            while(n > sum) {
                n -= sum;
                lead++;
            }
            ret = ret*10 + lead;
            lead = 0;
        }
        if(ret < 0) ret = Integer.MAX_VALUE;
        return ret;
    }
}
