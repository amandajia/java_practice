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
        if(root == null)    return;
        root.next = null;
        TreeLinkNode p = root;
        while(p!=null) {
            TreeLinkNode nh = null;
            TreeLinkNode np = null;
            while(p!=null) {
                if(p.left !=null) {
                    if(nh == null) {
                        nh = p.left;
                        np = nh;
                    } else {
                        np.next = p.left;
                        np = np.next;
                    }
                } 
                if(p.right != null) {
                    if(nh == null) {
                        nh = p.right;
                        np = nh;
                    } else {
                        np.next = p.right;
                        np = np.next;
                    }
                } 
                p = p.next;
            }
            p = nh;
            
        }
        
    }
}
