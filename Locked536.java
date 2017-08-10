/*
You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Example:
Input: "4(2(3)(1))(6(5))"
Output: return the tree root node representing the following tree:

       4
     /   \
    2     6
   / \   / 
  3   1 5   
Note:
There will only be '(', ')', '-' and '0' ~ '9' in the input string.
An empty tree is represented by "" instead of "()".

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    String parse(String s) {
        if(s == null || s.length() ==0) return s;
        int index = 0;
        int count = 0;
        for(;index < s.length(); ++index) {
            if(s.charAt(index) == ')'){
                if(count == 1)  break;
                count--;
            }
            if(s.charAt(index) == '(')
                count++;
        }
        return s.substring(1, index);
    }
    public TreeNode str2tree(String s) {
        if(s == null || s.length() == 0)    return null;
        int i = 0;
        int sign = s.charAt(0)=='-'? -1: 1;
        int val = 0;
        for(; i < s.length(); ++i){
            if(s.charAt(i) == '-')  continue;
            if(s.charAt(i) == '(')  break;
            val = val * 10 + s.charAt(i) - '0';
        }
        TreeNode root = new TreeNode(sign * val);
        String left = parse(s.substring(i));
        String right = null;
        if(i + left.length() + 2< s.length())   right = parse(s.substring(i + left.length() + 2));
        TreeNode l = str2tree(left);
        TreeNode r = str2tree(right);
        root.left = l;
        root.right = r;
        //System.out.println(s + " : " + root.val + " : " );
        return root;
    }
}
