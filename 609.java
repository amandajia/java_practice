public class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> fileToPath = new HashMap<>();
        for(String path: paths) {
            String[] p = path.split(" ");
            for(int i = 1; i< p.length; ++i) {
                String [] nc = p[i].split("\\(");
                String file = nc[1].substring(0, nc[1].length() - 1);
                fileToPath.putIfAbsent(file, new ArrayList<String>());
                List<String> lp =  fileToPath.get(file);
                StringBuilder sb = new StringBuilder();
                sb.append(p[0]);
                sb.append('/');
                sb.append(nc[0]);
                lp.add(sb.toString());
            }
        }
        List<List<String>> ret = new ArrayList<>();
        for(List<String> v:  fileToPath.values()) {
            if(v.size()> 1) {
                ret.add(v);
            }
        }
        return ret;
    }
}
