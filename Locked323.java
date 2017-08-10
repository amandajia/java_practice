/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:
     0          3
     |          |
     1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:
     0           4
     |           |
     1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.


*/

public class Solution {
    public class UF {
        int[] parent ;
        int[] height;
        public int com;
        public UF (int n) {
            com = n;
            parent = new int[n];
            height = new int[n];
            for(int i = 0; i< n; ++i)
                parent[i] = i;
        }
        public boolean find(int i, int j) {
          return findRoot(i) == findRoot(j);
        }
        
        private int findRoot(int i) {
            while(i!=parent[i]) i = parent[i];
            return i;
        }
        
        public void connect(int i, int j) {
            i = findRoot(i);
            j = findRoot(j);
            if(i == j)  return;
            com --;
            if(height[i] > height[j]) {
                parent[j] = i;
            } else if(height[i] < height[j]) {
                parent[i] = j;
            } else {
                parent[j] = i;
                height[i]++;
            }
        }
    }
    public int countComponents(int n, int[][] edges) {
        UF union = new UF(n);
        for(int[] edge: edges) {
            union.connect(edge[0] , edge[1]);
        }
        return union.com;
    }
}
