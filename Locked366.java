/*
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Example:
Given binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Returns [4, 5, 3], [2], [1].

Explanation:
1. Removing the leaves [4, 5, 3] would result in this tree:

          1
         / 
        2          
2. Now removing the leaf [2] would result in this tree:

          1          
3. Now removing the leaf [1] would result in the empty tree:

          []         
Returns [4, 5, 3], [2], [1].

Credits:
Special thanks to @elmirap for adding this problem and creating all test cases.


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
    Map<TreeNode, Integer> depth = new HashMap<>();
    int cal(TreeNode root) {
       if(root == null) return 0;
       int d = Math.max(cal(root.left), cal(root.right)) + 1;
       depth.put(root, d);
       return d;
    }
    void visit(List<List<Integer>> ret, TreeNode node) {
        int d = depth.get(node);
        ret.get(d - 1).add(node.val); 
    }
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if(root == null)    return ret;
        int d = cal(root);
        for(int i=0; i<d; ++i) {
            ret.add(new ArrayList<Integer>());
        }
        Stack<TreeNode> st = new Stack();
        TreeNode p = root;
        while(!st.isEmpty() || p!= null) {
            if(p!= null) {
                while(p.left != null) {
                    st.push(p);
                    p = p.left;
                }
                visit(ret, p);
                p = p.right;
            } else {
                TreeNode n = st.pop();
                visit(ret, n);
                p = n.right;
            }
        }
        return ret;
    }
}
