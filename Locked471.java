/*
Given a non-empty string, encode the string such that its encoded length is the shortest.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.

Note:
k will be a positive integer and encoded string will not be empty or have extra space.
You may assume that the input string contains only lowercase English letters. The string's length is at most 160.
If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them is fine.
Example 1:

Input: "aaa"
Output: "aaa"
Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.
Example 2:

Input: "aaaaa"
Output: "5[a]"
Explanation: "5[a]" is shorter than "aaaaa" by 1 character.
Example 3:

Input: "aaaaaaaaaa"
Output: "10[a]"
Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".
Example 4:

Input: "aabcaabcd"
Output: "2[aabc]d"
Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".
Example 5:

Input: "abbbabbbcabbbabbbc"
Output: "2[2[abbb]c]"
Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "2[2[abbb]c]".
Seen this question in a real interview before?
*/
public class Solution {
    static class Node {
        String word;
        int time;
        int last ;
        StringBuilder sb;
        public Node(String w, int t) {
            word = w; time = t;
        }
        void cal() {
            if(last == time)   return;
            sb = new StringBuilder();
            if(time == 1) {
                sb.append(word);
            } else {
                sb.append(time);
                sb.append('[');
                sb.append(word);
                sb.append(']');
                if(sb.length() > time * word.length()){
                    sb = new StringBuilder();
                    for(int i = 0; i< time; ++i) {
                        sb.append(word);
                    }
                } 
            }
            last = time;
        }
        
        int size() {
            cal();
            return sb.length();
        }
        String getRet() {
            cal();
            return sb.toString();
        }
    }
    Map<String,String> table = new HashMap<>();
    public String encode(String s) {
    	if(s.length() < 4)	return s;
        if(table.containsKey(s))    return table.get(s);
        List<Node> [] shortest = new ArrayList[s.length() + 1];
        shortest[0] = new ArrayList<Node>();
        for(int i = 0; i< s.length(); ++i) {
            List<Node> small = new ArrayList<Node>();
            small.add(new Node(s.substring(0, i + 1), 1));
            for(int j = 0; j <= i; ++j) {
                // add to end
                List<Node> old =  shortest[j];
                List<Node> l = new ArrayList<>();
                for(Node n: old) {
                    l.add(new Node(n.word, n.time));
                }
                String left = s.substring(j, i + 1);
                if(l.size()> 0 && left.equals(l.get(l.size() - 1).word)){
                    l.get(l.size() - 1).time++;
                    if(getSize(l)< getSize(small)) {
                        small = l;
                    }
                } else {
                	Node min = new Node(left, 1);
                	for(int size = 1; size <= left.length()/2; ++size) {
                		if(size != 1 && left.length()% size != 0)	continue;
                		String first = left.substring(0, size);
                		int k = 0;
                		int count = left.length()/size;
	                    for(; k < count-1; ++k) {
	                    	if(!first.equals(left.substring((k + 1)*size, (k+2)*size))) {
	                    		break;
	                    	}
	                    }
	                    if(k == count - 1) {
                            String en = encode(first);
	                    	Node nn  = new Node(en, count);
	                    	if(nn.size() < min.size())	
	                    		min = nn;
	                    }
                	}
                	l.add(min);
                	if(getSize(l)< getSize(small)) {
                        small = l;
                    }
                }
            }
            
            shortest[i + 1] = small;
            //System.out.println(s.substring(0, i + 1) + " : coded " + print(small));
        }
        
        String ret = print(shortest[s.length()]);
        table.put(s, ret);
        return ret;
    }
    int getSize(List<Node> l) {
        int size = 0;
        for(Node n : l)
            size+= n.size();
        return size;
    }
    
    String print(List<Node> ret) {
        StringBuilder sb = new StringBuilder();
        for(Node n: ret) {
            sb.append(n.getRet());
        }
        return sb.toString();
    }
    
}
