/*
Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.
*/
public class Solution {
    public boolean canPermutePalindrome(String s) {
        int [] table = new int[256];
        for(int i = 0; i<s.length(); ++i) {
            table[(int)(s.charAt(i))] ++;
        }
        int count = 0;
        for(int i =0; i< 256; ++i) {
            if(table[i] % 2 ==1)    
                count++;
        }
        return count<=1;
    }
}
