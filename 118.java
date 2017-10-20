public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<>();
        if(numRows == 0) return ret;
        List<Integer> line = new ArrayList<>();
        line.add(1);
        ret.add(line);
        for(int i = 1; i< numRows; ++i) {
            List<Integer> past = ret.get(i-1);
            List<Integer> cur = new ArrayList<>();
            cur.add(1);
            for(int j = 0; j < i - 1; j++) {
                cur.add(past.get(j) + past.get(j+1));
            }
            cur.add(1);
            ret.add(cur);
        }
        
        return ret;
    }
}
