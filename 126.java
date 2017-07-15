//https://leetcode.com/problems/word-ladder-ii/
public class Solution {
    boolean connect(String a, String b) {
        int diff = 0;
        for(int i =0; i< a.length(); ++i) {
            if(a.charAt(i) != b.charAt(i)) {
                diff++;
                if(diff > 1)    return false;   
            }
        }
        return diff == 1;
    }
    
    static class Node {
        String word;
        int dist;
        public Node(String w, int d) {
            word = w;
            dist = d;
        }
    }
    
    void findPath(Node cur, String sw, List<String> path, List<List<String>> ret, Map<String, List<Node>> pa) {
        if(cur.word.equals(sw)) {
            path.add(sw);
            Collections.reverse(path);
            ret.add(path);
            return;
        }
        for(Node pre : pa.get(cur.word)) {
            List<String> np = new ArrayList<>(path);
            np.add(cur.word);
            findPath(pre, sw, np, ret, pa);
        }
    }
    
    public List<List<String>> findLadders(String bw, String ew, List<String> wordList) {
        if(wordList == null || wordList.size() == 0 )   return new ArrayList<>();
        List<List<String>> ret = new ArrayList<>();
        Map<String, Integer> table = new HashMap<>();
        Map<String, List<Node>> parents = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<Node>((n1, n2) -> n1.dist - n2.dist);
        Node source = new Node(bw, 0);
        for(String w: wordList) {
            if(connect(w, bw)) {
                table.put(w, 1);
                Node n = new Node(w, 1);
                pq.add(n);
                List<Node> ns = new ArrayList<>();
                ns.add(source);
                parents.put(w, ns);
            } else {
                table.put(w, Integer.MAX_VALUE);
            }
        }
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(cur.word.equals(ew)) {
                findPath(cur, bw, new ArrayList<>(), ret, parents);
                return ret;
            }
            if(cur.dist > table.get(cur.word))  continue;
            char[] chs = cur.word.toCharArray();
            for(int i = 0; i < chs.length; ++i) {
                char orig = chs[i];
                for(int to = 'a'; to <= 'z'; ++to) {
                    if(orig == to)  continue;
                    chs[i] = (char)to;
                    String chw = new String(chs);
                    if(table.containsKey(chw)) {
                        int oldV = table.get(chw);
                        if( oldV > cur.dist + 1) {
                            table.put(chw, cur.dist + 1);
                            pq.add(new Node(chw, cur.dist + 1));
                            List<Node> ns = new ArrayList<>();
                            ns.add(cur);
                            parents.put(chw, ns);
                        } else if (oldV == cur.dist + 1) {
                            parents.get(chw).add(cur);
                        }
                    } 
                }
                chs[i] = orig;
            }
        }
        return ret;
    }
}
