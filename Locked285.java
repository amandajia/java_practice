/*
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.
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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null || p == null)   return null;
        Stack<TreeNode> st = new Stack();
        TreeNode i = root;
        boolean found = false;
        while(!st.isEmpty() || i != null) {
            if(i != null) {
                while(i.left != null)   {
                    st.push(i);   
                    i = i.left;
                }
                if(found)   return i;
                if(i == p)  found = true;
                i = i.right;
            } else {
                if(found) return st.pop();
                TreeNode cur =  st.pop();
                if(cur == p)    found = true;
                i = cur.right;
            }
            
        }
        return null;
    }
}
