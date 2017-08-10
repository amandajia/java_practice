/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:
pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
Notes:
You may assume both pattern and str contains only lowercase letters.
*/

public class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        String[] table = new String[26];
        return match(str, 0, pattern, 0, table, new HashSet<String> ());
    }
    
    boolean match(String str, int i, String pattern, int j, String[] table, Set<String> used) {
        if(i == str.length() && j == pattern.length())  {
            /*
            for(int k = 0; k < 26; ++k) {
                if(table[k] == null)    continue;
                char t = (char)(k + 'a');
                System.out.println(t + " : "  + table[k]);
            }
            */
            return true;
        }
        if(i >= str.length() || j >= pattern.length())  return false; 
        int cur = pattern.charAt(j) - 'a';
        if(table[cur]!= null) {
            if(str.startsWith(table[cur], i)) {
                return match(str, i + table[cur].length(), pattern, j + 1, table, used);
            }
            return false;
        }
        int count = 1;
        int other = 0;
        for(int k = j + 1 ; k < pattern.length(); ++k) {
            if(pattern.charAt(k) == pattern.charAt(j)) {
                count ++;
                continue;
            } 
            int index = pattern.charAt(k) - 'a';
            if(table[index]!= null) {
                other += table[index].length();
            } else other++;
        }
        int maxBit = (str.length() - i - other)/count;
        for(int wc = 1; i+ wc <= str.length() && wc<=maxBit; ++wc) {
            //System.out.println(str + " I " + i + " : " + wc);
            String c = str.substring(i, i + wc);
            if(used.contains(c))    continue;
            table[cur] = c;
            used.add(c);
            if(match(str, i + c.length(), pattern, j + 1, table, used)) {
                return true;
            }
            table[cur] = null;
            used.remove(c);
        }
        return false;
    }
}
