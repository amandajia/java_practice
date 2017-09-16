public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        if (k == 0 || k > n) return ret;
        if (k == 1) {
            for (int i = 1; i <= n; ++i ) {
                List<Integer> newL = new ArrayList<>();
                newL.add(i);
                ret.add(newL);
            }
            return ret;
        }
        List<List<Integer>> with = combine(n-1, k-1);
        if (n > k) {
            List<List<Integer>> without = combine(n -1, k);
            ret.addAll(without);
        }
        for(int i=0; i< with.size(); i++) {
            with.get(i).add(n);
        }
        ret.addAll(with);
        
        return ret;
    }
}
