public class Solution {
    public int con(int start, int end, int n) {
        return (start + 1)* n  + end;
    }
    boolean isPar(Map<Integer, Integer> par, int start, int end, int n) {
        if((start + end)%2 == 1) {
            int i = (start + end)/2;
            Integer radius = par.get(con(i, i + 1, n));
            if(radius == null)  return false;
            return radius >= i - start;
        } else { //single core
            int i = (start + end)/2;
            Integer radius = par.get(i);
            if(radius == null)  return false;
            return radius >= i - start; 
        }
    }
    public int minCut(String s) {
        if(s == null || s.length() == 0)    return 0;
        int [] sub = new int [s.length()];
        int n = s.length();
        sub[0] = 0;
        Map<Integer, Integer> par = new HashMap<>();
        for(int i = 0; i < n - 1; ++i) {
            int pos = 1;
            while(i - pos >=0 && i + pos < n && (s.charAt(i - pos) == s.charAt(i + pos))) {   
                    pos++;
                }
            if(pos > 1) par.put(i, pos - 1);
            if(s.charAt(i) == s.charAt(i + 1)) {
                int start = i - 1;
                int end = i + 2;
                while(start >= 0 && end < n && s.charAt(start) == s.charAt(end)) {
                    start--;
                    end ++;
                }
                par.put(con(i, i + 1, n), i - start - 1);
               // System.out.println(con(i, i + 1, n) + " !"+  (i - start - 1));
            }
                
        }
        
        for(int i = 1; i < sub.length; ++i) {
            sub[i] = sub[i - 1] + 1;
            if(isPar(par, 0, i, n)) {
                sub[i] = 0;
                continue;
            }
            for(int j = i - 1; j>0; j--) {
                if(sub[j - 1] + 1 >= sub[i])    continue;
                if(isPar(par, j, i, n)) sub[i] = sub[j - 1] + 1;
            }
        }
        return sub[s.length() - 1];
        
    }
}
