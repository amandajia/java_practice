/*
A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.

Note:

A transaction will be given as a tuple (x, y, z). Note that x ? y and z > 0.
Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
Example 1:

Input:
[[0,1,10], [2,0,5]]

Output:
2

Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.

Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
Example 2:

Input:
[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

Output:
1

Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.

Therefore, person #1 only need to give person #0 $4, and all debt is settled.
*/
import java.util.Map.Entry;
public class Solution {

    
    int max = Integer.MAX_VALUE;
    
    int collide(List<Integer> p, List<Integer> n, int count) {
        Collections.sort(p);
        Collections.sort(n, Collections.reverseOrder());
        int i = 0;
        int j = 0;
        while(i!= p.size() && j != n.size()) {
            if(p.get(i) > - n.get(j)) {
                j++;
            } else if(p.get(i) < - n.get(j)) {
                i++;
            } else {
                p.remove(i);
                n.remove(j);
                count++;
            }
        }
        return count;
    }
    
    void DFS(List<Integer> pos, List<Integer> neg, int cur) {
        cur = collide(pos, neg, cur);
        if(pos.size() == 0 || neg.size() ==0) {
            if(cur < max) {
                max = cur;
            }
            return ;
        }
        int last = pos.remove(pos.size() - 1);
        for(int i = 0; i< neg.size(); ++i) {
            List<Integer> nl = new ArrayList<>(neg);
            List<Integer> np = new ArrayList<>(pos);
            int n = nl.remove(i);
            int total = last + n;
            if(total > 0) {
                np.add(total);
            } else {
                nl.add(total);
            }
            DFS(np, nl, cur + 1);
        }
    }
    
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> owned = new HashMap<>();
        for(int [] t : transactions) {
            owned.putIfAbsent(t[0], 0);
            owned.putIfAbsent(t[1], 0);
            owned.put(t[0], owned.get(t[0]) - t[2]);
            owned.put(t[1], owned.get(t[1]) + t[2]);
        }
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();
        for(int v : owned.values()) {
            if(v > 0) {
                pos.add(v);
            } else {
                neg.add(v);
            }
        }
       
        DFS(pos, neg, 0); 
        return max;
    }
}
