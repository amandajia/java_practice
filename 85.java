public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)   return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int ret = 0;
        int [] height = new int[n];
        int [] left   = new int[n];
        int [] right  = new int[n];
        for(int i =0; i< n; ++i)    right[i] = n;
        for(int k = 0; k < m; ++k) {
            int cur_left = 0;
            int cur_right = n;
            for(int i = n - 1; i>=0; i--) {
                if(matrix[k][i] == '1') {
                    right[i] = Math.min(right[i], cur_right);
                } else {
                    right[i] = n;
                    cur_right = i;
                }
            }
            for(int i =0; i < n; ++i) {
                if(matrix[k][i] == '1') {
                    height[i] ++;
                    left[i] = Math.max(cur_left, left[i]);
                    if(height[i] *(right[i] - left[i]) > ret) {
                        ret = height[i] *(right[i] - left[i]);
                    } 
                } else {
                    height[i] = 0;
                    left[i] = 0;
                    cur_left = i + 1;
                }
            }
            /*
            for(int i = 0 ; i< n; ++i) 
                System.out.print(height[i] + ", ");
            System.out.println("left ");
            for(int i = 0 ; i< n; ++i) 
                System.out.print(left[i] + ", ");
            System.out.println("right ");
            for(int i = 0 ; i< n; ++i) 
                System.out.print(right[i] + ", ");
            System.out.println("line " + k);
            */
        }
        return ret;
    }
}
