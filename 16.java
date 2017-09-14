public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        int last = Integer.MIN_VALUE;
        for(int i=0; i<nums.length -2; ++i) {
            if(nums[i] == last) continue;
            twoSum(nums, - nums[i], i, ret);
            last = nums[i];
        }
        return ret;
    }
    
    public void twoSum(int[] nums, int target, int excl, List<List<Integer>> ret) {
        if(nums == null || nums.length == 0)    return ;
        Map<Integer, Integer> table = new HashMap<>();
        for(int i = excl + 1; i< nums.length; ++i) {
            if(table.containsKey(target -nums[i]) && table.get(target - nums[i]) == 1) {
                List<Integer> line = new ArrayList<>();
                line.add(-target);
                line.add(target - nums[i]);
                line.add(nums[i]);
                ret.add(line);
                table.put(target - nums[i], 2);
            }
            if(!table.containsKey(nums[i]))
                table.put(nums[i], 1);
        }
    }
}
