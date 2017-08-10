/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.


*/
public class Solution {
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> table = new HashMap<>();
        table.put('1', '1');
        table.put('0', '0');
        table.put('8', '8');
        table.put('9', '6');
        table.put('6', '9');
        for(int i=0; i<=num.length()/2; ++i) {
            if(!table.containsKey(num.charAt(i)) ||table.get(num.charAt(i)) != num.charAt(num.length() - i - 1))
                return false;
        }
        return true;
    }
}
