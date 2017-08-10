/*
Given a picture consisting of black and white pixels, and a positive integer N, find the number of black pixels located at some specific row R and column C that align with all the following rules:

Row R and column C both contain exactly N black pixels.
For all rows that have a black pixel at column C, they should be exactly the same as row R
The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

Example:
Input:                                            
[['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'W', 'B', 'W', 'B', 'W']] 

N = 3
Output: 6
Explanation: All the bold 'B' are the black pixels we need (all 'B's at column 1 and 3).
        0    1    2    3    4    5         column index                                            
0    [['W', 'B', 'W', 'B', 'B', 'W'],    
1     ['W', 'B', 'W', 'B', 'B', 'W'],    
2     ['W', 'B', 'W', 'B', 'B', 'W'],    
3     ['W', 'W', 'B', 'W', 'B', 'W']]    
row index

Take 'B' at row R = 0 and column C = 1 as an example:
Rule 1, row R = 0 and column C = 1 both have exactly N = 3 black pixels. 
Rule 2, the rows have black pixel at column C = 1 are row 0, row 1 and row 2. They are exactly the same as row R = 0.

Note:
The range of width and height of the input 2D array is [1,200].
*/
public class Solution {
    boolean compare(char[][]picture, int i, int j) {
        for(int k = 0; k < picture[0].length; ++k)  
            if(picture[i][k] != picture[j][k])  return false;
        return true;
    }
    
    public int findBlackPixel(char[][] picture, int N) {
        if(picture == null || picture.length == 0 || picture[0].length == 0)    return 0;
        List<Integer> rows = new ArrayList<>();
        int [] parent = new int [picture.length];
        for(int i = 0; i< picture.length; ++i) {
            parent[i] = i;
        }
        List<Integer> roots = new ArrayList<>();
        for(int i = 0; i < picture.length; ++i) {
            for(int can : roots) {
                if(compare(picture, i, can)) {
                    parent[i] = can;
                    break;
                }
            }
            if(parent[i] == i)  roots.add(i);
            int count = 0;
            for(int j = 0; j< picture[0].length; ++j) 
                if(picture[i][j] == 'B')    
                    count++;
            if (count == N) rows.add(i);
        }
        
        // System.out.println(roots);
        int ret = 0;
        for(int i = 0; i < picture[0].length; ++i) { 
            List<Integer> same = new ArrayList<>();
            for(int j = 0; j < picture.length; ++j ) {
                if(picture[j][i] == 'B')    {
                    same.add(j);    
                }
                if(same.size() > N)
                    break;
            }
            if(same.size() == N) {
                // System.out.println(rowCount + " " + same);
                int k = 1;
                for(; k< same.size(); k++) {
                    if(parent[same.get(k)]!= parent[same.get(k - 1)])
                        break;
                } 
                if(k == same.size()){
                    
                    for(int base: rows) {
                        if(parent[same.get(0)] == parent[base]) ret++;
                    }
                }
                if (same.size() == 0) {
                    ret += rows.size();
                    continue;
                }
                
            }
               
        }
        return ret;
    }
}
