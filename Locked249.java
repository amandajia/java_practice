/*
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
A solution is:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
Seen this question in a real interview before?   Yes  No
Difficulty:Medium
Total Accepted:25.4K
Total Submissions:61.7K
Contributor: LeetCode
Companies 
Google Uber 
Related Topics 
Hash Table String 
Similar Questions 
Group Anagrams 
 
*/
public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<List<Integer>, List<String>> table = new HashMap<>();
        for(String cur : strings) {
            List<Integer> key = new ArrayList<>();
            for(int i=1; i<cur.length(); ++i) {
                key.add((cur.charAt(i) + 26 - cur.charAt(i-1))%26);
            }
            List<String> v = table.get(key);
            if(v == null) {
                v = new ArrayList<>();
                table.put(key, v);
            }
            v.add(cur);
        }
        List<List<String>> ret = new ArrayList<>();
        for(List<String> i: table.values()) {
            ret.add(i);
        }
        return ret;
    }
}
