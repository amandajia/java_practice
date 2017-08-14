public class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ret = new ArrayList<>();
        if(words == null || words.length == 0) return ret;
        for(int i =0; i < words.length; ++i) {
            for(int j = 0; j<words.length; ++j) {
                if(i == j)  continue;
                String l = words[i];
                String s = words[j];
                int li = 0;
                int si = s.length() - 1;
                int n = words[i].length() + words[j].length();
                int count  = 0;
                while(count < n/2) {
                    if(si < 0) {
                        s = l;
                        si = s.length() - 1;
                    } 
                    if(li >= l.length()) {
                        l = s;
                        li = 0;
                    }
                    //System.out.println(words[i] + " ! " + words[j] + "  !  " + li + " !~ " + si);
                    if(l.charAt(li) != s.charAt(si))
                        break;
                    li ++;
                    si --;
                    count++;
                }
                if(count >= n/2)
                    ret.add(new ArrayList<Integer> (Arrays.asList(i, j)));
            }
        }
        return ret;
    }
}
