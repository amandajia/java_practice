public class Solution {
    public String removeDuplicateLetters(String s) {
        if(s == null) return "";
        if(s.length() == 0 || s.length() <= 1 ) return s;
        int [] last = new int [26];
        for(int i = 0; i< s.length(); ++i) {
            int cur = s.charAt(i) - 'a';
            last[cur] = i + 1; 
        }
        int min = s.length();
        for(int i = 0; i<26; ++i) {
            if(last[i]!= 0 && last[i] < min) {
                min = last[i];
            }
        }
        int index =-1;
        char choose = (char)250;
        for(int i =0; i< min; ++i) {
            if(s.charAt(i) < choose) {
                choose = s.charAt(i);
                index = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(choose);
        
        StringBuilder left = new StringBuilder();
        for(int i = index+1; i<s.length(); ++i) {
            if(s.charAt(i)!= choose)
                left.append(s.charAt(i));
        }
        //System.out.println(s+ " : " + sb.toString() + " ! " + left.toString());
        sb.append(removeDuplicateLetters(left.toString()));
        return sb.toString();
    }
}
