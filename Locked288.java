/*
An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> 
false

isUnique("cart") -> 
true

isUnique("cane") -> 
false

isUnique("make") -> 
true

*/
public class ValidWordAbbr {
    Map<String, Boolean> table;
    Map<String, Integer> count;
    public ValidWordAbbr(String[] dictionary) {
        table = new HashMap<>();
        count = new HashMap<>();
        for(String s: dictionary) {
            if(s == null)   continue;
            String key = (s.length() <= 2)? s: con(s);
            if(table.containsKey(s))
                continue;
            count.putIfAbsent(key, 0);
            count.put(key, count.get(key) + 1);
            table.put(s, true);
        }
    }

    public boolean isUnique(String word) {
        String w = null;
        if(word != null)    w = (word.length() <= 2)? word: con(word);
        if(!count.containsKey(w))
            return true;
        if(count.get(w) > 1)
            return false;
        return table.containsKey(word);
    }
    
    String con(String w) {
        StringBuilder sb = new StringBuilder();
        sb.append(w.charAt(0));
        sb.append(w.length() - 2);
        sb.append(w.charAt(w.length() - 1));
        return sb.toString();
    }
}


// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");
