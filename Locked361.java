/*
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note that you can only put the bomb at an empty cell.

Example:
For the given grid

0 E 0 0
E 0 W E
0 E 0 0

return 3. (Placing a bomb at (1,1) kills 3 enemies)
Credits:
Special thanks to @memoryless for adding this problem and creating all test cases.
*/
public class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int ret = 0;
        int m = grid.length;
        int n = grid[0].length;
        Map<Integer, Integer> table = new HashMap<>();
        for(int i = 0; i< grid.length; ++i) {
            int count = 0;
            Stack<Integer> toAssign = new Stack();
            for(int j = 0; j< grid[0].length; ++j) {
                switch(grid[i][j]) {
                    case 'E':
                        count++;
                        break;
                    case 'W':
                        while(!toAssign.isEmpty()) {
                            table.put(i * n + toAssign.pop(), count);
                        }
                        count = 0;
                        break;
                    case '0':
                        toAssign.push(j);
                        break;
                } 
            }
            while(!toAssign.isEmpty()) {
                table.put(i * n + toAssign.pop(), count);
            }
        }
        
        for(int j = 0; j< grid[0].length; ++j) {
            int count = 0;
            Stack<Integer> toAssign = new Stack();
            for(int i = 0; i< grid.length; ++i) {
                switch(grid[i][j]) {
                    case 'E':
                        count++;
                        break;
                    case 'W':
                        while(!toAssign.isEmpty()) {
                            int cur = table.get(toAssign.pop()*n + j) + count;
                            if(cur > ret)  ret = cur;
                        }
                        count = 0;
                        break;
                    case '0':
                        toAssign.push(i);
                        break;
                } 
            }
            while(!toAssign.isEmpty()) {
                int cur = table.get(toAssign.pop()*n + j) + count;
                if(cur > ret)  ret = cur;
            }
        }
        return ret;
    }
}
