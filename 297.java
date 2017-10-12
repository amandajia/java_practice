/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if(root == null)    return sb.toString();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int count = 1;
        while(!q.isEmpty() && count > 0) {
            TreeNode cur = q.poll();
            if(cur == null)      {
                if(sb.length() > 0) {
                    sb.append(',');
                }
                sb.append('n');
            }
            else {
                count --;
                if(sb.length() > 0) {
                    sb.append(',');
                }
                sb.append(cur.val);
                q.add(cur.left);
                q.add(cur.right);
                if(cur.left!= null)     count++;
                if(cur.right != null)   count++;
            }
        }
        return sb.toString();
    }
    
    public class Token {
        String data;
        int index = 0;
        public Token(String t){
            data = t;
        }
        public Boolean hasNext() {
            return index < data.length();
        }
        
        public Integer getNext() {
            int sum = 0;
            int sign = 1;
            if(data.charAt(index) == ',')
                index++;
            if(data.charAt(index) == 'n') {
                index++;
                return null;
            }
            while(index < data.length()) {
                char cur = data.charAt(index);
                if(cur == ',')   return sum * sign;
                if(cur == '-' || cur == '+') {
                    index++;
                    if(cur == '-')  sign = -1;
                }
                sum = sum * 10 + data.charAt(index++) - '0';
            }
            return sum* sign;
        }
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.equals("")) return null;
        Queue<TreeNode> parent = new LinkedList<>();
        Queue<TreeNode> child = new LinkedList<>();
        Token t = new Token(data);
        TreeNode head = new TreeNode(t.getNext());
        parent.add(head);
        while(!parent.isEmpty() || !child.isEmpty()) {
            if(parent.isEmpty()) {
                Queue<TreeNode> temp = child;
                child = parent;
                parent = temp;
            }
            TreeNode cur = parent.poll();
            if(!t.hasNext()) return head;
            Integer left = t.getNext();
            if(left != null)    {
                cur.left = new TreeNode(left);
                child.add(cur.left);
            }
            if(!t.hasNext()) return head;
            Integer right = t.getNext();
            if(right != null)    {
                cur.right = new TreeNode(right);
                child.add(cur.right);
            }
        }
        return head;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
