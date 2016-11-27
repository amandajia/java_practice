public class Solution {
    public class UnionFind{
        public int conponent;
        int[] height;
        int[] parent;
        public UnionFind(int n){
            parent=new int[n];
            height=new int[n];
            for(int i=0;i<n;i++){
                parent[i]=i;
            }
            conponent=n;
        }
        public boolean find(int i, int j){
            return findRoot(i)==findRoot(j);
        }
        public int findRoot(int i){
            while(i!=parent[i]) i=parent[i];
            return i;
        }
        public void connect(int i, int j){
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
            //System.out.println("i= "+i+"   "+"j= "+j+"    "+"rootI= "+rootI+"    "+"rootJ= "+rootJ);
            conponent--;
        }
    }
    public int countComponents(int n, int[][] edges) {
        UnionFind wf=new UnionFind(n);
        for(int[] e : edges){
            wf.connect(e[0], e[1]);
        }
        return wf.conponent;
    }
}
