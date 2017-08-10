/*
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ
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
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null)    return null;
        TreeNode p = root;
        TreeNode left = p.left;
        TreeNode right = p.right;
        p.left = null;
        p.right = null;
        while(left != null) {
            TreeNode ll = left.left;
            TreeNode lr = left.right;
            left.left = right;
            left.right = p;
            p = left;
            left = ll;
            right = lr;
        }
        return p;
    }
}
