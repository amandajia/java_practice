/*
Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a-b|. Your task is to find the maximum distance.

Example 1:
Input: 
[[1,2,3],
 [4,5],
 [1,2,3]]
Output: 4
Explanation: 
One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
Note:
Each given array will have at least 1 number. There will be at least two non-empty arrays.
The total number of the integers in all the m arrays will be in the range of [2, 10000].
The integers in the m arrays will be in the range of [-10000, 10000].

*/
public class Solution {
    static class Extreme {
        int val;
        List<Integer> ind; // multiple same value 
        public Extreme(int v, int i) {
            val = v; 
            ind = new ArrayList<>();
            ind.add(i);
        }
    }
    
    public int maxDistance(List<List<Integer>> arrays) {
        Extreme s1 = null;
        Extreme s2 = null;
        Extreme b1 = null;
        Extreme b2 = null;
        for(int i = 0; i< arrays.size(); ++i) {
            if(arrays.get(i) == null || arrays.get(i).size() == 0)  continue;
            int curMin = arrays.get(i).get(0);
            int curMax = arrays.get(i).get(arrays.get(i).size() - 1);
            if(s1 == null) {
                s1 = new Extreme(curMin, i);
            } else if(s1.val > curMin) {
                s2 = s1;
                s1 = new Extreme(curMin, i);
            } else if(s1.val == curMin) {
                s1.ind.add(i);
            } else if(s2 == null || s2.val > curMin) {
                s2 = new Extreme(curMin, i);
            } else if(s2.val == curMin) {
                s2.ind.add(i);
            }
            
            if(b1 == null) {
                b1 = new Extreme(curMax, i);
            } else if(b1.val < curMax) {
                b2 = b1;
                b1 = new Extreme(curMax, i);
            } else if(b1.val == curMax) {
                b1.ind.add(i);
            } else if(b2 == null || b2.val < curMax) {
                b2 = new Extreme(curMax, i);
            } else if(b2.val == curMax) {
                b2.ind.add(i);
            }
        }
        if(b1.ind.size()> 1 || s1.ind.size()> 1 || b1.ind.get(0) != s1.ind.get(0)) {
            return b1.val - s1.val;
        }
        return Math.max(b1.val - s2.val, b2.val - s1.val);
    }
}
