/*
Given an array A (index starts at 1) consisting of N integers: A1, A2, ..., AN and an integer B. The integer B denotes that from any place (suppose the index is i) in the array A, you can jump to any one of the place in the array A indexed i+1, i+2, …, i+B if this place can be jumped to. Also, if you step on the index i, you have to pay Ai coins. If Ai is -1, it means you can’t jump to the place indexed i in the array.

Now, you start from the place indexed 1 in the array A, and your aim is to reach the place indexed N using the minimum coins. You need to return the path of indexes (starting from 1 to N) in the array you should take to get to the place indexed N using minimum coins.

If there are multiple paths with the same cost, return the lexicographically smallest such path.

If it's not possible to reach the place indexed N then you need to return an empty array.

Example 1:
Input: [1,2,4,-1,2], 2
Output: [1,3,5]
Example 2:
Input: [1,2,4,-1,2], 1
Output: []
Note:
Path Pa1, Pa2, ..., Pan is lexicographically smaller than Pb1, Pb2, ..., Pbm, if and only if at the first i where Pai and Pbi differ, Pai < Pbi; when no such i exists, then n < m.
A1 >= 0. A2, ..., AN (if exist) will in the range of [-1, 100].
Length of A is in the range of [1, 1000].
B is in the range of [1, 100].

*/

public class Solution {
    List<Integer> get(int [] last, int index) {
        List<Integer> ret = new ArrayList<>();
        ret.add(index);
        while(index != 1) {
            ret.add(last[index - 1]);
            index = last[index - 1];
        }
        Collections.reverse(ret);
        return ret;
    }
    
    int compare(List<Integer> p1, List<Integer> p2) {
        int i = 0;
        for(; i< p1.size() && i < p2.size() ; ++i) {
            if(p1.get(i) < p2.get(i)) {
                return -1;
            } else if(p1.get(i) > p2.get(i)){
                return 1;
            }
        }
        if(i == p1.size()) return -1;
        return 1;
    }
    
    public List<Integer> cheapestJump(int[] A, int B) {
        List<Integer> ret = new ArrayList<>();
        if(A == null || A.length == 0 || A[0] == -1 || A[A.length - 1] == -1)  return ret;
        int [] last = new int [A.length];
        int [] cost = new int [A.length];
        last[0] = 1;
        cost[0] = A[0];
        for(int i = 1; i < A.length; ++i) {
            if(A[i] == -1)  continue;
            for(int k = 1; k <= B; ++k) {
                if(i - k < 0) {
                    break;
                }
                if(A[i - k] == -1)  continue;
                if(last[i] == 0 || cost[i] > cost[i - k] + A[i]) { // first available option
                    last[i] = i - k + 1;
                    cost[i] = cost[i - k] + A[i];
                    continue;
                } 
                if(cost[i] == cost[i - k] + A[i]) {
                    int old = last[i];
                    List<Integer> oldp = get(last, i + 1);
                    last[i] = i - k + 1;
                    List<Integer> newp = get(last, i + 1);
                    if(compare(oldp, newp) < 0 ) {
                        last[i] = old;
                    }
                } 
            }
            if(last[i] == 0)    return ret; // not possible
        }
        if(last[last.length - 1] == 0)  return ret; //not possinble
        /*
        for(int i = 0; i< A.length; ++i) {
            System.out.println(i + 1 + " ! " + last[i] + " : " + cost[i]);
        }
        */
        return get(last, last.length);
    }
}
