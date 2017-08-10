/*
Given a positive integer a, find the smallest positive integer b whose multiplication of each digit equals to a.

If there is no answer or the answer is not fit in 32-bit signed integer, then return 0.

Example 1
Input:

48 
Output:
68
Example 2
Input:

15
Output:
35

*/
public class Solution {
    int fac(int a, int [] c) {
        int [] f = new int [] {2, 3, 5, 7};
        for(int i =0 ; i < f.length; ++i) {
            while(a % f[i] == 0) {
                c[i] ++;
                a /= f[i];
            }
        }
        return a;
    }
    
    public int smallestFactorization(int a) {
        if(a == 1)  return 1;
        int [] count = new int[4]; // 2, 3, 5, 7
        int mod = fac(a, count);
        if(mod != 1) return 0;
        List<Integer> ret = new ArrayList<>();
        
        int num8 = count[0] / 3;
        if(num8 > 10) {
            return 0;
        }
        while(num8 > 0) {
            ret.add(8);
            num8 --;
        }
        
        int num9 = count[1] / 2;
        if(num9 > 10) {
            return 0;
        }
        while(num9 > 0) {
            ret.add(9);
            num9 --;
        }
        int num2 = count[0] % 3; 
        
        int num3 = count[1] % 2;
        if(num3 == 0) {
            ret.add(2 * num2);
        } else if(num2 == 0) {
            ret.add(3);
        } else {
            ret.add(6);
            if(num2 > 1)    ret.add(2);
        }
        
        if(ret.size() + count[2] + count[3] > 10)   return 0;
        while(count[2] > 0 ) {
            ret.add(5);
            count[2] --;
        }
        while(count[3] > 0 ) {
            ret.add(7);
            count[3] --;
        }
        Collections.sort(ret);
        long r = 0L;
        for(int i : ret) {
            r = r * 10 + i;
        }
        if(r > Integer.MAX_VALUE)   return 0;
        return (int)r;
    }
}
