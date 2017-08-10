/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (0, 1)

Output: "lul"
Explanation: There are two shortest ways for the ball to drop into the hole.
The first way is left -> up -> left, represented by "lul".
The second way is up -> left, represented by 'ul'.
Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".

Example 2

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (3, 0)
Output: "impossible"
Explanation: The ball cannot reach the hole.

Note:
There is only one ball and one hole in the maze.
Both the ball and hole exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.

*/

public class Solution {
    int [][] dirs = new int[][] {{1, 0}, {0, -1}, {0, 1}, {-1, 0}}; 
    char[] sxzy=new char[]{'d','l','r','u'}; 
    static class Point { 
        int x; 
        int y; 
        int w; 
        public Point(int xx, int yy, int ww) { 
            x= xx; y = yy; w = ww; 
        } 
    } 
    public boolean deadEnd(int[][] maze, int[] curPos, int[] dir, int[] hole){ 
        int x=curPos[0]+dir[0]; 
        int y=curPos[1]+dir[1]; 
        if(x<0 || y<0 || x>=maze.length || y>=maze[0].length) return true; 
        if(maze[x][y]==1) return true; 
        return drop(curPos, hole); 
    } 
     
    boolean drop(int []cur, int []hole) { 
        return (cur[0] == hole[0]) && cur[1] == hole[1]; 
    } 
     
    int getDir(Point o, Point n) { 
        if(n.x > o.x)   return 0; 
        if(n.y < o.y)   return 1; 
        if(n.y > o.y )  return 2; 
        return 3; 
    } 
     
    void roll(int[][]maze, Point[][]dist, Point cur, PriorityQueue<Point> unvisited, int[] hole) { 
        for(int[] dir : dirs){ 
            int[] next=new int[2]; 
            next[0]=cur.x; 
            next[1]=cur.y; 
            int curDist = 0; 
            while(!deadEnd(maze,next,dir, hole)){ 
                next[0] += dir[0]; 
                next[1] += dir[1]; 
                curDist ++; 
            } 
            if(curDist == 0)    continue; 
            int totalDist = cur.w + curDist; 
            Point n = dist[next[0]][next[1]] ; 
            Point finalP = new Point(next[0], next[1], totalDist); 
            if(n.w > totalDist) { 
                n.x = cur.x; 
                n.y = cur.y; 
                unvisited.add(finalP); 
                n.w = totalDist; 
            } else if(n.w == totalDist) {
                String old = generateRet(dist, next);
                int oldx = n.x;
                int oldy = n.y;
                n.x = cur.x;
                n.y = cur.y;
                String newP = generateRet(dist, next);
                if(old.compareTo(newP)< 0) {
                    n.x= oldx;
                    n.y= oldy;
                }
            }
        }    
    } 
     
    String generateRet(Point[][] dist, int [] hole){ 
        StringBuilder sb = new StringBuilder(); 
        Point p = new Point(hole[0], hole[1], 0); 
        while(dist[p.x][p.y].w != 0) {  // not origin
            Point last = dist[p.x][p.y]; 
            sb.append(sxzy[getDir(last, p)]); 
            p.x = last.x; 
            p.y = last.y; 
        } 
        return sb.reverse().toString(); 
    } 
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) { 
        if(maze== null || maze.length == 0 || maze[0].length == 0)  return "impossible"; 
        if(maze[ball[0]][ball[1]] == 1)    return "impossible"; 
        if(ball[0] == hole[0] && ball[1] == hole[1])  return ""; 
        PriorityQueue<Point> unvisited=new PriorityQueue<>((o1,o2) -> o1.w-o2.w); 
        Point[][] distance=new Point[maze.length][maze[0].length]; 
        for(int i=0;i<maze.length;i++){ 
            for(int j=0;j<maze[0].length;j++){ 
                distance[i][j]=new Point(0,0,Integer.MAX_VALUE); 
            } 
        } 
        distance[ball[0]][ball[1]].w=0; 
        unvisited.add(new Point(ball[0], ball[1], 0)); 
        while(!unvisited.isEmpty()){ 
            Point temp=unvisited.poll(); 
            if(temp.x==hole[0] && temp.y==hole[1]) return generateRet(distance, hole); 
            if(temp.w>distance[temp.x][temp.y].w) continue; 
            roll(maze,distance,temp,unvisited, hole); 
        } 
        if(distance[hole[0]][hole[1]].w == Integer.MAX_VALUE) return "impossible"; 
        return generateRet(distance, hole); 
    } 
}
