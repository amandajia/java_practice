public class Solution {
    public int numDistinct(String s, String t) {
        if(s == null || s.length() ==0 || t == null || t.length() == 0) return 0;
        int [][] count = new int[s.length()][t.length()];
        for(int i = 0; i<s.length(); ++i) {
            if(i > 0) {
                count[i][0] = count[i - 1][0];
            }
            if(s.charAt(i) == t.charAt(0)) {
                count[i][0] ++;
            }
        }
        for(int j = 1; j< t.length(); ++j) {
            for(int i = j; i < s.length(); ++i) {
                count[i][j] = count[i - 1][j];    
                if(s.charAt(i) == t.charAt(j)) {
                    count[i][j] += count[i - 1][j - 1];    
                }
            }
        }
        return count[s.length() - 1][t.length() - 1];
    }
}
