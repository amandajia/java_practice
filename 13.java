public class Solution {
    public int romanToInt(String s) {
         //I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1,000
         Map<Character, Integer> dict = new HashMap<> ();
         dict.put('I', 1);
         dict.put('V', 5);
         dict.put('X', 10);
         dict.put('L', 50);
         dict.put('C', 100);
         dict.put('D', 500);
         dict.put('M', 1000);
         int ret = 0;
         int pre = dict.get(s.charAt(0));
         for (int i = 1; i< s.length(); ++i) {
             int cur = dict.get(s.charAt(i));
             if (cur > pre) {
                 ret -= pre;
             } else {
                 ret += pre;
             }
             pre = cur;
         }
         ret += pre;
         return ret;
    }
}
