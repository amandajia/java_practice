//https://leetcode.com/problems/reverse-words-in-a-string/
public static String reverseWords(String s) {
        if(s==null || s.length()==0) return s;
        s=s.trim();
        int length=s.length();
        Stack<String> box=new Stack<>();
        for(int i=0;i<length;i++){
        	char c=s.charAt(i);
        	if(c==' ') continue;
        	StringBuilder sb=new StringBuilder();
        	while(c!=' '){
        		sb.append(c);
        		i++;
        		if(i>=length) break;
        		c=s.charAt(i);
        	}
        	box.add(sb.toString());
        }
        StringBuilder sb=new StringBuilder();
        while(!box.isEmpty()){
        	sb.append(box.pop());
        	sb.append(" ");
        }
        return sb.toString().trim();
    }
