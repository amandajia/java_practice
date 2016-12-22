//https://leetcode.com/problems/simplify-path/
public String simplifyPath(String path) {
        if(path==null || path.length()==0) return "/";
        Stack<String> box=new Stack<>();
        int length=path.length();
        StringBuilder sb=new StringBuilder();
        int i=0;
        while(i<length){
        	if(path.charAt(i)=='/'){
        	    int j=i+1;
                while(j<length && path.charAt(j)!='/') j++;
        	    String cur=path.substring(i+1,j);
        	    if(cur.equals("..") && !box.isEmpty()) box.pop();
        	    else if(!cur.equals(".") && !cur.equals("..") && !cur.equals("")) box.add(cur);
                i=j;
            }
        }
        for (String s : box) sb.append("/"+s);
        if(sb.length()==0) return "/";
        return sb.toString();
    }
