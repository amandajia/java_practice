//https://leetcode.com/problems/longest-consecutive-sequence/
public class UF{
		public int[] parent;
		public int[] height;
		public int result=0;
		public UF(int n){
		    parent=new int[n];
		    height = new int[n];
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
		public void union(int i, int j){
			i=findRoot(i);
			j=findRoot(j);
			if(i==j) return;
			if(height[i]>height[j]) parent[j]=i;
			else if(height[i]<height[j]) parent[i]=j;
			else{
				parent[i]=j;
				height[j]++;
			}
		}
		public int maxUnion(){
			int[] count=new int[parent.length];
	        for(int i=0;i<parent.length;i++){
	            int root = findRoot(i);
	        	count[root]++;
	        	result=Math.max(result, count[root]);
	        }
	        return result;
		}
	}
	
	public int longestConsecutive(int[] nums) {
        if(nums==null || nums.length==0) return 0;
        UF unionFind =new UF(nums.length);
        HashMap<Integer, Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
        	if(map.containsKey(nums[i])) continue;
        	map.put(nums[i], i);
        	if(map.containsKey(nums[i]+1)){
        		unionFind.union(i, map.get(nums[i]+1));
        	}
        	if(map.containsKey(nums[i]-1)){
        		unionFind.union(i, map.get(nums[i]-1));
        	}
        }
        return unionFind.maxUnion();
    }
