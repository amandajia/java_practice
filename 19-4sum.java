public class Solution {
 public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length < 4) return result;
        Arrays.sort(nums);
        int len = nums.length;
        for(int i=0; i<len -3; ++i) {// 4 sum
            if(i>0 && nums[i-1] == nums[i] )    continue;
            int target1 = target - nums[i];
            for(int j = i+1; j<len-2; ++j) { // 3sum
                if(j>i+1 && nums[j-1] == nums[j])   continue;
                int target2 = target1 - nums[j];
                int start = j+ 1;
                int end = len -1;
                while(start < end) {
                    if(nums[start] + nums[end] == target2) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[start], nums[end]));
                        start++;
                        while(start < end && nums[start] == nums[start - 1])   start++;
                        end --;
                        while(start <end && nums[end] == nums[end + 1])       end--;
                    } else if(nums[start] + nums[end] > target2)
                        end--;
                    else    start++;
                }
            }
        }
        return result;
    }
}
