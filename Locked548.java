/*

Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:

0 < i, i + 1 < j, j + 1 < k < n - 1
Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
where we define that subarray (L, R) represents a slice of the original array starting from the element indexed L to the element indexed R.
Example:
Input: [1,2,1,2,1,2,1]
Output: True
Explanation:
i = 1, j = 3, k = 5. 
sum(0, i - 1) = sum(0, 0) = 1
sum(i + 1, j - 1) = sum(2, 2) = 1
sum(j + 1, k - 1) = sum(4, 4) = 1
sum(k + 1, n - 1) = sum(6, 6) = 1
Note:
1 <= n <= 2000.
Elements in the given array will be in range [-1,000,000, 1,000,000].
*/
public class Solution {
    public boolean splitArray(int[] nums) {
        if(nums == null || nums.length < 7) return false;
        int start = 0;
        int target = 0;
        int [] sums = new int [nums.length];
        sums[0] = nums[0];
        for(int i = 1; i< nums.length; ++i) {
            sums[i] = nums[i] + sums[i - 1];
        }
        for(int i = 0; i < nums.length - 6; ++i) {
            target += nums[i];
            int first = find(sums, target, i + 1, i + 1);
            if( first < 0 || first == nums.length)   continue;
            Stack<Integer> sol = new Stack();
            sol.add(first);
            while(sol.size() > 0) {
                start = sol.peek();
                while(sol.size() < 3) {
                    start = find(sums, target, start, start);
                    if(start > 0) {
                        sol.add(start);
                    } else {
                        break;
                    }
                }    
                if(sol.size() == 3 && sol.peek() == nums.length) {
                    //System.out.println(target + " : " + sol.pop() + " : " + sol.pop() + " | " +sol.pop());
                    return true;
                }
                Integer nextTry = null;
                while(!sol.isEmpty()) {
                    int last = sol.pop();
                    int sp = sol.isEmpty()? i + 1 : sol.peek();
                    nextTry = find(sums, target, sp, last);
                    if(nextTry != -1) {
                        sol.push(nextTry);
                        break;
                    }
                }
            }
        }
        return false;
    }
    int find(int [] sums, int target, int start, int least) {
        for(int i = least + 1; i < sums.length; ++i) {
            if(sums[i] - sums[start] == target) return i + 1;
        }
        return -1;
    }  
    
}
