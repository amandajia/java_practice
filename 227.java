/* Base calculator II
 * https://leetcode.com/problems/basic-calculator-ii/
 *
 */
public class Solution {
    public int calculate(String s) {
        Deque<Integer> nums = new ArrayDeque();
        Deque<Character> ops = new ArrayDeque();
        Integer cur = null;
        for(int i=0; i< s.length() || cur != null; ++i) {
            Character c = i<s.length()? s.charAt(i): null;
            if(c!= null && Character.isDigit(c)) {
                cur = (cur == null)? c - '0' : cur * 10 + c - '0';
                continue;
            } 
            if(cur != null) {
                Character l = ops.isEmpty()? null :ops.peekLast();
                if(l == null || l == '+' || l == '-')
                    nums.add(cur);
                else {
                    ops.pollLast();
                    int pre = nums.pollLast();
                    if(l == '*')
                        nums.add(pre * cur);
                    else 
                        nums.add(pre / cur);
                } 
                cur = null;
            }
            if(c == null || c == ' ') {
                continue;
            } 
            ops.add(c);
        }
        int pre = nums.pollFirst();
        while(!ops.isEmpty()) {
            char o = ops.pollFirst();
            cur = nums.pollFirst();
            if(o == '-') 
                pre = pre - cur;
            else 
                pre = pre + cur;
        }
        return pre;
    }
}
