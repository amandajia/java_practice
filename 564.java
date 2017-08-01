  public class Solution {
    boolean woo(long v) {
        long ret = v - 1;
        while(ret > 0) {
            if(ret % 10 != 9)   return false;
            ret /= 10;
        }
        return true;
    }
    String app(String first, String mid) {
        return new StringBuilder().append(first).append(mid)
            .append(new StringBuilder().append(first).reverse().toString()).toString();
    }
    
    String minRet = null;
    long dist = Long.MAX_VALUE;
    
    void set(String attempt, long oldVal) {
        long newVal = Long.parseLong(attempt);
        long newDiff = Math.abs(newVal - oldVal);
        if(newDiff < dist) {
            dist = newDiff;
            minRet = attempt;
        } else if(newDiff == dist) {
            long small = Long.parseLong(minRet);
            if(small > newVal) {
                minRet = attempt;
            }
        }
    }
    
    public String nearestPalindromic(String n) {
        if(n.length() == 1) {
            char c = (char)(n.charAt(0) - 1);
            return new StringBuilder().append(c).toString();
        } else if(n.length() == 2) {
            minRet = "9";
            long old = Long.parseLong(n);
            dist = old - 9;
            for(int i = 11; i< 100; i = i + 11) {
                if(i ==old)  continue;
                set(new StringBuilder().append(i).toString(), old);
            }
            set(new StringBuilder().append(101).toString(), old);
            return minRet;
        }
        long oldVal = Long.parseLong(n);
        if(woo(oldVal))    return new StringBuilder().append(oldVal - 1).toString();  // 1000 - 999
        String first = n.substring(0, n.length()/ 2);
        String middle = n.substring(n.length()/ 2, (n.length()+1)/2 );
        String sec = n.substring((n.length()+1)/2);
    
        String attempt = app(first, middle);
        long newVal = Long.parseLong(attempt);
        if(newVal != oldVal) {
            //System.out.println(oldVal + " : " + newVal);
            minRet = attempt;
            dist = Math.abs(newVal - oldVal);
        }
        if(middle.length() != 0) { // dan
            String num = new StringBuilder().append(first).append(middle).toString();
            long p = Long.parseLong(num);
            long sp = p -1;
            String small = null;
            if(woo(p)) {
                small = app(new StringBuilder().append(sp).toString(), ""); // 10|0|01 -> 99 | 99
            } else
                small = app(new StringBuilder().append(sp/10).toString(), new StringBuilder().append(sp % 10).toString());
            set(small, oldVal);
            long bp = p + 1;
            String big = null;
            if(woo(bp)) {
                big = app(new StringBuilder().append(bp/10).toString(), ""); // 99| 9| 99  100| 001 
            } else {
                big = app(new StringBuilder().append(bp/10).toString(), new StringBuilder().append(bp % 10).toString());
            }
            set(big, oldVal);
        } else { // shuang
            long p = Long.parseLong(first);
            long sp = p -1;
            String small = null;
            if(woo(p)) {
                small = app(new StringBuilder().append(sp).toString(), "9"); // 100| 001 -> 99| 9| 99
            } else
                small = app(new StringBuilder().append(sp).toString(), "");
            set(small, oldVal);
            long bp = p + 1;
            String big = null;
            if(woo(bp)) {
                big = app(new StringBuilder().append(bp/10).toString(), "0"); // 99| 99  10 |0| 01 
            } else {
                big = app(new StringBuilder().append(bp).toString(), "");
            }
            set(big, oldVal);
        }
        
        return minRet;
    }
}
