public class Solution {
    int cou = 0;
    class Node {
        Node [] child;
        String word;
        public Node() {
            child = new Node[26];
        }
    }
    
    class Trie {
        Node root;
        int [][] dir = new int[][] {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        public Trie(Set<String> words) {
            root = new Node();
            for(String w: words) {
                Node cur = root;
                for(int i = 0; i< w.length(); ++i) {
                    int ind = w.charAt(i) - 'a';
                    if(cur.child[ind] == null) {
                        cur.child[ind] = new Node();
                    }
                    cur = cur.child[ind];
                }
                cur.word = w;
            } 
               
        }
         void DFS(char [][] board, boolean[][] visit, int i, int j, Node parent, Set<String> ret) {
            int cur = board[i][j] - 'a';
            if(parent.child[cur] == null) {
                return ;
            }
            Node cn = parent.child[cur];
            if(cn.word != null) ret.add(cn.word);
            visit[i][j] = true;
            for(int [] d: dir) {
                int x = i + d[0];
                int y = j + d[1];
                if(x < 0 || y < 0 || x >= board.length || y >= board[0].length || visit[x][y]) continue;
                DFS(board, visit, x, y, cn, ret);
            } 
            visit[i][j] = false;
        }
       
        
        Set<String> query(char[][] b) {
            Set<String> ret = new HashSet<>();
            for(int i = 0; i< b.length; ++i) {
                for(int j = 0; j<b[0].length; ++j) {
                    boolean [][] visit = new boolean[b.length][b[0].length];
                    DFS(b, visit, i, j, root, ret);
                }
            }
            return ret;
        }
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> ret = new ArrayList<>();
        if(words ==null || words.length == 0 || board == null || board.length == 0 || board[0].length == 0)   return ret; 

        Set<String> ws = new HashSet<>();
        for(String w: words) {
            ws.add(w);
        }
        Trie t = new Trie(ws);
        Set<String> rets =  t.query(board);
        return new ArrayList<>(rets);
    }
}
