public class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        boolean found = false;
        for(int i = n-1; i>0; --i) {
            if(nums[i] <= nums[i-1])    continue;
            int cur = nums[i - 1];
            int j;
            for(j=n-1; j>=i; --j)
                if(nums[j] > cur)
                  break;
            nums[i-1] = nums[j];
            nums[j] = cur;
            Arrays.sort(nums, i, n);
            found = true;
            break;
        }
        if(!found)
            Arrays.sort(nums);
        
    }
}
