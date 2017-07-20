public class Solution {
    int [][] copy(int [][] template, int m, int n) {
        int [][] nt = new int[template.length][template[0].length];
        for(int i = 0; i < m ; ++i) {
            for(int j = 0; j< n; ++j)
                nt[i][j] = template[i][j];
        }
        return nt;
    }
    int [][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    boolean within(int x, int y , int m , int n) {
        return x >=0 && y >= 0 && x < m && y < n;
    }
    
    boolean checkValid(int [][] template) {
        for(int ii = 0; ii < template.length; ++ii) {
            for(int jj = 0; jj< template[0].length; ++jj) {
                if(template[ii][jj] == Integer.MAX_VALUE) {
                    boolean block = true;
                    for(int [] d : dirs) {
                        int x = ii + d[0];
                        int y = jj + d[1];
                        if(within(x, y, template.length, template[0].length)) {
                            if(template[x][y] == 0) {
                                block = false;
                                break;
                            }    
                        }
                    }
                    if(block)   return false;
                } 
            }
        }
        return true;
    }
    
    public int shortestDistance(int[][] grid) {
        // record all candidates
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        int [][] template = new int[m][n];
        for(int i = 0; i < grid.length; ++i) {
            for(int j = 0; j < grid[0].length; ++j) {
                if(grid[i][j] == 1) {
                    count ++;
                    template[i][j] = Integer.MAX_VALUE;
                } else if(grid[i][j] == 2) {
                    template[i][j] = -1;
                } 
            }
        }
        if(!checkValid(template))   return -1;
        int minStep = Integer.MAX_VALUE;
        for(int i =0 ; i< m; ++i) {
            for(int j = 0; j<n; ++j) {
                if(template[i][j] == 0) {
                    int[][] table =  copy(template, m, n);
                    Queue<int[]> cur = new LinkedList<>();
                    cur.add(new int[]{i, j});
                    int curStep = 0; // cur min distance to all houses
                    int curCount = 0; // cur number of house reached
                    int depth = 1; // current cur point distance to source
                    while(!cur.isEmpty() && curCount < count) {
                        if(curStep + (depth -1)*(count - curCount) >= minStep)   break;
                        Queue<int[]> next = new LinkedList<>();
                        while(!cur.isEmpty()) {
                            int[] pos = cur.poll();
                            if(table[pos[0]][pos[1]] == Integer.MAX_VALUE - 1) {
                                continue;    
                            }
                            if(table[pos[0]][pos[1]] == Integer.MAX_VALUE) { // current is a house
                                curStep += depth - 1;
                                curCount ++;
                                table[pos[0]][pos[1]] --;
                            } else {
                                for(int [] d: dirs) {
                                    int x = d[0] + pos[0];
                                    int y = d[1] + pos[1];
                                    if(within(x, y, m, n) && (table[x][y] == 0 || table[x][y] == Integer.MAX_VALUE)) {
                                        next.add(new int[]{x, y});
                                    }
                                }      
                                table[pos[0]][pos[1]] = depth;
                            }
                            
                        }
                        depth++;
                        cur = next;
                    }
                    if(cur.isEmpty() && curCount < count) {
                        for(int ii = 0; ii < m; ++ii) {
                            for(int jj = 0; jj<n; ++jj) {
                                if(table[ii][jj] < Integer.MAX_VALUE - 1 && table[ii][jj] > 0) {
                                    template[ii][jj] = -1;
                                }    
                            }
                        }
                        if(!checkValid(template))   return -1;
                        
                        
                    }
                    
                    if(curCount == count && curStep < minStep) {
                        minStep = curStep;
                    }
                    
                }    
            }
        }
        return minStep == Integer.MAX_VALUE? -1: minStep;
    }
}
