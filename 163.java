//https://leetcode.com/problems/missing-ranges/
  public void help(List<String> result, int l, int r){
        if(l==r) result.add(Integer.toString(l));
        else{
        	StringBuilder sb=new StringBuilder();
        	sb.append(Integer.toString(l)+"->"+Integer.toString(r));
        	result.add(sb.toString());
        }
    }
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
		List<String> result=new LinkedList<String>();
        if(nums==null || lower>upper) return result;
        if(nums.length==0){
            int l=lower;
        	int r=upper;
        	help(result,l,r);
        	return result;
        } 
        int i=0;
        if(nums[0]>lower){
        	int l=lower;
        	int r=nums[0]-1;
        	help(result,l,r);
        }
        for(;i<nums.length-1;i++){
        	if(nums[i+1]-1>nums[i]){
        		int l=nums[i]+1;
            	int r=nums[i+1]-1;
            	help(result,l,r);
        	}
        }
        if(upper>nums[nums.length-1]){
            if(upper==nums[nums.length-1]){
                result.add(Integer.toString(lower));
                return result;
            }
        	int l=nums[nums.length-1]+1;
        	int r=upper;
        	help(result,l,r);
        }
        return result;
    }
