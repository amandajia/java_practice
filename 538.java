public class Solution {
    class Complex {
        int a ;
        int b ;
        public Complex(int aa, int bb) {
            a = aa;
            b = bb;
        }
        public Complex(String n) {
            int sign = 1;
            Integer cur = null;
            
            for(int i = 0; i< n.length(); ++i) {
                if(n.charAt(i) == '-') {
                    sign = -1;
                } else if(n.charAt(i) == '+') {
                    if(cur == null) continue;
                    a = sign * cur;
                    sign = 1;
                    cur = null;
                } else if(n.charAt(i) == 'i') {
                    b = sign * cur;
                    break;
                } else if(n.charAt(i) >= '0' && n.charAt(i)<='9') {
                    if(cur == null )    cur = n.charAt(i) - '0';
                    else                cur = cur * 10 + n.charAt(i) - '0';  
                } 
                
                
            }
        }
        public String getVal() {
            StringBuilder sb = new StringBuilder();
            sb.append(a);
            sb.append('+');
            sb.append(b);
            sb.append('i');
            return sb.toString();
        }
        
        public Complex mul(Complex c) {
            return new Complex(c.a * a - c.b * b, a * c.b + b * c.a);
        }
    }
    public String complexNumberMultiply(String a, String b) {
        Complex c1 = new Complex(a);
        Complex c2 = new Complex(b);
        return c1.mul(c2).getVal();
    }
}
