// https://leetcode.com/problems/divide-two-integers/
public class Solution {
    public int divide(int dividend, int divisor) {
        if(divisor == 0)    throw new RuntimeException("divisor can't be 0");
        if(divisor == 1)    return dividend;
        if(dividend == Integer.MIN_VALUE && divisor == -1)  return Integer.MAX_VALUE;
        if(dividend == Integer.MIN_VALUE && divisor == Integer.MIN_VALUE)   return 1;
        if(divisor == Integer.MIN_VALUE)    return 0;
        int sign = 1;
        int carry = 0;
        if(dividend < 0) {
            sign *= -1;
            dividend *= -1;
            if(dividend < 0) {
                carry = 1;
                dividend = Integer.MAX_VALUE;
            }
        } 
        if(divisor < 0) {
            sign *= -1;
            divisor *= -1;
        }
        // yu = dividend/ divisor;
        int yu = 0;
        int count = 0;
        int d = divisor;
        while((d<<1) > 0 && d < dividend) {
            d<<=1;
            count++;
        }
        if((d << 1) > 0) {
            d >>= 1;
            count --;
        }
        
        //System.out.println(d + " : " + dividend + " : " + count);
        while(count >= 0) {
            yu <<=1;
            if(dividend >= d) {
                dividend = dividend - d;
                yu++;
            }
            //System.out.println(dividend + " ! " + d + " ! " + yu);
            d >>= 1;
            count--;
        }
        if(carry + dividend >= divisor) 
            yu++;
        return yu * sign;
    }
}
