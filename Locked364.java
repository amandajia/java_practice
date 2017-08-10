/*
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:
Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

Example 2:
Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)


*/

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
    public class Level {
        public int l;
        NestedInteger i;
        public Level(NestedInteger ii, int ll) { i = ii; l = ll;}
    }
    public int depthSumInverse(List<NestedInteger> nestedList) {
        Stack<Level> st = new Stack();
        for(NestedInteger i: nestedList) {
            st.push(new Level(i, 1));
        }
        List<int[]> res = new ArrayList<>();
        int level = 1;
        while(!st.isEmpty()) {
            Level cur = st.pop();
            if(cur.l > level)
                level = cur.l;
            if(cur.i.isInteger()) {
                int[] line = new int[2];
                line[0] = cur.i.getInteger();
                line[1] = cur.l;
                res.add(line);
                continue;
            }
            for(NestedInteger i: cur.i.getList()) {
                st.push(new Level(i, cur.l + 1));
            }
        }
        int ret = 0;
        level ++;
        for(int [] i: res) {
            ret += (level - i[1]) * i[0];
        }
        return ret;
    }
}
