//https://leetcode.com/problems/word-pattern/
public Queue<String> help(String str){
		Queue<String> box=new LinkedList<>();
		int length=str.length();
		for(int i=0;i<length;i++){
			char c=str.charAt(i);
			if(c==' ') continue;
			StringBuilder sb=new StringBuilder();
			while(c!=' '){
				sb.append(c);
				i++;
				if(i>=length) break;
				c=str.charAt(i);
			}
			box.add(sb.toString());
		}
		return box;
	}
	public boolean wordPattern(String pattern, String str) {
        if(pattern==null || pattern.length()==0 || str==null || str.length()==0) return false;
        HashMap<Character,String> map=new HashMap<>();
        Set<String> exist=new HashSet<>();
        int length=pattern.length();
        Queue<String> wordBox=help(str);
        if(wordBox.size()!=length) return false;
        for(int i=0;i<length;i++){
        	String curWord=wordBox.poll();
        	if(!map.containsKey(pattern.charAt(i))){
        		if(exist.contains(curWord)) return false;
        		exist.add(curWord);
        		map.put(pattern.charAt(i), curWord);
        		continue;
        	}
        	if(!map.get(pattern.charAt(i)).equals(curWord)) return false;
        }
        return true;
    }
