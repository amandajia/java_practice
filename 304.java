//https://leetcode.com/problems/range-sum-query-2d-immutable/
public class NumMatrix {
    public int[][] box;
    public NumMatrix(int[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0){
            box=null;
            return;
        }
        int row=matrix.length;
        int col=matrix[0].length;
        box=new int[row][col];
        box[0][0]=matrix[0][0];
        for(int i=0;i<row;i++) box[i][0]=matrix[i][0];
        for(int i=0;i<row;i++){
            for(int j=1;j<col;j++){
                box[i][j]=box[i][j-1]+matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(box==null) return 0;
        int total=0;
        if(col1==0){
            for(int i=row1;i<=row2;i++) total+=box[i][col2];
            return total;
        }
        for(int i=row1;i<=row2;i++){
            total+=box[i][col2]-box[i][col1-1];
        }
        return total;
    }
}
