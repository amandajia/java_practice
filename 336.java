//https://leetcode.com/problems/palindrome-pairs/
public static boolean isPal(String s) {
	    for (int i = 0; i < s.length()/2; i++){
	        if (s.charAt(i) != s.charAt(s.length()-1-i)) return false;
	    }
	    return true;
	}
	public static List<List<Integer>> palindromePairs(String[] words) {
		Set<List<Integer>> result=new HashSet<List<Integer>>();
		List<List<Integer>> finalResult= new LinkedList<List<Integer>>();
		if(words==null || words.length<2) return finalResult;
		HashMap<String, Integer> box=new HashMap<>();
		for(int i=0;i<words.length;i++) box.put(words[i], i);
		for(int i=0;i<words.length;i++){
			for(int j=0;j<=words[i].length();j++){
				String A=words[i].substring(0, j);
				String B=words[i].substring(j,words[i].length());
				if(isPal(A)==true){
					String temp = new StringBuilder(B).reverse().toString();
					if(box.containsKey(temp) && box.get(temp)!=i){
						List<Integer> subList=new LinkedList<>();
						subList.add(box.get(temp));
						subList.add(i);
						result.add(subList);
					}
				}
				if(isPal(B)==true){
					String temp = new StringBuilder(A).reverse().toString();
					if(box.containsKey(temp) && box.get(temp)!=i){
						List<Integer> subList=new LinkedList<>();
						subList.add(i);
						subList.add(box.get(temp));
						result.add(subList);
					}
				}
			}
		}
		finalResult.addAll(result);
		return finalResult;
    }
