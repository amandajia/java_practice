/*
Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

Follow up:
As an added challenge, try to code it using only iterators in C++ or iterators in Java.
*/

public class Vector2D implements Iterator<Integer> {
    List<List<Integer>> v;
    int i;
    int j;
    public Vector2D(List<List<Integer>> vec2d) {
        v = vec2d;
        i = 0;
        j = 0;
        while(i< v.size() && 0 == v.get(i).size()) {
            i ++ ;
        }
    }

    @Override
    public Integer next() {
        Integer ret = v.get(i).get(j);
        j++;
        while(i< v.size() && j == v.get(i).size()) {
            i ++ ;
            j = 0;
        }
        return ret;
    }

    @Override
    public boolean hasNext() {
        return (i < v.size() && j < v.get(i).size());
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
