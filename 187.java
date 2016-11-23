//https://leetcode.com/problems/repeated-dna-sequences/
public List<String> findRepeatedDnaSequences(String s) {
		List<String> result=new LinkedList<String>();
        if(s==null || s.length()<10) return result;
        HashMap<String,Integer> box=new HashMap<>();
        int i=0;
        while(i+10<=s.length()){
        	String cur=s.substring(i, i+10);
        	if(!box.containsKey(cur)) box.put(cur, 1);
        	else{
        		int temp=box.get(cur);
        		box.put(cur, temp+1);
        	}
        	i++;
        }
        for(String e:box.keySet()){
        	if(box.get(e)>1){
        		result.add(e);
        	} 
        }
        return result;
    }
