public class Solution {
    public boolean isNumber(String s) {
        if(s == null)   return false;
        s = s.trim();
        if(s.length() == 0) return false;
        int i = 0;
        int digits = 0;
        if(s.charAt(i) == '+' || s.charAt(i) == '-')
            i++;
        while(i< s.length() && Character.isDigit(s.charAt(i))){
            i++;
            digits++;
        }
        if(i< s.length() && s.charAt(i) == '.')
            i++;
        if(digits == 0 && ( i >= s.length() || !Character.isDigit(s.charAt(i))) )
            return false;
        while(i< s.length() && Character.isDigit(s.charAt(i)))
            i++;
        if(i< s.length() && ( s.charAt(i) == 'e' || s.charAt(i) == 'E' )) {
            digits = 0;
            i++;
            //System.out.println(i + s);
            if(i< s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-'))
                i++;
            while(i< s.length() && Character.isDigit(s.charAt(i))){
                i++;
                digits++;
            }
            if(digits == 0)
                return false;
        }
        if(i < s.length())  return false;
        return true;
        
    }
}
