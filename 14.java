public class Solution {
    public String longestCommonPrefix(String[] strs) {
        String ret = "";
        if (strs == null || strs.length == 0) return ret;
        int index = 0;
        while (index<strs[0].length()) {
            char t = strs[0].charAt(index);
            int i = 0;
            for(; i< strs.length; ++i) {
                if(strs[i].length()<= index || strs[i].charAt(index) != t) 
                    break;
            }
            if(i < strs.length) break;
            index++;
        }
        return strs[0].substring(0, index);
    }
}
