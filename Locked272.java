/*
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
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
    Stack<TreeNode> up = new Stack();
    Stack<TreeNode> down = new Stack();
    
    void init(TreeNode root, double target) {
        TreeNode p = root;
        int last = 0;
        while(p != null) {
            last = p.val;
            up.push(p);
            if(p.val == target)  {
                down = (Stack<TreeNode>)up.clone();
                getPrecessor();
                return;
            }
            root = p;
            if(p.val > target) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if(last > target) {
            down = (Stack<TreeNode>)up.clone();
            getPrecessor();
        } else {
            down = (Stack<TreeNode>)up.clone();
            getSuccessor();
        }
        
    }
    
    Integer getPrecessor() {
        if(down.isEmpty())  return null;
        TreeNode t = down.peek();
        int ret = t.val;
        if(t.left != null) {
            t = t.left;
            while(t != null) {
                down.push(t);
                t = t.right;
            }
        } else {
            t = down.pop();
            TreeNode p ;
            while(!down.isEmpty()) {
                p = down.peek();
                if(p.val <= t.val) 
                    break;
                t = down.pop();
            }
        }
        
        return ret;
    }
    
    Integer getSuccessor() {
        if(up.isEmpty())  return null;
        TreeNode t = up.peek();
        int ret = t.val;
        if(t.right != null) {
            t = t.right;
            while(t != null) {
                up.push(t);
                t = t.left;
            }
        } else {
            t = up.pop();
            TreeNode p ;
            while(!up.isEmpty()) {
                p = up.peek();
                if(p.val >= t.val) 
                    break;
                t = up.pop();
            }
        }
        
        return ret;
    }
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> ret= new ArrayList<>();
        if(root == null || k == 0)  return ret;
        init(root, target);
        Integer upi = getSuccessor();
        Integer downi = getPrecessor();
        //System.out.println(upi + " : " + downi);
        for(int i=0; i<k; ++i) {
            if(upi == null) {
                ret.add(downi);
                downi = getPrecessor();
            } else if(downi == null) {
                ret.add(upi);
                upi = getSuccessor();
            } else {
                double con = Math.abs(target - upi) - Math.abs(target - downi);
                if(con < 0) {
                    ret.add(upi);
                    upi = getSuccessor();
                } else {
                    ret.add(downi);
                    downi = getPrecessor();
                }
            }
        }
        return ret;
    }
}
