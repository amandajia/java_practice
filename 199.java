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
    private int qp(Queue<TreeNode> o, Queue<TreeNode> t) {
        TreeNode cur =null;
        while(!o.isEmpty()) {
            cur = o.poll();
            if (cur.left != null) t.add(cur.left);
            if (cur.right != null) t.add(cur.right);
            
        }
        
        return cur.val;
    }
    
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;
        Queue<TreeNode> lq =new LinkedList<> ();
        Queue<TreeNode> rq =new LinkedList<> ();
        lq.add(root);
        while(!lq.isEmpty() || !rq.isEmpty()) {
            int show ;
            if (!lq.isEmpty()) show = qp(lq, rq);
            else show = qp(rq, lq);
            ret.add(show);
        }
        return ret;
        
    }
}
