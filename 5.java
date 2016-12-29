//https://leetcode.com/problems/longest-palindromic-substring/
public static String isPalindrom(String s, String curMax, int i, int j) {
	    while(i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)){
	        i--;
	        j++;
	    }
	    if(j-i+1>curMax.length()) curMax=s.substring(i+1,j);
	    return curMax;
	}
	
	public String longestPalindrome(String s) {
        if(s==null || s.length()<=2) return s;
        int length=s.length();
        String r1=s.substring(0,1);
        String r2=s.substring(0,1);
        for(int i=0;i<length;i++){
            r1=isPalindrom(s,r1,i,i); //for odd length
            r2=isPalindrom(s,r2,i,i+1); //for even length
        }
        if(r1.length()>r2.length()) return r1;
        return r2;
    }
