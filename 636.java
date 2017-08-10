public class Solution {
    private final static String S = "start";
    
    public int[] exclusiveTime(int n, List<String> logs) {
        int [] ret = new int[n];
        Stack<Integer> func = new Stack();
        Integer last = null;
        for(String l : logs) {
            String[] seg = l.split(":");
            int fId = Integer.parseInt(seg[0]);
            int time = Integer.parseInt(seg[2]);
            if(seg[1].equals(S)) {
                if(last != null && !func.isEmpty()) {
                    ret[func.peek()] += time - last;
                }
                func.push(fId);
                last  = time;
            } else {
                func.pop();
                ret[fId] += time - last + 1;
                last = time + 1;
            }
            
        }
        
        return ret;
    }
}
