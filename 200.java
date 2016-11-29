//https://leetcode.com/problems/number-of-islands/
public class UF{
		public int totalIsland=0;
		public int[] height;
		public int[] parent;
		public UF(char[][] grid, int m, int n){
			parent=new int[m*n];
			height=new int[m*n];
			for(int i=0;i<m*n;i++) parent[i]=i;
			for(int i=0;i<m;i++){
				for(int j=0;j<n;j++){
					if(grid[i][j]=='1') totalIsland++;
				}
			}
		}
		public int findRoot(int i){
			while(parent[i]!=i) i=parent[i];
			return i;
		}
		public boolean find(int i, int j){
			return findRoot(i)==findRoot(j);
		}
		public void union(int i, int j){
			i=findRoot(i);
			j=findRoot(j);
			if(i==j) return;
			if(height[i]>height[j]){
				parent[j]=i;
			}
			else if(height[i]<height[j]){
				parent[i]=j;
			}
			else{
				parent[i]=j;
				height[j]++;
			}
			totalIsland--;
		}
	}
	
	public int numIslands(char[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0) return 0;
        int m=grid.length;
        int n=grid[0].length;
        UF unionFind=new UF(grid,m,n);
        for(int i=0;i<m;i++){
        	for(int j=0;j<n;j++){
        		if(grid[i][j]=='0') continue;
        		if(i>0 && grid[i-1][j]=='1'){
        			unionFind.union(i*n+j, i*n+j-n);
        		}
        		if(i<m-1 && grid[i+1][j]=='1'){
        			unionFind.union(i*n+j, i*n+j+n);
        		}
        		if(j>0 && grid[i][j-1]=='1'){
        			unionFind.union(i*n+j, i*n+j-1);
        		}
        		if(j<n-1 && grid[i][j+1]=='1'){
        			unionFind.union(i*n+j, i*n+j+1);
        		}
        	}
        }
        return unionFind.totalIsland;
    }
