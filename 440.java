public class Solution {
    int [] util(int n) {
        List<Integer> res = new ArrayList<>();
        while(n > 0) {
            res.add(n % 10);
            n /= 10;
        }
        Collections.reverse(res);
        int [] ret = new int[res.size()];
        for(int i = 0; i< ret.length; ++i)
            ret[i] = res.get(i);
        return ret;
    }
    
    int eq(int [] det, int [] ret, int index) {
        for(int i = 0; i<= index; ++i) {
            if(det[i] == ret[i]) {
                continue;
            }
            if(det[i] > ret[i]) return -1;
            else return 1;
        }
        return 0;
    }
    
    int gen(int wei) {
        int ret = 0;
        for(int i = 0; i< wei; ++i) {
            ret = ret * 10 + 1;
        }
        return ret;
    }
    
    public int findKthNumber(int n, int k) {
        int[] det = util(n);
        int[] ret = new int[det.length];
        int index = 0;
        ret[0] = 1;
        while(k > 1) {
            int com = eq(det, ret, index);
            if(com == 0) {
                int total = gen(ret.length - index - 1);
                int diff = 0;
                for(int i = index + 1; i< ret.length; ++i) {
                    diff = diff * 10 + det[i];
                }
                total += diff + 1;
                if(k - total <= 0 ) {
                    k --; // skip current one
                    index++;
                    //System.out.println(k + " : " + index + " : " + total);
                    ret[index] = 0;
                    continue;
                }
                k -= total;
                ret[index] ++;
            } else {
                int len = (com < 0)?ret.length - index :ret.length - index -1;
                int total = gen(len);
                if(k - total <= 0 ) {
                    k --; // skip current one
                    index++;
                    ret[index] = 0;
                    continue;
                }
                k -= total;
                ret[index] ++;
            }
        }
        int res = 0;
        for(int i =0; i<= index; ++i) {
            res = res * 10 + ret[i];
        }
        return res;
    }
}
