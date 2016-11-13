// https://leetcode.com/problems/delete-node-in-a-bst/
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
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null)    return null;
        TreeNode parent = null;
        TreeNode p = root;
        while(p!= null && p.val!= key) {
            parent = p;
            p = (p.val > key)? p.left : p.right;
        }
        if( p == null)
            return root;
        if( p == root && p.right == null) {
            return p.left;
        }
        // find element just large than this one
        if(p.left == null && p.right == null) {
            if(parent.val > p.val)
                parent.left= null;
            else
                parent.right = null;
            return root;
        }
        TreeNode toDelete = p;
        if( p.right != null) {
            parent = p;
            p = p .right;
            while(p.left!= null) {
                parent = p;
                p = p.left;
            }
            // delete p;
            toDelete.val = p.val;
            if(parent.val > p.val) {
                parent.left = deleteNode(p, p.val);
            } else { 
                parent.right = deleteNode(p, p.val);
            }
        } else {
            p.val = p.left.val;
            p.right = p.left.right;
            p.left = p.left.left;
        }
        return root;
    }
}
