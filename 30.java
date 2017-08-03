public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ret = new ArrayList<>();
        if(words == null || words.length == 0)  return ret;
        int n = words.length;
        int w = words[0].length();
        Map<String, Integer> table = new HashMap<>(); 
        int counter = 0;
        int [] freq = new int[n];
        for(int i = 0; i<n; ++i) {
            if(!table.containsKey(words[i])){
                table.put(words[i], counter);
                freq[counter]++;
                counter++;
            }
            else 
                freq[table.get(words[i])]++;
        }
        int [] v = new int [s.length() - w + 1];
        for(int i = 0; i < v.length; ++i) {
            String key = s.substring(i, i + w);
            Integer se = table.get(key);
            v[i] = (se == null)? -1 : se;
        }
        for(int i = 0; i < s.length() - w * n +1; ++i ){
            if(v[i] == -1)  continue;
            int [] check = new int[counter];
            int j;
            for(j = 0; j< n; ++j) {
                if(v[i + j *w] < 0)
                    break;
                int in = v[i + j *w];
                check[in] ++;
                if(check[in] > freq[in])
                    break;
            }
            if(j == n) 
                ret.add(i);
        }
        return ret;
    }
}
