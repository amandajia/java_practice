/*
There's a tree, a squirrel, and several nuts. Positions are represented by the cells in a 2D grid. Your goal is to find the minimal distance for the squirrel to collect all the nuts and put them under the tree one by one. The squirrel can only take at most one nut at one time and can move in four directions - up, down, left and right, to the adjacent cell. The distance is represented by the number of moves.

Example 1:
Input: 
Height : 5
Width : 7
Tree position : [2,2]
Squirrel : [4,4]
Nuts : [[3,0], [2,5]]
Output: 12
Explanation:

Note:
All given positions won't overlap.
The squirrel can take at most one nut at one time.
The given positions of nuts have no order.
Height and width are positive integers. 3 <= height * width <= 10,000.
The given positions contain at least one nut, only one tree and one squirrel.

*/

public class Solution {
    public int minDistance(int height, int width, int[] tree, int[] s, int[][] nuts) {
        if(nuts == null || nuts.length == 0)    return 0;
        int ret = 0;
        int minDis = Integer.MAX_VALUE;
        for(int i = 0; i < nuts.length; ++i) {
            int [] nut = nuts[i];
            int route = Math.abs(nut[0] - tree[0]) + Math.abs(nut[1] - tree[1]);
            ret += 2 * route;
            int dis = Math.abs(nut[0] - s[0]) + Math.abs(nut[1] - s[1]) - route;
            if(dis < minDis) {
                minDis = dis;
            }
        }
        return ret + minDis;
    }
}
