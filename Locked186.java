/*

Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?

Related problem: Rotate Array

*/
public class Solution {
       void reverse(char []s, int start, int end) {
        end --;
    	while( start < end) {
            char t = s[start];
            s[start] = s[end];
            s[end] = t;
            start ++;
            end --;
        }
    }
    
    public void reverseWords(char[] s) {
        reverse(s, 0, s.length);
        int start = 0;
        for(int i = 0; i < s.length; ++i) {
        	if (s[i]== ' ') {
        		reverse(s, start, i);
        		start = i + 1;
        	} 
        }
        reverse(s, start, s.length);
    }
}
