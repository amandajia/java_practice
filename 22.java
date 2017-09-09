public class Solution {
    
    private void cal(int piled, int remain, StringBuffer pre, List<String> ret) {
        if(remain == 0) {
            for (int i=0; i< piled; ++i) 
                pre.append(")");
            ret.add(pre.toString());
            return;
        } 
        if (piled > 0) {
            StringBuffer newPre = new StringBuffer(pre);
            newPre.append(")");
            cal(piled-1, remain, newPre, ret);
        }
        pre.append("(");
        cal(piled+1, remain -1, pre, ret);
        
    }
    
    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        cal(0, n, sb, ret);
        return ret;
    }
}
