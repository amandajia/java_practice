//https://leetcode.com/problems/surrounded-regions/
public class UF{
		public int[] parent;
		public int[] height;
		public int count;
		public UF(int m, int n){
			count=m*n;
			parent=new int[m*n + 1];
			height=new int[m*n + 1];
			for(int i=0;i<m*n;i++){
				parent[i]=i;
			}
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
	public void solve(char[][] board) {
        if(board==null || board.length==0 || board[0].length==0) return;
        int m=board.length;
        int n=board[0].length;
        UF unionFind=new UF(m,n);
        for(int i=0;i<m;i++){
        	for(int j=0;j<n;j++){
        		if((i==0 || i==m-1 || j==0 || j==n-1) && (board[i][j])=='O'){
        			unionFind.union(i*n+j, n*m);
        		}
        		else if(board[i][j]=='O'){
        			int p=i*n+j;
        			if(i<m-1 && board[i+1][j]=='O'){
        				unionFind.union(p,p+n);
        			}
        			if(i>0 && board[i-1][j]=='O'){
        				unionFind.union(p,p-n);
        			}
        			if(j<n-1 && board[i][j+1]=='O'){
        				unionFind.union(p, p+1);
        			}
        			if(j>0 && board[i][j-1]=='O'){
        				unionFind.union(p, p-1);
        			}
        		}
        		
        	}
        }
        for(int i=0;i<m;i++){
        	for(int j=0;j<n;j++){
        		if(!unionFind.find(i*n+j, n*m)){
        			board[i][j]='X';
        		}
        	}
        }
    }
