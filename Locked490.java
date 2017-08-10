/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

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

Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false
Explanation: There is no way for the ball to stop at the destination.

Note:
There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
*/
public class Solution {
    int [][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean pengbi(int[][]maze, int [] cur, int [] dir) {
        int x = cur[0] + dir[0];
        int y = cur[1] + dir[1];
        return x < 0 || y < 0 || x >= maze.length || y >= maze[0].length || maze[x][y] == 1; 
    }
    
    boolean DFS(int [][] maze, int[] cur, int [] dest, int[][] visited) {
        for(int[] dir : dirs) {
            // dir   = {0, 1}
            int [] next = new int [2]; // deep copy 
            // why int [] next = cur; not working?
            next[0] = cur[0]; next[1] = cur[1];
            while(!pengbi(maze, next, dir)) {
                next[0] += dir[0];
                next[1] += dir[1];
            }
            // next is the final position rolling towards current direction
            if (visited[next[0]][next[1]] == 1) continue;
            // next position == dest pos
            if (next[0] == dest[0] && next[1] == dest[1])   return true;
            visited[next[0]][next[1]] = 1;
            if (DFS(maze, next, dest, visited)) return true;
            // roll toward current direction(next position) has no result we are looking for
        }
        return false;
    }
    
    public boolean hasPath(int[][] maze, int[] start, int[] dest) {
        //pre condition 
        if(maze == null || maze.length == 0 || maze[0].length == 0) return false;
        // start[0] == start_x, start[1] == start_y
        if(maze[start[0]][start[1]] ==1 || maze[dest[0]][dest[1]] ==1 ) return false;
        if(start[0] == dest[0] && start[1] == dest[1])  return true;
        int [][] visited = new int[maze.length][maze[0].length];
        visited[start[0]][start[1]] = 1;
        return DFS(maze, start, dest, visited);
    }
}
