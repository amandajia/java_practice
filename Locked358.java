/*
Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:
s = "aabbcc", k = 3

Result: "abcabc"

The same letters are at least distance 3 from each other.
Example 2:
s = "aaabc", k = 3 

Answer: ""

It is not possible to rearrange the string.
Example 3:
s = "aaadbbcc", k = 2

Answer: "abacabcd"

Another possible answer is: "abcabcda"

The same letters are at least distance 2 from each other.
Credits:
Special thanks to @elmirap for adding this problem and creating all test cases.



*/
public class Solution {
    public class charCount {
        public char c;
        public int count;
        public charCount(char cc, int co) {
            c = cc;
            count = co;
        }
    }
    public String rearrangeString(String str, int k) {
        Queue<charCount> blackList = new LinkedList<>();
        PriorityQueue<charCount> pq = new PriorityQueue<charCount> ( new Comparator<charCount> () {
            @Override
            public int compare(charCount c1, charCount c2) {
                return c2.count - c1.count;
            }
        }); 
        int[] table = new int[256];
        for(int i = 0; i<str.length(); ++i) {
            table[(int)str.charAt(i)] ++;
        }
        for(int i = 0; i< 256; ++i) {
            if(table[i] > 0)
                pq.add(new charCount((char)i, table[i]));
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            charCount cur = pq.poll();
            sb.append(cur.c);
            cur.count --;
            blackList.add(cur);
            if(blackList.size()>= k) {
                charCount unleash = blackList.poll();
                if(unleash.count > 0 ) {
                    pq.add(unleash);
                }
            }
        }
        while(!blackList.isEmpty()) {
            if(blackList.poll().count > 0)
                return "";
        }
        return sb.toString();
    }
}
