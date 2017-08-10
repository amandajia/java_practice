/*
Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
*/
public class Solution {
    String build(int start, int end) {
        if(start == end) return new Integer(start).toString();
        StringBuilder sb = new StringBuilder();
        sb.append(start);
        sb.append("->");
        sb.append(end);
        return sb.toString();
    }
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ret = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            ret.add(build(lower, upper));
            return ret;
        }
        int start = lower;
        for(int i = 0; i < nums.length; ++i) {
            if(start > nums[i]) continue;
            if(nums[i] != start) {//nums[i] > start;
                ret.add(build(start, nums[i] - 1));
            } 
            start = nums[i] + 1;
        }
        boolean overflow = (nums[nums.length - 1] == Integer.MAX_VALUE);
        if(!overflow && start <= upper)
            ret.add(build(start, upper));
        return ret;
    }
}
