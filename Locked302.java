/*
An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.
*/
public class Solution {
    int bxs(char[][] image, int start, int end, boolean ve, boolean x) {
        while(start < end) {
            int mid = start + (end - start) /2;
            boolean found = false;
            if(!x) {
                for(int i = 0; i<image.length; ++i) {
                    if(image[i][mid] == '1') {
                        found = true;
                        break;   
                    }
                }
            } else {
                for(int i = 0; i<image[0].length; ++i) {
                    if(image[mid][i] == '1') {
                        found = true;
                        break;   
                    }
                }
            }
            if(found) {
                if(ve)
                    end = mid;
                else 
                    start = mid + 1;
            } else {
                if(ve)
                    start = mid + 1;
                else 
                    end = mid;
            }
        }
        if(ve)
            return start;
        return start - 1;
    }
    
    public int minArea(char[][] image, int x, int y) {
        int xs = bxs(image, 0, x, true, true);
        int xl = bxs(image, x, image.length, false, true);
        int ys = bxs(image, 0, y, true, false);
        int yl = bxs(image, y, image[0].length, false, false);
        return (xl- xs + 1) * (yl - ys + 1);
    }
}
