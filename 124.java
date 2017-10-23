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
    int maxVal = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxDownPath(root);
        return maxVal;
    }
    int maxDownPath(TreeNode root) {
        if(root == null) return Integer.MIN_VALUE;
        int l = Math.max(0, maxDownPath(root.left));
        int r = Math.max(0, maxDownPath(root.right));
        maxVal = Math.max(maxVal, l + root.val + r);
        return Math.max(l, r) + root.val;
    }
}
