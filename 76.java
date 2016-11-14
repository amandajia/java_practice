//https://leetcode.com/problems/minimum-window-substring/
public String minWindow(String s, String t) {
        if(s==null || s.length()==0 || t==null || t.length()==0 || (s.length()<t.length())) return "";
        int min=Integer.MAX_VALUE;
        int total=t.length();
        int[] box=new int[128];
        int head=0;
        int tail=0;
        for(int i=0;i<t.length();i++){
        	box[t.charAt(i)]++;
        }
        for(int i=0;i<s.length();i++){
        	if(box[s.charAt(i)]-1>=0){
        	    total--;
        	}
        	box[s.charAt(i)]--;8
        	while(total==0){
        		if(i-tail+1<min){
        			min=i-tail+1;
        			head=tail;
        		}
        		if(box[s.charAt(tail)]+1>0){
        		    total++;
        		}
        		box[s.charAt(tail)] ++;
        		tail++;
        	}
        }
        if(min==Integer.MAX_VALUE) return "";
        return s.substring(head, head+min);
    }
