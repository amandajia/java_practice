public class Solution {
    public boolean isValid(String s) {
        if(s == null ) return true;
        Stack<Character> st = new Stack();
        for(int i = 0; i< s.length(); i++) {
            char cur = s.charAt(i);
            if(cur == '(')          st.push(')');
            else if(cur == '[')     st.push(']');
            else if(cur == '{')     st.push('}');
            else if(cur == ')' || cur == '}' || cur == ']') {
                if(st.isEmpty() || cur != st.pop()) return false;
            } else return false;
        }
        return st.isEmpty();
    }
}
