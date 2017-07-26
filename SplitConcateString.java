public class Solution {
    StringBuilder max = new StringBuilder();
    void cal(String head, List<String> rest, int skip) {
        for(int i =0; i< head.length(); ++i) {
            StringBuilder cur = new StringBuilder();
            cur.append(head.substring(i));
            for(int j = (skip + 1)%rest.size(); j != skip; j = (j + 1) % rest.size()) {
                cur.append(rest.get(j));
            }
            if( i > 0) {
                cur.append(head.substring(0, i));
            }
            for(int k = 0; k < max.length(); ++k) {
                char m = max.charAt(k);
                char c = cur.charAt(k);
                if(c > m) {
                    max = cur;
                    break;
                } else if( m > c) {
                    break;
                }
                
            }
        }
    } 
    
    public String splitLoopedString(String[] strs) {
        if(strs == null || strs.length == 0)   return "";
        List<String> order = new ArrayList<>(strs.length);
        for(String s : strs) {
            int n = s.length();
            int i = 0;
            for(; i < n - i - 1; ++i) {
                char h = s.charAt(i);
                char t = s.charAt( n - i - 1);
                if(h==t)  continue;
                if(h > t) {
                    order.add(s);
                    break;
                } else {
                    order.add(new StringBuilder(s).reverse().toString()); 
                    break;
                }
            }
            if(i >= n - i -1) 
                order.add(s);
        }
        
        for(String s : order)   max.append(s);
        for(int i = 0; i < order.size(); ++i) {
            StringBuilder cur =new StringBuilder(order.get(i));
            cal(cur.toString(), order, i);
            cal(cur.reverse().toString(), order, i);
        }
        return max.toString();
    }
}
