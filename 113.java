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
    void cal(TreeNode root, int sum, List<Integer> parent, List<List<Integer>> ret) {
        if(root.left == null && root.right ==null) {
            if(root.val == sum) {
                parent.add(sum);
                ret.add(parent);
            }
            return ;
        }
        sum = sum - root.val;
        parent.add(root.val);
        if(root.left!=null && root.right!= null) {
            List<Integer> np = new ArrayList<>(parent); 
            cal(root.right, sum, parent, ret);
            cal(root.left, sum, np, ret);
            return ;
        }

        if(root.left!= null) {
            cal(root.left, sum, parent, ret);         
        } else {
            cal(root.right, sum, parent, ret);
        }

    }
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> parent = new ArrayList<>();
        if(root == null)    return ret;
        cal(root, sum, parent, ret);
        return ret;
    }
}
