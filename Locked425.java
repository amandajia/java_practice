/*
Given a set of words (without duplicates), find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y
Note:
There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
Example 1:

Input:
["area","lead","wall","lady","ball"]

Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
Example 2:

Input:
["abat","baba","atan","atal"]

Output:
[
  [ "baba",
    "abat",
    "baba",
    "atan"
  ],
  [ "baba",
    "abat",
    "baba",
    "atal"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of word
*/

import java.util.Date;
public class Solution {
    int l ;
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
            for(int i =0 ; i < n; ++i) {
                p = p.child[ar[i] - 'a'];
                if(p == null)
                    return ret;
            }
            
            expend(ar, n, p, ret);
            return ret;
        }
        void expend(char[] prefix, int n, Trie p,List<String> ret) {
            int count = 0;
            for(int i = 0; i< 26; ++i) {
                if(p.child[i] != null) {
                    char [] pref = new char[l];
                    for(int j = 0; j<n; ++j)
                        pref[j] = prefix[j];
                    pref[n] = p.child[i].letter;
                    expend(pref, n+1, p.child[i], ret);
                    count++;
                }
            }
            if(count == 0) 
                ret.add(new String(prefix));
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
        l = words[0].length();
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
