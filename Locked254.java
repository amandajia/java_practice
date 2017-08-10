/*
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note: 
You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Examples: 
input: 1
output: 
[]
input: 37
output: 
[]
input: 12
output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
input: 32
output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]

*/
public class Solution {
    public List<List<Integer>> getF(int n, int minF) {
        List<List<Integer>> ret = new ArrayList<>();
        int maxF = (int)Math.sqrt(n);
        for(int i = minF; i<= maxF; i++) {
            if(i * (n/i) == n && (n/i) >= i) {
                List<List<Integer>> s = getF(n/i, i);
                List<Integer> cur = new ArrayList<>();
                cur.add(i);
                cur.add(n/i);
                ret.add(cur);
                for(List<Integer> t : s) {
                    t.add(0, i);
                    ret.add(t);
                }
            }
        }
        return ret;
    }
    
    public List<List<Integer>> getFactors(int n) {
        return getF(n, 2);
    }
}
