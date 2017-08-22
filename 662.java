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
    static class Node {
        TreeNode n;
        int index;
        public Node(int i, TreeNode t) {
            index = i;
            n = t;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        List<Node> line = new ArrayList<>();
        if(root == null)    return 0;
        line.add(new Node(1, root));
        int ret = 1;
        while(!line.isEmpty()) {
            List<Node> next = new ArrayList<>();
            for(Node t : line) {
                TreeNode cur = t.n;
                int index = t.index * 2;
                if(cur.left!= null) {
                    next.add(new Node(index, cur.left));
                } 
                if(cur.right != null) {
                    next.add(new Node(index + 1, cur.right));
                }
            }
            if(next.size() > 1) {
                ret = Math.max(ret, next.get(next.size() - 1).index - next.get(0).index + 1);
            }
            line = next;
        }
        return ret;
    }
}
