/*
This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “makes”, word2 = “coding”, return 1.
Given word1 = "makes", word2 = "makes", return 3.

Note:
You may assume word1 and word2 are both in the list.
*/
public class Solution {
    public int swd(String[] words, String wo) {
        int w = -1;
        int ret = words.length;
        for(int i =0; i<words.length; ++i) {
            if(words[i].equals(wo)) {
                if(w>=0 && i - w < ret)  ret = i - w;
                w = i;
            } 
        }
        return ret;
    }
    
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if(word1.equals(word2)) {
            return swd(words, word1);
        }
        int w1 = -1;
        int w2 = -1;
        int ret = words.length;
        for(int i =0; i<words.length; ++i) {
            if(words[i].equals(word1)) {
                w1 = i;
                if(w2>=0 && w1 - w2 < ret)  ret = w1 - w2; 
            } else if(words[i].equals(word2)) {
                w2 = i;
                if(w1>=0 && w2 - w1 < ret)  ret = w2 - w1; 
            } 
        }
        return ret;
    }
}
