//https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    int SIZE = 4;
    int left = 0;
    int end = SIZE;
    char [] line = new char[SIZE];
    public int read(char[] buf, int n) {
        if(end < 0) return 0;
        int count = 0;
        while(left!= 0 && left < end && count < n) {
            buf[count++] = line[left++];
        }
        if(end != SIZE && left == end) {
            end = -1;
            return count;
        }
        while(count < n) {
            int num = read4(line);
            int j = 0;
            for(; j < num && count < n; ++ j) {
                buf[count++] = line[j];
            }
            left = j;
            if(num != SIZE)   { 
                end = num;
                break;
            }
        }
        return count;
    }
}
