/*
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].

*/
public class Solution {
    String nextPerm(String s) {
        char [] cs = s.toCharArray();
        int n = cs.length;
        int i = n - 2;
        for(; i >=0; i--) {
            if(cs[i + 1] <= cs[i])  continue;
            // i < i + 1;
            Arrays.sort(cs, i + 1, n);
            //System.out.println(cs);
            int j = i + 1;
            for(; j<n; ++j) {
                if(cs[j] > cs[i]) {
                    break;
                }
            }
            char temp = cs[i];
            cs[i] = cs[j];
            cs[j] = temp;
            break;
        }
        if(i < 0 )  return null;
        return new String(cs);
    }
    
    public List<String> generatePalindromes(String s) {
        List<String> ret = new ArrayList<>();
        if(s == null || s.length() == 0)  return ret;
        int [] count = new int[256];
        for(int i = 0; i<s.length(); ++i) {
            count[s.charAt(i)] ++;
        }
        int odd = -1;
        int ocount = 0;
        for(int i = 0; i< 256; ++i) {
            if(count[i] % 2 == 1) {
                odd = i;
                ocount++;
            }
        }
        if(ocount > 1)  return ret;
        Character mid = (odd == -1)? null: (char)odd;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< 256; ++i) {
            if(count[i] < 2)    continue;
            int c = count[i] /2 ;
            char [] cur = new char[c];
            Arrays.fill(cur, (char)i);
            sb.append(cur);
        }
        String cur = sb.toString();
        do {
            sb = new StringBuilder();
            sb.append(cur);
            if(mid != null) sb.append(mid);
            sb.append(new StringBuilder(cur).reverse().toString());
            ret.add(sb.toString());
        } while((cur = nextPerm(cur)) != null);
        return ret;
    }
}
