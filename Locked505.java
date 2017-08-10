/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: 12
Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: -1
Explanation: There is no way for the ball to stop at the destination.

Note:
There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

*/
public class Solution {
    int [][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean pongbi(int [] cur, int [] dir, int[][]maze) {
        int px = cur[0] + dir[0];
        int py = cur[1] + dir[1];
        return px >= maze.length || px < 0 || py >= maze[0].length || py < 0 || (maze[px][py] == 1) ; 
    }
    
    void DFS(int [][]maze, int[]start, int[] dest, int[][] visited, int dist, int coming) {
        if(dist + Math.abs(dest[0] - start[0]) + Math.abs(dest[1] - start[1])>= visited[dest[0]][dest[1]]) return;
        for(int d = 0; d<4; ++d) {
            if(d == coming) continue;
            int [] dir = dirs[d];
            int [] cur = new int[2];
            int cDist = dist;
            cur[0] = start[0];
            cur[1] = start[1];
            while(!pongbi(cur, dir, maze)) {
                cur[0] += dir[0];
                cur[1] += dir[1];
                cDist ++;
            }
            if(cur[0] == dest[0] && cur[1] == dest[1]) {
                visited[dest[0]][dest[1]] = Math.min(visited[dest[0]][dest[1]], cDist);
                return ;
            }
            if(visited[cur[0]][cur[1]] <= cDist) {
                continue;
            }  
            visited[cur[0]][cur[1]] = cDist;
            DFS(maze, cur, dest, visited, cDist, d);
        }
    }
    
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        if(maze == null || maze.length == 0 || maze[0].length == 0) return -1;
        if(start[0] == dest[0] && start[1] == dest[1]) {
            return 0;
        }
        if(maze[start[0]][start[1]] == 1)   return -1;
        int [][] visited = new int[maze.length][maze[0].length];
        for(int i = 0; i<maze.length; ++i) 
            for(int j = 0; j<maze[0].length; ++j)
                visited[i][j] = Integer.MAX_VALUE;
        visited[start[0]][start[1]] = 0;
        DFS(maze, start, dest, visited, 0, 4);
        return (visited[dest[0]][dest[1]] == Integer.MAX_VALUE)? -1: visited[dest[0]][dest[1]];
    }
}
