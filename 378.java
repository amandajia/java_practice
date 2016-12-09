//https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
public class Solution {
    int [] find(int[] row, int start, int end, int pivot) {
        while(start < end) { 
            int mid = (start + end )/2;
            if(row[mid] > pivot) {
                end = mid;
            } else if (row[mid] < pivot) {
                start = mid + 1;
            } else {
                int []ret = new int[2];
                int i = mid - 1; 
                while(i> start && row[i] == pivot) {
                    i--;
                } 
                if(i< start || row[i] != pivot) i++;
                ret[0] = i;
                i = mid + 1; 
                while(i < end && row[i] == pivot) {
                    i++;
                } 
                if(i == end || row[i] != pivot) i--;
                ret[1] = i;
                return ret;
            }
        }
        return new int[] {start};
    }
    
    public int kthSmallest(int[][] matrix, int k) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int [][]range = new int [m][2];
        for(int i=0; i<m; ++i) {
            range[i][0] = 0;
            range[i][1] = n;
        }
        k--;
        while(true) {
            List<Integer> list = new ArrayList<>();
            for(int i=0; i< m; ++i) {
                if(range[i][0]<range[i][1])
                    list.add(i);
            }
            int row = list.get(list.size()/2);
            int col = (range[row][0] + range[row][1]) /2;
            int pivot = matrix[row][col];
            int smaller = 0;
            List<int []> same = new ArrayList<>();
            for(int i =0; i< m; ++i) {
                int [] line = find(matrix[i], range[i][0], range[i][1], pivot);
                smaller += line[0];
                if(line.length == 1)
                    same.add(new int[] {line[0]});
                else {
                    same.add(new int[] {line[0], line[1]});
                }
            }
            if(smaller > k) {
                for(int i =0; i<m; ++i) {
                    range[i][1] = same.get(i)[0];
                }
            } else {
                int count = 0;
                for(int i = 0; i< m; ++i) {
                    int[] line = same.get(i);
                    if(line.length == 2) {
                        count += line[1] - line[0] + 1;
                    }
                }
                if(count + smaller <= k) {
                    for(int i =0; i<m; ++i) {
                        if(same.get(i).length > 1)
                            range[i][0] = same.get(i)[1] + 1;
                        else {
                            range[i][0] = same.get(i)[0];
                        }
                    }
                } else {
                    return pivot;
                }
            }
        }
    }
}
