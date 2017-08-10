/*
Given a string, find the length of the longest substring T that contains at most k distinct characters.

For example, Given s = “eceba” and k = 2,

T is "ece" which its length is 3.
*/

public class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(s == null || k == 0)   return 0;
        int [] dict = new int[256];
        int count = 0;
        int ret = 0;
        int last = 0;
        for(int i = 0; i< s.length(); ++i) {
            int index = (int)s.charAt(i);
            if(dict[index] == 0)    count++;
            dict[index] ++;
            if(count<=k) {
                if(i - last + 1 > ret) 
                    ret = i - last + 1;
            } else {
                while(count>k) {
                    int l = (int)s.charAt(last);
                    dict[l] --;
                    last ++;
                    if(dict[l] == 0)
                        count--;
                }
            }
        }
        return ret;
    }
}
