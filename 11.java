public class Solution {
    public int maxArea(int[] height) {
        int ret = 0;
        if (height==null || height.length ==0 ) return ret;
        List<Integer> locals = new ArrayList<>();
        locals.add(0);
        int localMax = height[0];
        for (int i = 0; i< height.length; ++i) {
            if (height[i] > localMax) {
                localMax = height[i];
                locals.add(i);
            }
        }
        List<Integer> locals2 = new ArrayList<>();
        locals2.add(height.length - 1);
        localMax = height[height.length - 1];
        for (int i = height.length -1 ; i >= 0; --i) {
            if (height[i] > localMax) {
                localMax = height[i];
                locals2.add(i);
            }
        }
        
        for(int i =0 ; i< locals.size(); ++i) {
            for (int j =0; j< locals2.size(); ++j) {
                int indexi =  locals.get(i);
                int indexj = locals2.get(j);
                if ( indexj< indexi) break;
                if ((indexj - indexi) * Math.min(height[indexi], height[indexj]) > ret) {
                    ret = (indexj - indexi) * Math.min(height[indexi], height[indexj]);
                } 
            }
        }
        
        
        return ret;
        
    }
}
