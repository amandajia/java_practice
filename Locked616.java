/*
Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.

Example 1:
Input: 
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"
Example 2:
Input: 
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"
Note:
The given dict won't contain duplicates, and its length won't exceed 100.
All the strings in input have length in range [1, 1000].
*/
public class Solution {
    static class Interval {
        int start;
        int end;
        public Interval(int s, int e) {
            start = s; end = e;
        }
    }
    
    List<Interval> merge(PriorityQueue<Interval> pq) {
        List<Interval>ret = new ArrayList<>();
        Interval last = null;
        while(!pq.isEmpty()) {
            Interval cur = pq.poll();
            if(last == null) {
                last = cur;
            } else if(last.start == cur.start) {
                continue;
            } else if(cur.start > last.end) {
                ret.add(new Interval(last.start, last.end));
                last = cur;
            } else {
                last.end = Math.max(last.end, cur.end);
            }
        }
        if(last != null)    ret.add(last);
        return ret;
    }
    public String addBoldTag(String s, String[] dict) {
        
        PriorityQueue<Interval> pq = new PriorityQueue<Interval>((i1, i2) -> (i1.start != i2.start)? i1.start - i2.start : i2.end -i1.end);
        
        for(String d: dict) {
            int index = 0;
            while( (index = s.indexOf(d, index)) != -1 ) {
                pq.add(new Interval(index, index + d.length()));
                index ++;
            }
        }
        //System.out.println(pq.size());
        List<Interval> inters = merge(pq);
        int index = 0;
        StringBuilder sb = new StringBuilder();
        for(Interval i : inters) {
            if(i.start != index) {
                sb.append(s.substring(index, i.start));
            }
            sb.append("<b>");
            sb.append(s.substring(i.start, i.end));
            sb.append("</b>");
            index = i.end;
        }
        if(index != s.length()) {
            sb.append(s.substring(index));
        }
        return sb.toString();
    }
}
