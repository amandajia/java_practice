/*
Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

Example 1:

Input:
org: [1,2,3], seqs: [[1,2],[1,3]]

Output:
false

Explanation:
[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
Example 2:

Input:
org: [1,2,3], seqs: [[1,2]]

Output:
false

Explanation:
The reconstructed sequence can only be [1,2].
Example 3:

Input:
org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]

Output:
true

Explanation:
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
Example 4:

Input:
org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]

Output:
true
UPDATE (2017/1/8):
The seqs parameter had been changed to a list of list of strings (instead of a 2d array of strings). Please reload the code definition to get the latest changes.
*/
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
