/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4

*/
public class Solution {
    public void wallsAndGates(int[][] rooms) {
        if(rooms == null || rooms.length == 0 || rooms[0].length == 0)  return;
        Queue<int[]> q = new LinkedList();
        int m = rooms.length;
        int n = rooms[0].length;
        for(int i = 0; i< m; i++) {
            for(int j=0; j< n; ++j){
                if(rooms[i][j] == 0) {
                    int [] g = new int[2];
                    g[0] = i;
                    g[1] = j;
                    q.add(g);
                }
            }
        }
        int[][]dir = new int [][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int[] d: dir) {
                int [] ng = new int[2];
                ng[0] = cur[0] + d[0];
                ng[1] = cur[1] + d[1];
                if(ng[0]  < 0 || ng[1] < 0 || ng[0] >= m || ng[1] >= n) continue;
                if(rooms[ng[0]][ng[1]] < 0)    continue;
                if(rooms[ng[0]][ng[1]] <= rooms[cur[0]][cur[1]]) continue;
                rooms[ng[0]][ng[1]] = rooms[cur[0]][cur[1]] + 1;
                q.add(ng);
            }
        }
    }
}
