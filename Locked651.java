/**
Imagine you have a special keyboard with the following keys:

Key 1: (A): Prints one 'A' on screen.

Key 2: (Ctrl-A): Select the whole screen.

Key 3: (Ctrl-C): Copy selection to buffer.

Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.

Now, you can only press the keyboard for N times (with the above four keys), find out the maximum numbers of 'A' you can print on screen.

Example 1:
Input: N = 3
Output: 3
Explanation: 
We can at most get 3 A's on screen by pressing following key sequence:
A, A, A
Example 2:
Input: N = 7
Output: 9
Explanation: 
We can at most get 9 A's on screen by pressing following key sequence:
A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
Note:
1 <= N <= 50
Answers will be in the range of 32-bit signed integer.

*/
public class Solution {
    static class Choice {
        int onboard;
        int buffer;
        public Choice (int o, int b) {
            onboard = o; buffer = b;
        }
    }
    public int maxA(int N) {
        if(N<=6)    return N;
        int [] ret = new int[N+1];
        for(int i = 0; i<=6; ++i)   ret[i] = i;
        PriorityQueue<Choice> pq = new PriorityQueue<>((c1, c2)-> (c2.onboard + c2.buffer == c1.onboard + c1.buffer)?
                                                       c2.buffer - c1.buffer :
                                                      (c2.onboard + c2.buffer - (c1.onboard + c1.buffer)));
        pq.add(new Choice(6, 3));
        for(int i = 7; i<=N; ++i) {
            List<Choice> next = new ArrayList<>();
            Choice best = pq.poll();
            ret[i] = best.onboard + best.buffer;
            best.onboard += best.buffer;
            int thres = best.buffer;
            next.add(best);
            Choice promise = new Choice(2 * ret[i - 3] , ret[i - 3]);
            next.add(promise);
            while(!pq.isEmpty()) {
                Choice cur = pq.poll();
                cur.onboard += cur.buffer;
                if (promise.onboard == cur.onboard) {
                    break;
                }
                if(cur.buffer > thres) {
                    thres = cur.buffer;
                    next.add(cur);
                }
            }
            pq.clear();
            for(Choice c : next)    pq.add(c);
        }
        return ret[N];
    }
}
