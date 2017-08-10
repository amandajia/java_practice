/*

You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.


*/
public class Solution {
    Map<String, Boolean> table = new HashMap<>();
    public boolean canWin(String st) {
        if(table.containsKey(st))
            return table.get(st);
        boolean ret = false;
        char [] s = st.toCharArray();
        for(int i = 0; i<s.length - 1; ++i) {
            if(s[i] == '+' && s[i+1] == '+') {
                s[i] = '-';
                s[i + 1] = '-';
                if(!canWin(new String(s))) {
                    ret = true;
                    break;
                }
                s[i] = '+';
                s[i+1] = '+';
            }
        }
        table.put(st, ret);
        return ret;
    }
}
