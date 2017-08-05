public class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if(nums == null || nums.length == 0) return 0;
        for(int i= 1; i<nums.length; ++i)
            nums[i] += nums[i-1];
        int max = 0;
        Map<Integer, Integer> table = new HashMap<>();
        table.put(0, -1);
        for(int i = 0; i< nums.length; ++i) {
            if(table.containsKey(nums[i] - k)) {
                max = Math.max(max, i - table.get(nums[i] - k));
            }
            if(!table.containsKey(nums[i])) {
                table.put(nums[i], i);
            }
            
        }
        return max;
    }
}
