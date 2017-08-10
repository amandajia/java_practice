/*
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Example 2:
Given the following words in dictionary,

[
  "z",
  "x"
]
The correct order is: "zx".

Example 3:
Given the following words in dictionary,

[
  "z",
  "x",
  "z"
]
The order is invalid, so return "".

Note:
You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.


*/

public class Solution {
    static class Node {
        char c;
        List<Node> childs;
        int order;
        public Node(char cc) {
            c = cc;
            order = 0;
            childs = new ArrayList<>();
        }
    }
    Node [] table = new Node[26];
    
    boolean DFS() {
        Set<Node> head = new HashSet<>();
        Set<Node> not = new HashSet<>();
        int max =0;
        for(int i =0; i< 26 ; ++i) {
            if(table[i] == null)    continue;
            max++;
            if(table[i].childs.size() > 0) {
                head.add(table[i]);
                for(Node n : table[i].childs) {
                	not.add(n);
                }
            }
        }
        Queue<Node> to = new LinkedList<>();
        head.removeAll(not);
        to.addAll(head);
        if(not.size() > 0 && to.isEmpty() ) return false;
        while(!to.isEmpty()) {
            Node root = to.poll();
            if(root.order != 0) continue;
            Stack<Node> st = new Stack<Node>();
            st.push(root);
            while(!st.isEmpty()) {
                Node cur = st.peek();
                List<Node> ch = cur.childs;
                int it =0;
                for(; it< ch.size(); ++it) {
                    Node n = ch.get(it);
                    if(n.order == 0) {
                    	if(st.contains(n))	return false;
                        st.push(n);
                        break;
                    }
                }
                if(it != ch.size()) continue;
                cur.order = max --;
                st.pop();
            }
        }
        for(int i =0; i< 26 ; ++i) {
            if(table[i] == null)    continue;
            if(table[i].childs.size() > 0) {
                if(table[i].order == 0) return false;
            }
        }
        return true;
    }
    
    public String alienOrder(String[] words) {
        for(int i = 0; i < words.length; ++i) {
            for(int j = 0; j< words[i].length(); ++j) {
                int index = words[i].charAt(j) - 'a';
                if(table[index] == null)  {
                    table[index] = new Node((char)(index +'a'));
                }
            } 
        }
        for(int i = 0; i< words.length - 1; ++i) {
            String pre = words[i];
            String next = words[i + 1];
            int minL = Math.min(pre.length(), next.length());
            for(int k = 0; k < minL; ++k) {
                char p = pre.charAt(k); 
                char n = next.charAt(k);
				if (p == n)
					continue;
				List<Node> pch = table[p - 'a'].childs;
				Node nn = table[n - 'a'];
				if (!pch.contains(nn))
					pch.add(nn);
				break;
			}
        }
        if(!DFS()) return "";
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) ->(n1.order!=n2.order)? n1.order - n2.order: n1.c - n2.c);
        for(int i = 0; i< 26; ++i) {
            if(table[i] == null)    continue;
            pq.add(table[i]);
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            sb.append(pq.poll().c);
        }
        return sb.toString();
    }
}
