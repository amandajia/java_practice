public class Solution {
        
    public List<String> letterCombinations(String digits) {
        String [] phone = new String[10];
        phone[2] = "abc";
        phone[3] = "def";
        phone[4] = "ghi";
        phone[5] = "jkl";
        phone[6] = "mno";
        phone[7] = "pqrs";
        phone[8] = "tuv";
        phone[9] = "wxyz";
        List<String> chars = new ArrayList<>();
        if(digits == null || digits.length() == 0)    return chars;
        int count = 1;
        for(int i= 0; i<digits.length(); ++i) {
            char d = digits.charAt(i);
            if(d == '0' || d == '1')    return new ArrayList<String> ();
            String can = phone[d - '0'];
            chars.add(can);
            count *= can.length();
        }
        char[][] ret = new char[count][digits.length()];
        int grain = count;
        for(int j=0; j< chars.size(); ++j) {
            String cur = chars.get(j);
            grain /= cur.length();
            for(int i= 0; i< count; ++i) {
                int offset = (i/grain) % cur.length();
                ret[i][j] = cur.charAt(offset);
            }
        }
        
        List<String> f = new ArrayList<>();
        for(int i=0; i< count; ++i) {
            char [] r = ret[i];
            f.add(new String(r));
        }
        return f;
    }
}
