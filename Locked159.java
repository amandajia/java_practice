/*
Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3.
*/
public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s == null)   return 0;
        int [] dict = new int[256];
        int count = 0;
        int ret = 0;
        int last = 0;
        for(int i = 0; i< s.length(); ++i) {
            int index = (int)s.charAt(i);
            if(dict[index] == 0)    count++;
            dict[index] ++;
            if(count<=2) {
                if(i - last + 1 > ret) 
                    ret = i - last + 1;
            } else {
                while(count>2) {
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
