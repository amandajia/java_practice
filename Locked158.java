/*
G, F 
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.


*/
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
            left = left % SIZE;
            for(; left< num && count < n; ++left) {
                buf[count++] = line[left];
            }
            if(num != SIZE)   { 
                end = num;
                break;
            }
        }
        left = left % SIZE;
        return count;
    }
}
