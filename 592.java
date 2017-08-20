public class Solution {
    class Num {
        long up;
        long bot;
        public Num(String n, int s) {
            String [] nums = n.split("/");
            up = Long.parseLong(nums[0]);
            bot= Long.parseLong(nums[1]);
            if(s == -1)     {   up = -1 * up;}
        }
        @Override
        public String toString() {
            Long a = up;
            Long b = bot;
            return a.toString() + "/" + b.toString();
        }
        public Num times(Num a) {
            this.up = this.up * a.bot + a.up * this.bot;
            this.bot = a.bot * this.bot;
            if(this.up != 0) {
                long g = gcd(this.up, this.bot);
                this.up = this.up/g;
                this.bot = this.bot /g;
            } else {
                this.bot = 1;
            }
            return this;
        }
        
        long gcd(long a, long b) {
            if(a < 0) a= -1 * a;
            if(a <  b) {
                long t = a;
                a = b;
                b = t;
            }
            while( a %  b != 0) {
                long r = a % b;
                a = b;
                b = r;
            }
            return b;
        }
    }
    
    public String fractionAddition(String expr) {
        if(expr == null || expr.length() < 3)   return "";
        int def = 1;
        int lastIndex = 0;
        List<Num> can = new ArrayList<>();
        for(int i = 0; i< expr.length(); ++i) {
            char ch = expr.charAt(i);
            if(ch == '-' || ch == '+') {
                if(i != 0) {
                    Num cur = new Num(expr.substring(lastIndex, i), def);
                    can.add(cur);
                }
                lastIndex = i + 1;
                def = (ch=='-')? -1 : 1;
            }
        }
        can.add(new Num(expr.substring(lastIndex), def));
        Num ret = can.get(0);
        for(int i = 1; i < can.size(); ++i) {
            ret = ret.times(can.get(i));
        }
        return ret.toString();
    }
}
