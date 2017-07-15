public class Solution {
    String cal(List<String> coms) {
        if(coms.size() == 1)
            return coms.get(0);
        List<String> ret = new ArrayList<>(coms.size()/2);
        for(int i = 0; i < coms.size()/2; ++i) {
            StringBuilder sb = new StringBuilder();
            sb.append('(');
            sb.append(coms.get(i));
            sb.append(',');
            sb.append(coms.get(coms.size() - i - 1));
            sb.append(')');
            ret.add(sb.toString());
        }
        return cal(ret);
    }
    public String findContestMatch(int n) {
        List<String> com = new ArrayList<>(n);
        for(int i = 1; i<=n; ++i)
            com.add(Integer.toString(i));
        return cal(com);
    }
}
