/*

Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.

Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.

The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.

The right-most node is also defined by the same way with left and right exchanged.

Example 1
Input:
  1
   \
    2
   / \
  3   4

Ouput:
[1, 3, 4, 2]

Explanation:
The root doesn't have left subtree, so the root itself is left boundary.
The leaves are node 3 and 4.
The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
So order them in anti-clockwise without duplicates and we have [1,3,4,2].
Example 2
Input:
    ____1_____
   /          \
  2            3
 / \          / 
4   5        6   
   / \      / \
  7   8    9  10  
       
Ouput:
[1,2,4,7,8,9,10,6,3]

Explanation:
The left boundary are node 1,2,4. (4 is the left-most node according to definition)
The leaves are node 4,7,8,9,10.
The right boundary are node 1,3,6,10. (10 is the right-most node).
So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
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
    static int LEFT = 1;
    static int RIGHT = 2;
    static class Node {
        TreeNode t;
        String p;
        public Node(TreeNode tt, String pp) {
            t = tt; p = pp;
        }
    }
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if(root == null)    return ret;
        List<TreeNode> res = new ArrayList<>();
        List<TreeNode> bfs = new ArrayList<>();
        bfs.add(root);
        res.add(root);
        List<TreeNode> rhs = new ArrayList<>();
        Map<TreeNode, String> side = new HashMap<>();
        side.put(root, "");
        PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2)-> e1.p.compareTo(e2.p));
        boolean le = false;
        boolean re = false;
        while(!bfs.isEmpty()) {
            List<TreeNode> next = new ArrayList<>();
            for(int i = 0; i < bfs.size(); ++i) {
                TreeNode cur = bfs.get(i);
                String path = side.get(cur);
                if(cur != root && cur.left == null && cur.right == null) {
                    pq.add(new Node(cur, path));
                }
                if(cur.left!=null) {
                    next.add(cur.left);
                    side.put(cur.left, path + "l");
                }
                if(cur.right != null) {
                    next.add(cur.right);
                    side.put(cur.right, path + "r");
                }
            }
            TreeNode l = bfs.get(0);
            String lp = side.get(l);
            if(!le && lp.length() > 0 && lp.charAt(0) == 'l') {
                res.add(l);
                if(l.left == null && l.right == null) {
                    le = true;
                }
            }
            TreeNode r = bfs.get(bfs.size() -1);
            String rp = side.get(r);
            if(!re && rp.length() > 0 && rp.charAt(0) == 'r') {
                rhs.add(r);
                if(r.left == null && r.right == null) {
                    re = true;
                }
            }
            bfs = next;
        }
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(!res.contains(cur.t)) {
                res.add(cur.t);
            }
        }
        for(int i = rhs.size() - 1; i>=0; --i) {
            if(!res.contains(rhs.get(i))) {
                res.add(rhs.get(i));
            }
        }
        for(TreeNode n: res) {
            ret.add(n.val);
        }
        return ret;
    }
}
