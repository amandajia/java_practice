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
    public void flatten(TreeNode root) {
        if(root== null) return;
        if(root.left == null && root.right == null) return;
        flatten(root.left);
        flatten(root.right);
        TreeNode r = root.right;
        TreeNode p = root.left;
        if(root.left == null )
            return;
        while(p.right!= null) {
            p = p.right;
        }
        p.right = r;
        root.right = root.left;
        root.left = null;
    }
}
