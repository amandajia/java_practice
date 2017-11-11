public class Solution {
    
    public int longestValidParentheses(String s) {
        int ret = 0;
        Stack<Integer> st = new Stack();
        int [] dp = new int[s.length()];
        for(int i = 0; i< s.length(); ++i) {
            dp[i] = -1;
            if(s.charAt(i) != '(' && s.charAt(i) != ')') {
                st.clear();
                continue;
            }
            //System.out.println(s.charAt(i));
            if(s.charAt(i) == '('){
                st.push(i);
            }
            else {
                if(!st.isEmpty()) {
                    int curIndex = st.pop();
                    dp[i] = curIndex;
                } 
            }
        }
        for(int i = s.length() - 1; i >= 0; i--) {
            if(dp[i] == -1) continue;
            int l = i - dp[i] + 1;
            int lb = dp[i] - 1;
            while(lb>= 0 && dp[lb] != -1) {
                l += lb - dp[lb] + 1;
                lb = dp[lb] - 1;
                i = lb;
                }
            if(l > ret) ret = l;
        }
        return ret;
    }
}
