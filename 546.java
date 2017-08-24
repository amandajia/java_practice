public class Solution {
    public int removeBoxes(int[] boxes) {
        if(boxes == null|| boxes.length == 0)   return 0;
        if(boxes.length == 1)   return 1;
        Map<Integer, Integer> limit = new HashMap<>();
        for(int b: boxes) {
            limit.putIfAbsent(b, 0);
            limit.put(b, limit.get(b) + 1);
        }
        int l = boxes.length;
        int [][][] box = new int[l + 1][l + 1][l];
        for(int inter = 0; inter < l; ++inter) {
            for(int i = 0; i + inter < l; ++i) {
                int cur = boxes[i];
                int count = 0;
                while(count <= inter && boxes[i + count] == cur)  count++;
                int klimit = limit.get(cur) - count;
                for(int k = 0; k <= klimit ; ++k) {
                    box[i][i + inter][k] = (k + count) * (k + count) + box[i + count][i + inter][0];
                    for(int m = i + count; m <= i + inter; ++m) {
                        if(boxes[m] == cur) {
                            box[i][i + inter][k] = Math.max(box[i][i + inter][k], 
                                box[m][i+inter][k + count] + box[i+ count][m - 1][0]);
                        }
                    }
                }
            }
            
        }
        return box[0][l - 1][0];
    }
}
