/**
Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18):
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].

*/

public class ZigzagIterator {
    List<Integer> l1;
    List<Integer> l2;
    int p1;
    int p2;
    boolean left = true;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        l1 = v1;
        p1 = 0;
        l2 = v2;
        p2 = 0;
        if(l1 == null)
            l1 = new ArrayList<>();
        if(l2 == null)
            l2 = new ArrayList<>();
    }

    public int next() {
        int ret ;
        if(p1 >= l1.size())
            return l2.get(p2++);
        if(p2 >= l2.size())
            return l1.get(p1++);
            
        if(!left) {
            ret = l2.get(p2++);
        } else {
            ret = l1.get(p1++);
        }
        left = !left;
        return ret;
    }

    public boolean hasNext() {
        return p1 < l1.size() || p2 < l2.size(); 
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
