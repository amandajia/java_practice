public class Solution {
    int [] nums;
    int [] s;
    public Solution(int[] nums) {
        this.nums = nums;
        this.s = new int[nums.length];
        for(int i =0; i<nums.length; ++i) {
            s[i] = nums[i];
        }
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        for(int i=0; i<s.length; ++i) 
            s[i] = nums[i];
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        Random rand = new Random();
        for(int i = nums.length - 1; i >= 1 ; i--) {
            int r = rand.nextInt(i + 1);
            int temp = s[i];
            s[i] = s[r];
            s[r] = temp;
        }
        return s;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
