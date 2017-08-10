/*
Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.

Begin with the first character and then the number of characters abbreviated, which followed by the last character.
If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
If the abbreviation doesn't make the word shorter, then keep it as original.
Example:
Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
Note:
Both n and the length of each word will not exceed 400.
The length of each word is greater than 1.
The words consist of lowercase English letters only.
The return answers should be in the same order as the original array.
*/
public class Solution {
    public List<String> wordsAbbreviation(List<String> dict) {
        Map<String, String> table = new HashMap<>();
        PriorityQueue<String> pq = new PriorityQueue<>( new Comparator<String> () {
            @Override
            public int compare(String s1, String s2) {
                if(s1.length() != s2.length())  return s1.length() - s2.length();
                if(s1.charAt(0)!=s2.charAt(0))  return s1.compareTo(s2);
                if(s1.charAt(s1.length() - 1) != s2.charAt(s2.length() - 1))    return s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1);
                return s1.compareTo(s2);
            } 
        });
        for(String d: dict) {
            pq.add(d);
        }
        while(!pq.isEmpty()) {
            List<String> lex = new ArrayList<>();
            String first = pq.poll();
            while(!pq.isEmpty() && pq.peek().length() == first.length() &&  
                  pq.peek().charAt(0) == first.charAt(0) && pq.peek().charAt(first.length() - 1) == first.charAt(first.length() - 1) ) {
                lex.add(pq.poll());
            }
           // System.out.println("GROUP    ====");
            int least = 1;
            for(int k = 0; k< lex.size(); ++k) {
                String next = lex.get(k);
                int i = 1;
                for(; i < first.length(); ++i) {
                    if(first.charAt(i)!=next.charAt(i)) {
                        break;
                    }
                }
                int fir_index = Math.max(least, i + 1);
                encode(first, fir_index, table);
               //System.out.println(first);
                least = i + 1;
                first = next;
            }
            //System.out.println(first);
            encode(first, least, table);
        }
        
        List<String> ret = new ArrayList<>();
        for(String d : dict) {
            ret.add(table.get(d));
        }
        return ret;
    }
    
    void encode(String s, int index, Map<String, String> table) {
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(0, index));
        sb.append(s.length() - index - 1);
        sb.append(s.charAt(s.length() - 1));
        if(sb.length()>= s.length()) {
            table.put(s, s);
        } else {
            table.put(s, sb.toString());
        }
    }
}
