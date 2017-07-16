public class Solution {
    
    public int numDecodings(String s) {
        if(s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
        int [][] nums = new int [s.length() + 1][10];
        int mod = 1000000007;
        nums[0][0] = 1;
        if(s.charAt(0) == '*') {
            nums[1][0] = 9;
            for(int i = 1; i<10; ++i) {
                nums[1][i] = 1;
            }
        }
        else {nums[1][0] = 1; }
            
        for(int i = 0; i< s.length() - 1; ++i) {
            int p = s.charAt(i);
            int c = s.charAt(i + 1);
            if(p == '*') {
                if(c == '*') {
                    int total = 0;
                    for(int ci= 1; ci< 10; ++ci) {
                        for(int pi = 1; pi < 10; ++pi) {
                            nums[i+2][ci] = (nums[i+1][pi] + nums[i+2][ci])% mod;
                            if(pi * 10 + ci <= 26) {
                                nums[i+2][ci] = (nums[i][0] + nums[i+2][ci]) % mod;    
                            }
                        }
                        total = (total + nums[i+2][ci]) % mod;
                    }
                    nums[i+2][0] = total;
                    
                } else {
                    c = c -'0';
                    if(c == 0) {
                        nums[i+ 2][0] = (nums[i][0] + nums[i][0]) % mod;
                        continue;
                    }
                    int sum = 0;
                    for(int k = 1; k < 10; ++k) {
                        sum = (sum + nums[i + 1][k]) % mod;
                        if(k * 10 + c <= 26) {
                            sum = ( sum + nums[i][0]) % mod;    
                        }
                    } 
                    nums[i+2][0] = sum; 
                }
            } else if(c == '*') {
                int sum = 0;
                p = p - '0'; 
                for(int k = 1; k< 10; ++k) {
                    if (p == 0) {
                        nums[i + 2][k] = nums[i + 1][0]; 
                    } else {
                        int cur = (p) * 10 + k;
                        nums[i + 2][k] = nums[i + 1][0];
                        if(cur <= 26) {
                            nums[i + 2][k] = ( nums[i+2][k] + nums[i][0])% mod;
                        }
                    }
                    sum = ( sum + nums[i+2][k]) % mod;
                }
                nums[i+2][0] = sum;
            } else {
                c = c - '0';
                p = p - '0';
                if(c == 0) {
                    if(p > 2  || p == 0)   return 0;
                    nums[i + 2][0] = nums[i][0];
                } else if (p == 0) {
                    nums[i + 2][0] = nums[i + 1][0]; 
                } else {
                    int cur = (p) * 10 + c - 0;
                    nums[i + 2][0] = nums[i + 1][0];
                    if(cur <= 26) {
                        nums[i + 2][0] = (nums[i+2][0] + nums[i][0]) % mod;
                    }
                }
            }
            
        }
        return nums[s.length()][0];
    }
}
