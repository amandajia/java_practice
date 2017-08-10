/*
A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]

Challenge:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
*/
public class Solution {
    public class UF {
        public int n;
        int [] p ;
        int [] h ;
        public UF(int num) {
            n = 0;
            p = new int[num];
            h = new int[num];
            for(int i = 0; i< num; ++i) 
                p[i] = -1;
        }
        int findP(int i) {
            while(i>= 0  && i != p[i]) i = p[i];
            return i;
        }
        public void connect(int i, int j) {
            i = findP(i);
            int oj = j;
            j = findP(j);
            if(j<0) {
                p[oj] = oj;
                n++;
               // System.out.println(oj  + " !! " );
                j = oj;
            }
            if(i < 0 || i == j) return;
            
            n--;
            if(h[i] > h[j]) {
                p[j] = i;
            } else if(h[i] < h[j]) {
                p[i] = j;
            } else {
                p[j] = i;
                h[i]++;
            }
        }
        
    }
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ret = new ArrayList<>();
        UF uf = new UF(m * n);
        int[][] dir = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        
        for(int [] pos : positions) {
            uf.connect(pos[0] *n + pos[1], pos[0] *n + pos[1]);
            for(int [] d: dir) {
                int x = d[0] + pos[0];
                int y = d[1] + pos[1];
                if(x < 0 || x>= m || y<0 || y>=n) continue;
                //System.out.println(pos[0] + " ! " + pos[1]);
                uf.connect(x * n + y, pos[0] *n + pos[1]);
            }
            ret.add(uf.n);
        }
        return ret;
    }
}
