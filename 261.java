//https://leetcode.com/problems/graph-valid-tree/
public class UF{
        public boolean result = true;
        int[] parent;
        int[] height;
        public UF(int n){
        	parent=new int[n];
        	height=new int[n];
            for(int i=0;i<n;i++){
                parent[i]=i;
            }
        }
        public int findRoot(int i){
        	while(parent[i]!=i) i=parent[i];
        	return i;
        }
        public boolean find(int i, int j){
        	return findRoot(i)==findRoot(j);
        }
        public boolean union(int i, int j){
        	i=findRoot(i);
        	j=findRoot(j);
        	if(i==j){
        	    result=false;
        	    return result;
        	}
        	if(height[i]>height[j]){
        		parent[j]=i;
        	}
        	else if(height[i]<height[j]){
        		parent[j]=i;
        	}
        	else{
        		parent[i]=j;
        		height[j]++;
        	}
        	return result;
        }
    }
    public boolean validTree(int n, int[][] edges) {
        if(n<=0 || edges==null) return false;
        UF unionFind=new UF(n);
        for(int[] e : edges){
        	if(unionFind.union(e[0],e[1])==false) return false;
        }
        return n-1==edges.length;
    }
