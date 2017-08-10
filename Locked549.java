/*
Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:
Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
Example 2:
Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
Note: All the values of tree nodes are in the range of [-1e7, 1e7].
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

    int ret = 0;
    
    public int longestConsecutive(TreeNode root) {
        if(root == null)    return 0;
        longest(root);
        return ret;
    }
    
    int [] longest(TreeNode root) {
        if(root == null)    return new int[]{0, 0};
        int [] cret = new int[]{1, 1};
        int [] l = longest(root.left);
        int [] r = longest(root.right);
        if(root.left != null) {
            if(root.left.val == root.val -1 ) {
                cret[0] = l[0] + 1;
            }
            if(root.left.val == root.val + 1 ) {
                cret[1] = l[1] + 1;
            }
        }
        if(root.right != null) {
            if(root.right.val == root.val -1 ) {
                cret[0] = Math.max(cret[0], r[0] + 1);
            }
            if(root.right.val == root.val + 1 ) {
                cret[1] = Math.max(cret[1], r[1] + 1);
            }
        }
        ret = Math.max(ret, cret[0]+ cret[1] - 1);
        return cret;
    }
}
