public class Solution {
    public int leastInterval(char[] tasks, int n) {
        if(tasks == null || tasks.length == 0)  return 0;
        if(n == 0)  return tasks.length;
        int [] next = new int[26];
        int [] count = new int[26];
        for(char t : tasks) {
            count[t - 'A'] ++;
        }
        int ret = 0;
        n++;
        for(int total = tasks.length; total > 0; ret++) {
            int index = -1;
            int max = 0;
            for(int i = 0; i< 26; ++i) {
                if(next[i] <= ret) {
                    if(count[i] > max) {
                        max = count[i];
                        index = i;
                    }
                }
            } 
            if(index < 0)   continue;
            total --;
            count[index] --;
            next[index] = ret + n;
        }
        return ret;
    }
}
