public class Solution {
    
    public boolean checkInclusion(String s1, String s2) {
        if(s1 ==null || s2== null || s1.length() > s2.length()) return false;
        int [] target = new int[26];
        for(int i = 0; i < s1.length(); ++i) {
            target[s1.charAt(i) - 'a'] --;
            target[s2.charAt(i) - 'a'] ++;
        }
        int nzc = 0;
        for(int i = 0; i < 26; ++i) {
            if(target[i] != 0)
                nzc ++;
        }
        if(nzc == 0)    return true;
        for(int i = s1.length() ; i< s2.length(); ++i ) {
            int cur = s2.charAt(i)-'a';
            int pre = s2.charAt(i - s1.length()) -'a';
            target[cur]++;
            target[pre] --;
            if(cur == pre)  continue;
            if(target[cur] == 0)    nzc --;
            if(target[pre] == 0)    nzc --;
            if(target[cur] == 1)    nzc ++;
            if(target[pre] == -1)   nzc ++;
            if(nzc == 0)    return true;
        }
        return false;
    }
}
