/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    Set<Integer> table = new HashSet<>(); 
    public boolean checkEqualTree(TreeNode root) {
        if(root == null)    return false;
        if(root.left == null && root.right == null) return false;
        int total = weigh(root);
        if((total  / 2) *2 != total)  return false;
        return table.contains(total / 2);
    }
    int weigh(TreeNode root) {
        if(root == null)    return 0;
        int ret =  weigh(root.left) + root.val + weigh(root.right);
        table.add(ret);
        //System.out.println(ret + " : " + root.val);
        return ret;
    }
}
