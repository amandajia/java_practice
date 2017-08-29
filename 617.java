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
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null)  return t2;
        if(t2 == null)  return t1;
        t1.val = t1.val + t2.val;
        TreeNode l = mergeTrees(t1.left, t2.left);
        TreeNode r = mergeTrees(t1.right, t2.right);
        t1.left = l;
        t1.right = r;
        return t1;
    }
}
