/*
This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

*/

public class WordDistance {
    Map<String, List<Integer>> dict = new HashMap<>();

    public WordDistance(String[] words) {
        for(int i = 0; i< words.length; ++i) {
            String word = words[i];
            dict.putIfAbsent(word, new ArrayList<Integer>());
            dict.get(word).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> l1 = dict.get(word1);
        List<Integer> l2 = dict.get(word2);
        int ret = Integer.MAX_VALUE;
        int i = 0; 
        int j = 0;
        while(i < l1.size() && j <l2.size()) {
            int dist = Math.abs(l1.get(i) - l2.get(j));
            if(dist < ret) ret = dist;
            if(l1.get(i) > l2.get(j))
                j++;
            else 
                i++;
        }
        return ret;
    }
}

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");
