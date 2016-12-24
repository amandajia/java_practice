//https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
public int maxSubArrayLen(int[] nums, int k) {
        if(nums==null || nums.length==0) return 0;
        int max=0;
        int length=nums.length;
        int sum=0;
        HashMap<Integer,Integer> box=new HashMap<>();
        for(int i=0;i<length;i++){
            sum+=nums[i];
            if(sum==k) max=i+1;
            else if(box.containsKey(sum-k)) max=Math.max(max, i-box.get(sum-k));
            if(!box.containsKey(sum)) box.put(sum,i);
        }
        return max;
    }
