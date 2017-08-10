/*
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples:

Given binary tree [3,9,20,null,null,15,7],
   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7
return its vertical order traversal as:
[
  [9],
  [3,15],
  [20],
  [7]
]
Given binary tree [3,9,8,4,0,1,7],
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
return its vertical order traversal as:
[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2
return its vertical order traversal as:
[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]

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
    List<List<Integer>> qian = new ArrayList<>();
    List<List<Integer>> hou  = new ArrayList<>();
    int getIndex(int p) {
        if(p < 0) {
            p = -1 * p -1; 
        } 
        return p;
    }
    
    void cal(Queue<TreeNode> l, Queue<TreeNode> r,  Map<TreeNode, Integer> table) {
        while(!l.isEmpty()) {
            TreeNode cur = l.poll();
            int p = table.get(cur);
            if(cur.left!= null) {
                table.put(cur.left, p - 1);
                r.add(cur.left);
            }
            if(cur.right!= null) {
                table.put(cur.right, p + 1);
                r.add(cur.right);
            }
            List<List<Integer>> ar = (p< 0)? qian: hou;
            p = getIndex(p);
            if(ar.size() <= p) {
                ar.add(new ArrayList<Integer>());
            }
            ar.get(p).add(cur.val);
        }
    }
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null)    return qian;
        Queue<TreeNode> left = new LinkedList<>();
        Queue<TreeNode> right= new LinkedList<>();
        Map<TreeNode, Integer> table = new HashMap<>();
        table.put(root, 0);
        left.add(root);
        while(!left.isEmpty() || !right.isEmpty()) {
            if(!left.isEmpty()) {
                cal(left, right, table);
            } else 
                cal(right, left, table);
        }
       // System.out.println(qian);
       // System.out.println(hou);
        Collections.reverse(qian);
        qian.addAll(hou);
        return qian;
    }
}
