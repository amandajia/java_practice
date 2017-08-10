/*
A string such as "word" contains the following abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Given a target string and a set of strings in a dictionary, find an abbreviation of this target string with the smallest possible length such that it does not conflict with abbreviations of the strings in the dictionary.

Each number or letter in the abbreviation is considered length = 1. For example, the abbreviation "a32bc" has length = 4.

Note:
In the case of multiple answers as shown in the second example below, you may return any one of them.
Assume length of target string = m, and dictionary size = n. You may assume that m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20.
Examples:
"apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")

"apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap3", "a3e", "2p2", 

*/

public class Solution {
    int count = 1;
    private int c1(String s) {
                int count = 0;
                int sum = 0;
                for(int i=0; i< s.length(); ++i) {
                    if(s.charAt(i)>=0 && s.charAt(i) <=9) 
                        count = 1;
                    else{
                        if(count > 0) {
                            sum++;
                            count = 0;
                        }
                        sum++;
                    }
                }
                return sum;
            }
    public String minAbbreviation(String target, String[] dictionary) {
        int n = target.length();
        int [] ins = new int [dictionary.length];
        int ic = 0;
        for(int i =0; i< dictionary.length; ++i) {
            if(dictionary[i].length() == n)
                ins[ic++] = i;
        }
        if(ic == 0) return Integer.toString(target.length());
        int [] arr = new int [n];
        
        PriorityQueue<String> pq = new PriorityQueue<String> (new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return c1(o1) - c1(o2);
            }
        });
        
        while(count<= n) {
            String abbr = getNext(target, arr);
            if(!pq.isEmpty() && c1(abbr)>= c1(pq.peek()) )    continue;
            boolean notfit = false;
            for(int i=0; i< ic; ++i) {
                if(valid(dictionary[ins[i]], abbr) ) {
                    notfit = true;
                    break;
                }
            }
            if(!notfit) pq.add(abbr);
        }
        return pq.poll();
    }
    
    String getNext(String target, int []arr) {
        StringBuilder sb = new StringBuilder();
        int last = -1;
        for(int i = 0; i< count; ++i) {
            if(arr[i] > last + 1)
                sb.append(arr[i] - last - 1);
            sb.append(target.charAt(arr[i]));
            last = arr[i];
        }
        if(last != target.length() - 1) 
            sb.append(target.length() - last - 1);

        nextPer(arr, target.length());
        return sb.toString();
    }
    
    void nextPer(int [] arr, int n) {
        for(int i = count -1; i>=0; i--) {
            if(arr[i] != n - count  + i) {
                arr[i] ++;
                for(int j = i+1; j<count; ++j) 
                    arr[j] = arr[j-1] + 1;
                return ;
            }
        }
        count ++;
        for(int i=0; i<count && i< n; ++i) 
            arr[i] = i;
    }
    
    boolean valid(String word, String abbr) {
        int k = 0;
        int count = 0;
        for(int i=0; i<abbr.length(); ++i) {
            if(k>= word.length())   return false;
            if(abbr.charAt(i) >= '0' && abbr.charAt(i) <= '9') {
                if(count == 0 && abbr.charAt(i) =='0')  return false;
                count = count * 10 + abbr.charAt(i) - '0';
            }
            else if(Character.isLetter(abbr.charAt(i))) {
                if(count > 0 ) {
                    k = k+ count;
                    count =0;
                }
                if(k>= word.length())
                        return false;
                if(abbr.charAt(i) != word.charAt(k++))    return false;
            }
        }
        
        if(count != word.length() - k)  return false; 
        return true;
    }
}
