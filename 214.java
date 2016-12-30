//https://leetcode.com/problems/shortest-palindrome/
public boolean isPalin(String s, int head, int tail){
        for(;head<tail; head++,tail--){
            if(s.charAt(head)!=s.charAt(tail)) return false;
           
        }
        return true;
    }
    public String shortestPalindrome(String s) {
        if(s==null || s.length()<2 ) return s;
        int length=s.length();
        String cur=s.substring(0, 1);
        String rest="";
        int odd=0;
        int [] box=new int [256];
        for(int i=0;i<length;i++) box[s.charAt(i)]++;
        for(int i=0;i<256;i++){
            if(box[i]%2!=0) odd++;
        }
        for(int i=length-1;i>=0;i--){
            int temp=(int)s.charAt(i);
            if(odd <= 1 ) {
                if(isPalin(s,0,i)) {
        		    rest=s.substring(i+1);
        		    cur=s.substring(0,i+1);
        		    break;
        	    } 
            }
            box[temp]--;
            if(box[temp]%2==0) odd--;
            else odd++;
        }
        StringBuilder sb=new StringBuilder();
        if(cur.length()==1) sb.append(s.substring(1));
        else sb.append(rest);
        sb.reverse();
        sb.append(s);
        return sb.toString();
    }
