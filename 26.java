public class Solution {
    public int removeDuplicates(int[] nums) {
        int index = 0;
        for(int i = 0; i< nums.length; ++i ) {
            boolean contains = false;
            for(int j =0; j< index; ++j) {
                if (nums[j] == nums[i]) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                nums[index] = nums[i];
                index ++;
            }
        }
        return index;
    }
}
