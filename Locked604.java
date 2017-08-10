/*
Design and implement a data structure for a compressed string iterator. It should support the following operations: next and hasNext.

The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter existing in the original uncompressed string.

next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
hasNext() - Judge whether there is any letter needs to be uncompressed.

Note:
Please remember to RESET your class variables declared in StringIterator, as static/class variables are persisted across multiple test cases. Please see here for more details.

Example:

StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

iterator.next(); // return 'L'
iterator.next(); // return 'e'
iterator.next(); // return 'e'
iterator.next(); // return 't'
iterator.next(); // return 'C'
iterator.next(); // return 'o'
iterator.next(); // return 'd'
iterator.hasNext(); // return true
iterator.next(); // return 'e'
iterator.hasNext(); // return false
iterator.next(); // return ' '

*/
public class StringIterator {

    List<Character> ch;
    List<Integer> count;
    int index;
    public StringIterator(String com) {
        ch = new ArrayList<>(com.length()/2);
        count = new ArrayList<>(com.length()/2);
        int cc = 0;
        for(int i = 0; i < com.length(); ++i) {
            char cur = com.charAt(i);
            if(cur >= 'a' && cur<='z' || cur>='A' && cur <='Z') {
                if(i > 0) 
                    count.add(cc);
                cc= 0;
                ch.add(cur);
            } else {
                cc = cc * 10 + cur - '0'; 
            }
        }
        count.add(cc);
        index=0;
    }
    
    public char next() {
        if(!hasNext())  return ' ';
        char ret = ch.get(index);
        int old = count.get(index);
        old --;
        count.set(index, old);
        if(old == 0)    index++;
        return ret;
    }
    
    public boolean hasNext() {
        return index != ch.size();
    }
}

/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator obj = new StringIterator(compressedString);
 * char param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
