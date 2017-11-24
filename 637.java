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
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ret = new ArrayList<>();
        if(root == null)    return ret;
        Queue<TreeNode> l = new LinkedList<>();
        l.add(root);
        while(!l.isEmpty()) {
            int count = 0;
            double avg = 0.0;
            Queue<TreeNode> r = new LinkedList<>();
            while(!l.isEmpty()) {
                TreeNode cur = l.poll();
                count++;
                avg += cur.val;
                if(cur.left!=null) {
                    r.add(cur.left);
                }
                if(cur.right != null) {
                    r.add(cur.right);
                }               
            }
            l = r;
            //System.out.println(avg + " " + count);
            ret.add(avg / count);
        }
        return ret;
    }
}
