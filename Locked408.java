/*
Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:
Given s = "internationalization", abbr = "i12iz4n":

Return true.
Example 2:
Given s = "apple", abbr = "a2e":

Return false.
*/
public class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int k = 0;
        int count = 0;
        for(int i=0; i<abbr.length(); ++i) {
            if(k>= word.length())   return false;
            if(abbr.charAt(i) >= '0' && abbr.charAt(i) <= '9') {
                if(count == 0 && abbr.charAt(i) =='0')  return false;
                count = count * 10 + abbr.charAt(i) - '0';
            }
            else if(Character.isLetter(abbr.charAt(i))) {
                if(count > 0 ) {
                    k = k+ count;
                    count =0;
                }
                if(k>= word.length())
                        return false;
                if(abbr.charAt(i) != word.charAt(k++))    return false;
            }
        }
        
        if(count != word.length() - k)  return false; 
        return true;
    }
}
