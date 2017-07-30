public class Solution {
    int missing(String s) {
        int big = 1;
        int small = 1;
        int digit = 1;
        for(int i =0; i< s.length(); ++i) {
            char c = s.charAt(i);
            if(c>='0' && c <= '9')  digit = 0;
            else if(c>='a' && c <= 'z')  small = 0;
            else if(c>='A' && c <= 'Z')  big = 0;
        }
        return big + small + digit;
    }
    
    static class Node {
        char c;
        int count;
        public Node(char cc, int t) {c = cc; count = t;}
    }
    PriorityQueue<Node> shoushen(String l) {
        List<Node> compose = new ArrayList<>();
        int count = l.length();
        char cur = l.charAt(0);
        int curCount =1;
        for(int i = 1; i< l.length(); ++i) {
            if(cur ==l.charAt(i)) {
                curCount ++;
                continue; }
            compose.add(new Node(cur, curCount));
            cur = l.charAt(i);
            curCount = 1;
        }
        compose.add(new Node(cur, curCount));
        PriorityQueue<Node> big = new PriorityQueue<>((n1, n2)-> n1.count%3 - n2.count%3);
        for(int i = 0; i < compose.size(); ++i) {
            int curC = compose.get(i).count;
            if(curC > 2) {
                big.add(compose.get(i));
            }    
        } 
        while(count > 20) {
            if(big.isEmpty()) break;
            Node curN = big.poll();
            curN.count --;
            if(curN.count > 2) {
                big.add(curN);
            }
            count --;
        }
        return big;
    }
    
    public int strongPasswordChecker(String s) {
        int miss = missing(s);
        if(s.length() < 6) {
            /*  if length < 4,  return 3+. adding three+ new missing charater 
                if length == 4, return 2+. adding two character separate orig string
                if length == 5 return 1+. return 1 only if at most one character missing, 2 different character exists in original string
                new charactor can perfectly seperate. return 2 if two character missing. that case, separate good as well.
            */
            
            return Math.max(miss, 6 - s.length()); 
        }
        int ret = Math.max(0, s.length() - 20);
        PriorityQueue<Node> compose = shoushen(s);
        int replace = 0;
        while(!compose.isEmpty()) {
            replace += compose.poll().count / 3;        
        }
            
        return ret + Math.max(replace, miss);
    }
}
