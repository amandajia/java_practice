public class Solution {
    public int compareVersion(String version1, String version2) {
        List<Integer> l = convert(version1);
        List<Integer> r = convert(version2);
        int size = Math.max(l.size(), r.size());
        for(int i=0; i< size; ++i) {
            int right = 0;
            int left = 0;
            if(i < r.size())    {
                right = r.get(i);
            } 
            if(i< l.size()) {
                left = l.get(i);
            }
            if(left > right) return 1;
            else if (left < right)   return -1;
        }
        return 0;
    }
    
    List<Integer> convert(String v) {
        List<Integer> l = new ArrayList<>();
        int init = 0;
        for(int i=0; i<v.length(); ++i) {
            if(v.charAt(i) == '.') {
                l.add(init);
                init = 0;
            } else {
                init = init*10 + (int)(v.charAt(i) - '0');
            }
        }
        if(init!=0)
            l.add(init);
        return l;
    }
}
