/**
 * https://leetcode.com/problems/mini-parser/
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
    public NestedInteger deserialize(String s) {
        Stack<NestedInteger> st = new Stack();
        if(s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }
        int sign = 1;
        Integer number = null;
        NestedInteger ret = null;
        for(int i =0; i< s.length(); ++i ) {
            if(s.charAt(i)=='-' ) {
                sign *= -1;
                continue;
            } 
            if(Character.isDigit(s.charAt(i))) {
                int last =  (int)(s.charAt(i) - '0') ; 
                number = (number == null)? last: number* 10 + last;
                continue;
            }
            if(s.charAt(i)==',') {
                if(number == null)
                    continue;
                List<NestedInteger> list = st.peek().getList();
                NestedInteger ni = new NestedInteger(sign * number);
                if(list == null) {
                    st.peek().add(ni);
                } else {
                    list.add(ni);
                }
                number = null;
                sign = 1;
                continue;
            }
            if(s.charAt(i) =='['){
                NestedInteger n = new NestedInteger();
                if(st.size() >0)
                    st.peek().add(n);
                st.push(n);
                continue;
            }
            
            if(number!=null) {
                List<NestedInteger> list = st.peek().getList();
                if(list == null) 
                    st.peek().setInteger(sign * number);
                else 
                    list.add(new NestedInteger(sign * number));
                number = null;
                sign = 1;
            }
            
            ret = st.pop();
            
        }
        return ret;
    }
}
