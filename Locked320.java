/*
Write a function to generate the generalized abbreviations of a word.

Example:
Given word = "word", return the following list (order does not matter):
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Seen this question in a real interview before?   Yes  
*/
public class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> ret = new ArrayList<>();
        int n = word.length();
        int perm = 0;
        double max = Math.pow(2, n);
        while(perm < max) {
            StringBuilder sb  = new StringBuilder();
            int cur = perm;
            int count = 0;
            int last = 0;
            while(count<n) {
                if(cur % 2 == 1) {
                    last++;
                } else {
                    if(last > 0) {
                        sb.append(last);
                    }
                    sb.append(word.charAt(count));
                    last = 0;
                }
                cur = cur>>1;
                count++;
            }
            if(last > 0)
                sb.append(last);
            ret.add(sb.toString());
            perm++;
        }
        return ret;
    }
} 
