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
