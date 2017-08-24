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
    TreeNode or;
    public boolean findTarget(TreeNode root, int k) {
        or = root;
        return pre(root, k);
    }
    boolean pre(TreeNode root, int k) {
        if(root == null )   return false;
        if(pre(root.left, k)) return true;
        if(find(or, k - root.val, root))    return true;
        if(pre(root.right, k))   return true;
        return false;
    }
    boolean find(TreeNode root, int k, TreeNode not) {
        if(root == null )   return false;
        if(root.val == k )  {
            if(root == not) return false;
            return true;
        } 
        if(root.val > k) {
            return find(root.left, k,not);
        }
        return find(root.right, k, not);
    }
}
