/*

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.

*/
public class Solution {
    int [] table;
    void cal(int n) {
        if( n < 3)    n = 3;
        table = new int[n];
        table[0] = 1;
        table[1] = 3;
        table[2] = 4;
        for(int i= 3; i< n; ++i) {
            int j = i - 2;
            while(j  >= 0) {
                table[i] += 4 * table[j];
                j -= 2;
            }
        }
        table[0] = 0;
    }
    public int strobogrammaticInRange(String low, String high) {
        if(low.length() > high.length() || (low.length() == high.length() && low.compareTo(high) > 0) ) {
            return 0;
        }
        int n = high.length();
        if(n > low.length() + 1) {
            cal(n);    
        } 
        int sum = 0;
        if(low.length() == n) {
            List<String> nums = find(n);
            for(String num : nums) {
                if(low.compareTo(num) <= 0 && num.compareTo(high) <= 0) 
                    sum++;
            }
            return sum;
        }
        
        List<String> nums = find(n);
        for(String num : nums) {
            if(num.compareTo(high) <= 0) 
                sum++;
        }
        int m = low.length();
        //System.out.println(sum);
        nums = find(m);
        for(String num : nums) {
            if(low.compareTo(num) <= 0) 
                sum++;
        } 
        //System.out.println(sum);
        for(int i= m+1; i<n; ++i)
            sum += table[i];
        return sum;
    }
    
    int[][] add = new int [][] {{1, 1}, {6, 9}, {8, 8}, {9, 6}};
    
    public List<String> find(int n) {
        List<String> ret = new ArrayList<>();
        if(n <= 0)  {
            ret.add("");
            return ret;
        }
        if(n == 1) {
            ret.add("0");
            ret.add("1");
            ret.add("8");
        }
        for(int i= 0; n - 2 - 2*i >= 0; ++i) {
            List<String> base = find( n - 2 - 2 * i);
            for(String core: base) {
                for(int j=0; j< add.length; ++j) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(add[j][0]);
                    for(int k=0; k< i; ++k)
                        sb.append(0);
                    sb.append(core);
                    for(int k=0; k< i; ++k)
                        sb.append(0);
                    sb.append(add[j][1]);
                    ret.add(sb.toString());
                }
            }
        }
        Collections.sort(ret);
        return ret;
    }
}
