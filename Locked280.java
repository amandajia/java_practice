/*
Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
*/public class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        // 1, 2, 3, 4
        // 1, 2, 3, 4, 5
        int n = nums.length;
            
        int mid = (n % 2 == 0)? n/2 : n/2+1;
        int j = ((n-1)%2 == 0)? n-1: n-2;
        for(int i = 1; i< mid; i+=2, j-=2) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
