/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/
public class Solution {
    
    public class UF {
        int p [];
        int h [];
        public int num;
        public UF (int n) {
            p = new int[n];
            h = new int[n];
            num = n;
            for(int i = 0; i< n; ++i)
                p[i] = i;
        }
        
        int findP(int i) {
            while(i != p[i])    i = p[i];
            return i;
        }
        
        boolean connect(int i, int j) {
            i = findP(i);
            j = findP(j);
            if(i == j)  return true;
            if(h[i] > h[j] ){
                p[j] = i;
            } else if(h[i] < h[j]) {
                p[i] = j;
            } else {
                p[j] = i;
                h[i]++;
            }
            num --;
            return false;
        }
        
    }
    public boolean validTree(int n, int[][] edges) {
        UF uf = new UF(n);
        for(int[] e: edges){
            if(uf.connect(e[0], e[1])) return false;
        }
        return uf.num == 1;
    }
}
