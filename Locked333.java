/*
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.
Here's an example:
    10
    / \
   5  15
  / \   \ 
 1   8   7
The Largest BST Subtree in this case is the highlighted one. 
The return value is the subtree's size, which is 3.
Follow up:
Can you figure out ways to solve it with O(n) time complexity?
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
    public class Tree {
        public int big;
        public int small;
        public int size;
        public Tree(int b, int s, int si) {
            big = b;
            small = s;
            size = si;
        }
    }
    
    int ret = 0;
    
    Tree cal(TreeNode root) {
        if(root == null)    return null;
        if(root.left == null && root.right == null) {
            if(ret == 0)    ret = 1;
            return new Tree(root.val, root.val, 1);
        }
        Tree l = cal(root.left);
        Tree r = cal(root.right);
        if( (l!= null && l.size < 0 ) || (r != null && r.size < 0)) return new Tree(0, 0, -1);
        int small = (l == null)? root.val: l.small;
        int big = (r == null)?root.val: r.big;
        int size = 1 + ((l == null)? 0 : l.size) + ((r == null)? 0 : r.size );
        if( (l!= null && l.big >= root.val) || (r!= null && r.small <= root.val) ) {
            return new Tree(big, small, -1);
        }
        if(size > ret)  ret = size;
        return new Tree(big, small, size);
        
    }
    
    public int largestBSTSubtree(TreeNode root) {
        cal(root);
        return ret;
    }
}
