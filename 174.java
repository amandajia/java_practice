public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon == null || dungeon.length == 0 || dungeon[0].length == 0)  return 1;
        int m = dungeon.length;
        int n = dungeon[0].length;
        int [][] box = new int [m + 1 ][n + 1];
        for(int j = 0; j<= m; ++j)
        for(int i= 0; i<= n; ++i)
            box[j][i] = Integer.MAX_VALUE;
        box[m][n - 1] = 0;
        // box saves the min HP prior to step in the grid
        for(int i= m - 1; i >= 0 ; i--)
            for(int j = n -1; j >= 0; j--)
                box[i][j] = Math.max(0, Math.min(box[i][j + 1], box[i + 1][j]) - dungeon[i][j]);
        
        return box[0][0] + 1;
    }
}
