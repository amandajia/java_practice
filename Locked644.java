/*
Google
Given an array consisting of n integers, find the contiguous subarray whose length is greater than or equal to k that has the maximum average value. And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation:
when length is 5, maximum average value is 10.8,
when length is 6, maximum average value is 9.16667.
Thus return 12.75.
Note:
1 <= k <= n <= 10,000.
Elements of the given array will be in range [-10,000, 10,000].
The answer with the calculation error less than 10-5 will be accepted.
*/
public class Solution {
    boolean bs(int [] nums, double mid, int k) {
        double [] ar = new double [nums.length];
        for(int i = 0; i < nums.length; ++i)    ar[i] = nums[i] - mid;
        double pre = 0;
        double cur = 0;
        for(int i = 0 ; i< k; ++i) {
            cur += ar[i];
        }
        if(cur >= 0)    return true;
        for(int i= k; i< nums.length; ++i) {
            cur += ar[i];
            pre += ar[i - k];
            if(pre < 0) {
                cur -= pre;
                pre = 0;
            }
            if(cur >= 0)
                    return true;
        }
        return false;
        
    }
    
    public double findMaxAverage(int[] nums, int k) {
        double l = -10001;
        double r = 10001;
        while(r - l > 0.000003) {
            double mid = (l + r)/2;
            if(bs(nums, mid, k)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
