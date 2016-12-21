//https://leetcode.com/problems/interleaving-string/
public boolean isInterleave(String s1, String s2, String s3) {
        if(s1==null && s2==null && s3!=null) return false;
        if(s1==null || s1.length()==0) return s2.equals(s3);
        if(s2==null || s2.length()==0) return s1.equals(s3);
        int l1=0;
        int l2=0;
        if(s1!=null) l1=s1.length();
        if(s2!=null) l2=s2.length();
        if((s3!=null && s3.length()!=l1+l2) || s3==null) return false;
        boolean[][] box=new boolean[l1+1][l2+1];
        for(int i=1;i<=l1;i++){
        	box[i][0]=s1.charAt(i-1)==s3.charAt(i-1);
        	if(box[i][0]==false) break;
        }
        for(int j=1;j<=l2;j++){
        	box[0][j]=s2.charAt(j-1)==s3.charAt(j-1);
        	if(box[0][j]==false) break;
        }
        for(int i=1;i<=l1;i++){
        	for(int j=1;j<=l2;j++){
        		char temp=s3.charAt(i+j-1);
        		if(box[i-1][j]==true){
        			box[i][j] = (s1.charAt(i-1)==temp) ;
        		}
        		else if(box[i][j-1]==true){
        			box[i][j] = (s2.charAt(j-1)==temp);
        		} 
        	}
        }
        return box[l1][l2];
    }
