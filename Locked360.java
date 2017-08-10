/*
Given a sorted array of integers nums and integer values a, b and c. Apply a function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

Example:
nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,

Result: [3, 9, 15, 33]

nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5

Result: [-23, -5, 1, 7]
Credits:
Special thanks to @elmirap for adding this problem and creating all test cases.
*/
public class Solution {
    int [] single(int [] nums, int b, int c) {
        int [] ret = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            int k = (b > 0)? i: nums.length - 1 - i;
            ret[i] = nums[k] * b + c;
        }
        return ret;
    }
    
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if(a == 0)  return single(nums, b, c);
        int [] pre  = new int[nums.length];
        int [] post = new int[nums.length];
        int count = -1;
        int i = 0;
        for(; i < nums.length; ++i) {
            if(count < 0 && nums[i] > -1.0 * b / (2.0 * a))  count = i;
            pre[i] = a * nums[i] * nums[i] + b * nums[i] + c; 
        }
        int k = count; 
        int j = count - 1;
        i = (a > 0)? 0: nums.length - 1;
        while(k < nums.length || j >= 0 ) {
            if(k>= nums.length) {
                post[i] = pre[j--];
            } else if ( j< 0) {
                post[i] = pre[k++];
            } else {
                if(a > 0) {
                    if(pre[j] > pre[k]) {
                        post[i] = pre[k++];     
                    } else {
                        post[i] = pre[j--]; 
                    }   
                } else {
                    if(pre[j] > pre[k]) {
                        post[i] = pre[j--];     
                    } else {
                        post[i] = pre[k++]; 
                    }
                }
            }
            if(a > 0) i++;
            else    i--;
        }
        return post;
    }
}
