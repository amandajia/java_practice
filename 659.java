public class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> seqToCount = new HashMap<>();
        for(int i : nums)   count.put(i, count.getOrDefault(i, 0) + 1);
        for(int i : nums) {
            if(count.get(i) == 0)   continue;
            else if(seqToCount.getOrDefault(i, 0) > 0) { // found and append
                seqToCount.put(i, seqToCount.getOrDefault(i, 0) - 1);
                seqToCount.put(i + 1, seqToCount.getOrDefault(i + 1, 0) + 1);
            } else if(count.getOrDefault(i + 1, 0) > 0 && count.getOrDefault(i + 2, 0) > 0) { //form seq itself
                count.put(i + 1, count.get(i + 1) - 1);
                count.put(i + 2, count.get(i + 2) - 1);
                seqToCount.put(i + 3, seqToCount.getOrDefault(i + 3, 0) + 1);
            } else { // not possible
                return false;
            }
            count.put(i, count.get(i) - 1);
        }
        return true;
    }
}
