public class Solution {
    public int maximumProduct(int[] nums) {
        int [] min = new int[2];
        int [] max = new int[3];
        min[0] = Integer.MAX_VALUE;
        min[1] = Integer.MAX_VALUE;
        max[0] = Integer.MIN_VALUE;
        max[1] = Integer.MIN_VALUE;
        max[2] = Integer.MIN_VALUE;
        for(int i : nums) {
            if(i <= min[0]) {
                min[1] = min[0];
                min[0] = i;
            } else if( i < min[1]) {
                min[1] = i;
            }
            if(i >= max[0]) {
                max[2] = max[1];
                max[1] = max[0];
                max[0] = i;
            } else if( i >= max[1]){
                max[2] = max[1];
                max[1] = i;
            } else if( i > max[2] ) {
                max[2] = i;
            }
        }
        
        return Math.max(max[0] * max[1] * max[2], max[0] * min[0] * min[1]);
    }
}
