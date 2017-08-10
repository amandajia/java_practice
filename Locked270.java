/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
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
    
    public int closestValue(TreeNode root, double target) {
        if(root == null) return 0;
        double dis = Double.MAX_VALUE;
        int ret = root.val;
        TreeNode p = root;
        while(p != null) {
            if(p.val == target) return p.val;
            root = p;
            if(p.val > target) {
                p = p.left;
            } else {
                p = p.right;
            }
            double newDis = Math.abs(root.val - target);
            if(newDis < dis) {
                dis = newDis;
                ret = root.val;
            }
        }
        return ret;
    }
}
