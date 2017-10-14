public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ret = new ArrayList<>();
        ret.add(1);
        if (rowIndex == 0) return ret;
        ret.add(1);
        if (rowIndex == 1) return ret;
        for (int i = 2; i<= rowIndex; ++i) {
            List<Integer> next = new ArrayList<>();
            next.add(1);
            for (int j = 0; j< ret.size() - 1; ++j) {
                next.add(ret.get(j) + ret.get(j + 1));
            }
            next.add(1);
            ret = next;
        }
        return ret;
    }
}
