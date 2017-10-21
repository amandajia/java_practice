public class Solution {
    public String convert(String s, int numRows) {
    	if (numRows == 1) return s;
        StringBuilder [] sb = new StringBuilder[numRows];
        for (int i= 0; i<numRows; ++i) 
            sb[i] = new StringBuilder();
        boolean in = true;
        for(int i=0, k=0; i< s.length(); ++i) {
            sb[k].append(s.charAt(i));
            if(in && k < numRows - 1) k ++;
            else if (in && k == numRows - 1) {in = false ; k = numRows - 2; }
            else if (!in && k > 0 ) k --;
            else {
                in = true;
                k = 1;
            }
        }
        String ret = sb[0].toString();
        for (int i=1; i< numRows; ++i) 
            ret += sb[i].toString();
        return ret;
    }
}
