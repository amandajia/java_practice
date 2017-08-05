public class Solution {
    public int canCompleteCircuit(int[] gas, int[] c) {
        int n = gas.length;
        int [] cost = new int [n];
        int sum = 0;
        for(int i = 0; i< n; ++i) {
            cost[i] = gas[i] - c[i];
            sum += cost[i];
        }
        if(sum <0)  return -1;
        sum = 0;
        int index = 0;
        int min_sum = 0;
        int start = 0;
        int i = 0;
        for(int count = 0; count< 2 * n; i = (i + 1) %n, ++count) {
            sum += cost[i];
            if(sum >= 0) {
                start = (i + 1)%n;
                sum = 0;
                continue;
            }
            if(sum < min_sum) {
                min_sum = sum;
                index = (i + 1)%n; 
            }
        }
        return index;
    }
}
