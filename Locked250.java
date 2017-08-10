/*
Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,
              5
             / \
            1   5
           / \   \
          5   5   5
return 4.
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
    int count = 0;
    boolean cal(TreeNode root) {
        if(root == null)    return true;
        if(root.left == null && root.right == null) {
            count++;
            return true;
        }
        boolean left = cal(root.left);
        boolean right =cal(root.right);
        if(!left || !right) return false;
        if((root.left!=null && root.left.val != root.val) || (root.right!=null && root.right.val != root.val))  return false;
        count++;
        return true;
    }
    
    public int countUnivalSubtrees(TreeNode root) {
        cal(root);
        return count;
    }
}
