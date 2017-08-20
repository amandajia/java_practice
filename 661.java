class Solution {
    int [][] sur = new int[][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1,1}};
    
    boolean valid(int x, int y, int m, int n) {
        return  x>=0 && y >=0 && x<m && y< n;
    }
    public int[][] imageSmoother(int[][] M) {
        if(M== null || M.length == 0 || M[0].length == 0)   return M;
        int m = M.length;
        int n = M[0].length;
        int [][] ret = new int[m][n];
        for(int i = 0; i< m;++i) {
            for(int j = 0; j< n; ++j) {
                int count = 0;
                int sum = 0;
                for(int [] dir : sur) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if(valid(x,y,m,n)) {
                        count ++;
                        sum += M[x][y];
                    }
                }
                ret[i][j] = sum /count;
            }
        }
        return ret;
    }
}
