/*
Assume you have an array of length n initialized with all 0's and are given k update operations.

Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.

Return the modified array after all k operations were executed.

Example:

Given:

    length = 5,
    updates = [
        [1,  3,  2],
        [2,  4,  3],
        [0,  2, -2]
    ]

Output:

    [-2, 0, 3, 5, 3]
Explanation:

Initial state:
[ 0, 0, 0, 0, 0 ]

After applying operation [1, 3, 2]:
[ 0, 2, 2, 2, 0 ]

After applying operation [2, 4, 3]:
[ 0, 2, 5, 5, 3 ]

After applying operation [0, 2, -2]:
[-2, 0, 3, 5, 3 ]
Credits:
Special thanks to @vinod23 for adding this probl
*/
public class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int [] ret = new int[length];
        for(int i = 0; i<updates.length; ++i) {
            int[] u = updates[i];
            ret[u[0]] += u[2];
            if(u[1] + 1 < length) {
                ret[u[1] + 1] += -u[2];
            }
        }
        int cur = 0;
        for(int i = 0; i < length; ++i) {
            ret[i] = cur + ret[i];
            cur = ret[i];
        }
        return ret;
    }
}
