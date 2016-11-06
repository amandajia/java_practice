//https://leetcode.com/problems/word-squares/
import java.util.Date;
public class Solution {
    public class Trie {
        public Character letter;
        public Trie [] child;
        public Trie(Character c) {
            letter = c;
            child = new Trie[26];
        }
        
        public List<String> find(char []ar, int n) {
            List<String> ret = new ArrayList<>();
            Trie p = this;
            StringBuilder sb = new StringBuilder();
            for(int i =0 ; i < n; ++i) {
                p = p.child[ar[i] - 'a'];
                if(p == null)
                    return ret;
                sb.append(ar[i]);
            }
            
            expend(sb.toString(), p, ret);
            return ret;
        }
        void expend(String prefix, Trie p,List<String> ret) {
            int count = 0;
            for(int i = 0; i< 26; ++i) {
                if(p.child[i] != null) {
                    String pref = prefix + p.child[i].letter;
                    expend(pref, p.child[i], ret);
                    count++;
                }
            }
            if(count == 0) 
                ret.add(prefix);
        }
        
        
    }
    
    Trie writeDict(String[] words) {
        Trie dict = new Trie(null);
        for(String s: words) {
            Trie p = dict;
            for(int i =0; i<s.length(); ++i) {
                char cur = s.charAt(i);
                if(p.child[cur - 'a'] == null ) {
                    p.child[cur - 'a'] = new Trie(cur);
                }
                p = p.child[cur - 'a'];
            }
        }
        return dict;
    }
    
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> ret = new ArrayList<>();
        if(words == null || words.length == 0)  return ret;
        Date start = new Date();
        Trie dict = writeDict(words);
        
        for(String w: words) {
            Queue<List<String>> line = new LinkedList<>();
            List<String> temp = new ArrayList<>();
            temp.add(w);
            line.add(temp);
            while( !line.isEmpty()) {
                List<String> s = line.poll();
                if(s.size() == w.length()) {
                    ret.add(s);
                    continue;   
                }
                int n = s.size();
                char [] query = new char [w.length()];
                for(int i = 0; i< n; ++ i)
                    query[i] = s.get(i).charAt(n);
                List<String> nexts = dict.find(query, n);
                for(String cur : nexts){
                    List<String> ns = new ArrayList<>(s);
                    ns.add(cur);
                    if(ns.size() == w.length())
                        ret.add(ns);
                    else 
                        line.add(ns);
                }
                    
            }
    
        }
        return ret;
        
    }
}
