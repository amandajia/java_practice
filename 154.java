public class Solution {
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length -1;
        int ret = Integer.MAX_VALUE;
        while(start <= end) {
            int mid = start + (end - start)/2;
            if(nums[mid] > nums[end]) {
                start = mid + 1;
            } else if(nums[mid] > nums[start]) {
                end = mid - 1;
            } else if(nums[mid] < nums[start]){
                ret = Math.min(ret, nums[mid]);
                end = mid - 1;
            } else {
                ret = Math.min(ret, nums[mid]);
                start ++;
            }
        }
        return ret;
    }
}
