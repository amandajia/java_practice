//https://leetcode.com/problems/sequence-reconstruction/
public class Solution {
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        int n = org.length;
        int [] position = new int [n + 1];
        for(int i=0; i<n; ++i) {
            position[org[i]] = i;
        }
        Set<Integer> present = new HashSet<>();
        int [] after = new int[n + 1];
        for(int i = 0; i< seqs.length; ++i) {
            int last = -1;
            for(int j = 0; j< seqs[i].length; ++j) {
                if(seqs[i][j] > n || seqs[i][j]<=0)  return false;
                present.add(seqs[i][j]);
                if(j!=0) {
                    if(position[last] >= position[seqs[i][j]])  return false;
                    if(position[last] + 1== position[seqs[i][j]]) {
                        after[last] = 1;
                    }
                }
                last = seqs[i][j];
            }
        }
        if(present.size() != n) return false;
        for(int i=0; i<n-1; ++i) {
            if(after[org[i]]!=1)    return false;
        }
        return true;
    }
}
