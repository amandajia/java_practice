//https://leetcode.com/problems/anagrams/
public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> result=new LinkedList<List<String>>();
        if(strs==null || strs.length==0) return result;
        HashMap<String,List<String>> box=new HashMap<>();
        for(int i=0;i<strs.length;i++){
        	List<String> temp=new LinkedList<>();
        	String original=strs[i];
        	char[] toChars=strs[i].toCharArray();
        	Arrays.sort(toChars);
        	String sortedArr=new String(toChars);
        	if(box.containsKey(sortedArr)){
        		temp=box.get(sortedArr);
        		temp.add(original);
        		box.put(sortedArr, temp);
        	}
        	else{
        		temp.add(original);
        		box.put(sortedArr, temp);
        	}
        }  
        result.addAll(box.values());
        return result;
    }
