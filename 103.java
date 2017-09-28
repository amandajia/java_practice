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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if(root == null)    return ret;
        Stack<TreeNode> l = new Stack();
        Stack<TreeNode> r = new Stack();
        l.push(root);
        while(!l.isEmpty() || !r.isEmpty()) {
            List<Integer> line = new ArrayList<>();
            if(!l.isEmpty()) {
                while(!l.isEmpty()) {
                    TreeNode cur = l.pop();
                    line.add(cur.val);
                    if (cur.left != null) r.push(cur.left);
                    if (cur.right!= null) r.push(cur.right);
                }    
            } else {
                while(!r.isEmpty()) {
                    TreeNode cur = r.pop();
                    line.add(cur.val);
                    if (cur.right!= null) l.push(cur.right);
                    if (cur.left != null) l.push(cur.left);
                }
            }
            ret.add(line);
        }
        
        return ret;
    }
}
