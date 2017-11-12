public class Solution {
    public double myPow(double x, int n) {
        if(n == 0 || x == 1)  return 1;
        boolean divide = false;
        boolean multiple = false;
        if(n < 0) {
            divide = true;
            if(n == Integer.MIN_VALUE) {
                n = Integer.MAX_VALUE;
                multiple = true;
            }else {
                n = n * -1;
            }
        }
        double ret = 1;
        double factor = x;
        while(n > 0) {
            if(n%2==1) {
                ret *= factor;
            }
            n = (n >> 1);
            factor = factor * factor;
        }
        
        if(multiple) 
            ret *= ret;
        if(divide)
            return 1/ret;
        return ret;
    }
}
