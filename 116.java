/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return ;
        root.next = null;
        TreeLinkNode parent = root;
        while(parent != null) {
            TreeLinkNode ite = parent;
            TreeLinkNode first= null, last =null;
            while(ite!= null) {
                if(ite.left != null) {  
                    if( first == null) {
                        first = ite.left;
                    } else {
                        last.next = ite.left;
                    }
                    last = ite.left;
                }
                if(ite.right!= null) {
                    if( first == null) {
                        first = ite.right;
                    } else {
                        last.next = ite.right;
                    }
                    last = ite.right;
                }
                ite = ite.next;
            }
            parent = first;
        } 
    }
}
