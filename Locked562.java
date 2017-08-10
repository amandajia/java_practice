/*
Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.

Example:
Input:
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
Output: 3
Hint: The number of elements in the given matrix will not exceed 10,000.
*/
public class Solution {
    public int longestLine(int[][] M) {
        if(M ==null || M.length == 0 || M[0].length == 0)   return 0;
        int max = 0;
        int m = M.length;
        int n = M[0].length;
        for(int i = 0; i < m; ++i) {
            for(int j = 0 ; j < n ; ++j) {
                if(M[i][j] == 1) {
                    int count = 0;
                    while(j < n && M[i][j] == 1) {
                        count++;
                        max = Math.max(max, count);
                        j++;
                    }
                }
            }
        }
        
        for(int i = 0; i < n; ++i) {
            for(int j = 0 ; j < m ; ++j) {
                if(M[j][i] == 1) {
                    int count = 0;
                    while(j < m && M[j][i] == 1) {
                        count++;
                        max = Math.max(max, count);
                        j++;
                    }
                }
            }
        }
        for(int i =0; i< m; ++i) {
            for(int j = 0, k = i; k < m && j< n; ++k, ++j) {
                if(M[k][j] == 1) {
                    int count = 0;
                    while(k< m && j < n && M[k][j] == 1) {
                        count++;
                        max = Math.max(max, count);
                        j++;
                        k++;
                    }
                }
            }
        }
        for(int i = 1; i< n; ++i) {
            for(int j = 0, k = i; k < n && j< m; ++k, ++j) {
                if(M[j][k] == 1) {
                    int count = 0;
                    while(k< n && j < m && M[j][k] == 1) {
                        count++;
                        max = Math.max(max, count);
                        j++;
                        k++;
                    }
                }
            }
        }
        
        for(int i = 0; i< n; ++i) {
            for(int j = 0, k = i; k >=0 && j< m; --k, ++j) {
                if(M[j][k] == 1) {
                    int count = 0;
                    while(k>=0 && j < m && M[j][k] == 1) {
                        count++;
                        max = Math.max(max, count);
                        j++;
                        k--;
                    }
                }
            }
        }
        for(int i = 0; i< m; ++i) {
            for(int j = n-1, k = i; k < m && j >= 0; ++k, --j) {
                if(M[k][j] == 1) {
                    int count = 0;
                    while(k < m && j >= 0  && M[k][j] == 1) {
                        count++;
                        max = Math.max(max, count);
                        j--;
                        k++;
                    }
                }
            }
        }
        return max;
    }
}
