//https://leetcode.com/problems/unique-paths-ii/
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid==null || obstacleGrid[0][0]==1) return 0;
        int row=obstacleGrid.length;
        int col=obstacleGrid[0].length;
        int[][] path=new int[row][col];
        path[0][0]=1;
        for(int j=1;j<col;j++){
            if(obstacleGrid[0][j]==1) path[0][j]=0;
        	else path[0][j]=path[0][j-1];
        }
        for(int i=1;i<row;i++){
            if(obstacleGrid[i][0]==1) path[i][0]=0;
        	else path[i][0]=path[i-1][0];
        }
        for(int i=1;i<row;i++){
        	for(int j=1;j<col;j++){
        		if(obstacleGrid[i][j]==1) path[i][j]=0;
        		else path[i][j]=path[i][j-1]+path[i-1][j];
        	}
        }
        return path[row-1][col-1];
    }
