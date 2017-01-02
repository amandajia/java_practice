//https://leetcode.com/problems/sort-characters-by-frequency/
public String frequencySort(String s) {
        if(s==null || s.length()<=1) return s;
        String result="";
        int[] freq=new int[256];
        for(int i=0;i<s.length();i++) freq[s.charAt(i)]++;
   
        TreeMap<Integer,List<Character>> box=new TreeMap<>();
        for(int i=0;i<256;i++){
            List<Character> cur=new LinkedList<Character>();
        	if(freq[i]>0){
        	   if(box.containsKey(freq[i])){
        	       cur=box.get(freq[i]);
        	   }
        	   cur.add((char)i);
        	   box.put(freq[i],cur);
        	}
        }
        StringBuilder sb=new StringBuilder();
        for(Integer i : box.keySet()){
            List<Character> temp=box.get(i);
            for(int j=0;j<temp.size();j++){
            	for(int z=0;z<i;z++){
            	    sb.append(temp.get(j));
            	}
            }
        }
        sb.reverse();
        result=sb.toString();
        return result;
    }
