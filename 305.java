//https://leetcode.com/problems/number-of-islands-ii/
public class UF{
		public int[] parent;
		public int[] height;
		public int count;
		public UF(int m, int n){
		    count=0;
			parent=new int[m*n];
			height=new int[m*n];
			for(int i=0;i<m*n;i++) parent[i]=i;
		}
		public boolean find(int i,int j){
			return findRoot(i)==findRoot(j);
		}
		public int findRoot(int i){
			while(parent[i]!=i) i=parent[i];
			return i;
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
			count--;
		}
	}
	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		List<Integer> result=new LinkedList<Integer>();
		if(m<0 || n<0) return result;
        if(positions==null ) return result;
        UF unionFind=new UF(m,n);
        int[][] matrix=new int[m][n];
        for(int[] e: positions){
        	int i=e[0];
        	int j=e[1];
        	if(matrix[i][j]==1){
        	    result.add(unionFind.count);
        	    continue;
        	}
        	matrix[i][j]=1;
        	unionFind.count++;
        	int p=i*n+j;
        	if(i>0 && matrix[i-1][j]==1){
        		unionFind.union(p, p-n);
        	}
        	if(i<m-1 && matrix[i+1][j]==1){
        		unionFind.union(p, p+n);
        	}
        	if(j>0 && matrix[i][j-1]==1){
        		unionFind.union(p, p-1);
        	}
        	if(j<n-1 && matrix[i][j+1]==1){
        		unionFind.union(p, p+1);
        	}
        	result.add(unionFind.count);
        }
        return result;
    }
