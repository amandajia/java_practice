/*
Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression. You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F (T and F represent True and False respectively).

Note:

The length of the given string is ≤ 10000.
Each number will contain only one digit.
The conditional expressions group right-to-left (as usual in most languages).
The condition will always be either T or F. That is, the condition will never be a digit.
The result of the expression will always evaluate to either a digit 0-9, T or F.
Example 1:

Input: "T?2:3"

Output: "2"

Explanation: If true, then result is 2; otherwise result is 3.
Example 2:

Input: "F?1:T?4:5"

Output: "4"

Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
          -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
          -> "4"                                    -> "4"
Example 3:

Input: "T?T?F:5:3"

Output: "F"

Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
          -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
          -> "F"                                    -> "F"

*/
public class Solution {
    public String parseTernary(String expression) {
        if(expression == null || expression.length() == 0)  return "";
        for(int i =0; i< expression.length(); ) {
            char cur = expression.charAt(i);
            if(i == expression.length() - 1)
                return Character.toString(cur);
            if(expression.charAt(i + 1) == '?') {
                 i = i+2;
                 if(cur == 'F') {
                    int depth = 0;
                    while(i < expression.length() && depth >= 0 ) {
                        if(expression.charAt(i) == '?')
                            depth++;
                        if(expression.charAt(i) == ':')
                            depth--;
                        i++;
                    }
                }
            } else 
                return Character.toString(cur);
        }
        return "";
    }
}
