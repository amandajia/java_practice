public class Solution {
    public boolean splitArray(int[] on) {
        if(on == null || on.length < 7) return false;
        List<Integer> to = new ArrayList<>();
        for(int o : on) {
            if(o != 0 || to.size() == 0)  to.add(o);
            else if(to.get(to.size() - 1) != 0) {
                to.add(o);
            }
        }
        //System.out.println(to);
        Integer [] nums = to.toArray(new Integer[0]);
        int start = 0;
        int target = 0;
        int [] sums = new int [nums.length];
        sums[0] = nums[0];
        for(int i = 1; i< nums.length; ++i) {
            sums[i] = nums[i] + sums[i - 1];
        }
        for(int i = 0; i < nums.length - 6; ++i) {
            target += nums[i];
            int first = find(sums, target, i + 1, i + 1);
            if( first < 0 || first == nums.length)   continue;
            Stack<Integer> sol = new Stack();
            sol.add(first);
            while(sol.size() > 0) {
                start = sol.peek();
                while(sol.size() < 3) {
                    start = find(sums, target, start, start);
                    if(start > 0) {
                        sol.add(start);
                    } else {
                        break;
                    }
                }    
                if(sol.size() == 3 && sol.peek() == nums.length) {
                    //System.out.println(target + " : " + sol.pop() + " : " + sol.pop() + " | " +sol.pop());
                    return true;
                }
                Integer nextTry = null;
                while(!sol.isEmpty()) {
                    int last = sol.pop();
                    int sp = sol.isEmpty()? i + 1 : sol.peek();
                    nextTry = find(sums, target, sp, last);
                    if(nextTry != -1) {
                        sol.push(nextTry);
                        break;
                    }
                }
            }
        }
        return false;
    }
    int find(int [] sums, int target, int start, int least) {
        if(least == start)  least ++;
        for(int i = least; i < sums.length; ++i) {
            if(sums[i] - sums[start] == target) return i + 1;
        }
        return -1;
    }  
    
}
