public class Solution {
    String convert(String p) {
        StringBuilder sb = new StringBuilder();
        char last = '*';
        for(int i = 0; i< p.length(); ++i) {
            char cur = p.charAt(i);
            if(cur == '*') {
                if(last == '.') {
                    sb.append('/');
                } else {
                    sb.append((char) (last - 'a' + 'A'));
                }
            } else {
                if(last != '*')
                    sb.append(last);
            }
            last = cur;
        }
        if(last != '*') {
            sb.append(last);
        }
        return sb.toString();
    }
    public boolean isMatch(String s, String p) {
        if(s == null || p == null)  return false;
        if(p.length() == 0) return s.length() == 0;
        String np = convert(p);
        //System.out.println(np);
        if(s.length() == 0) {
            for(int i = 0; i< np.length(); ++i) {
                if(np.charAt(i) <= 'z' && np.charAt(i) >= 'a' || np.charAt(i) == '.' ) {
                    return false;
                }
            }
            return true;
        }
        boolean [][] dp = new boolean [s.length()][np.length()];
        boolean taken = false;
        for(int i = 0; i< np.length(); ++i) {
            if(np.charAt(i) <= 'z' && np.charAt(i) >= 'a' || np.charAt(i) == '.' ) {
                if(taken)   break;
                if(np.charAt(i) == s.charAt(0) || np.charAt(i) == '.')
                    dp[0][i] = true;
                taken = true;
            } else if(np.charAt(i) == '/' ||  np.charAt(i) == s.charAt(0) - 'a' + 'A') {
                if(!taken)
                    dp[0][i] = true;
                else {
                    dp[0][i] = dp[0][i - 1];
                }
            } else if(i > 0){ // capital other letter, can be ignored
                dp[0][i] = dp[0][i - 1];
            }
        }
        for(int i = 1; i< s.length(); ++i) {
            if(!dp[i - 1][0])   break;
            char first = np.charAt(0);
            if(first == '/'|| s.charAt(i) - 'a' + 'A'== first ) {
                dp[i][0] = true;
            }
        }
       
        
        for(int j = 1; j< np.length(); ++j) {
            for(int i = 1; i < s.length(); ++i) {
                char cur = s.charAt(i);
                char mask = np.charAt(j);
                if(mask == cur || mask == '.') {
                    dp[i][j] = dp[i - 1][ j - 1];
                } else if(mask == '/' || mask == cur - 'a' + 'A') {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-1] || dp[i][j-1];
                } else if(mask >='A' && mask <='Z') {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
/*
        for(int i = 0; i< dp.length; ++i) {
            for(int j = 0; j < dp[0].length; ++j)
                System.out.print(dp[i][j] + " ");
            System.out.println();
        }
*/
        return dp[s.length() - 1][np.length() - 1];
    }
}
