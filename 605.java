public class Solution {
    public boolean canPlaceFlowers(int[] bed, int n) {
        if(n <= 0)  return true;
        boolean cant = false;
        for(int i =0; i< bed.length && n > 0; i++) {
            if(bed[i] == 1) {
                cant = true;
                continue;
            }
            if(cant) {
                cant = false;
                continue;
            }
            if(i != bed.length - 1 && bed[i + 1] == 1) {
                continue;
            }
            n--;
            cant = true;
        }
        if(n > 0)    return false;
        return true;
    }
}
