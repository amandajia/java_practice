/*
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
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
    int f = 0;
    int cal(TreeNode root) {
        if(root == null)    return 0;
        int left = cal(root.left);
        int right = cal(root.right);
        int ret = 1;
        if(root.left!= null && root.val == root.left.val - 1) 
            ret = left + 1;
        if(root.right!= null && root.val == root.right.val - 1) 
            ret = Math.max(right + 1, ret);
        if(ret > f) f= ret;
        return ret;
    }
    
    public int longestConsecutive(TreeNode root) {
        cal(root);
        return f;
    }
}
