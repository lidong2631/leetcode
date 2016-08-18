/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    char[] buffer = new char[4];
    int offset = 0, remainSize = 0;
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int readBytes = 0;
        boolean eof = false;
        while (!eof && readBytes < n) {
            if (remainSize == 0) {        // careful need to check if there remain characters from last call
                remainSize = read4(buffer);
                eof = remainSize < 4;
            }
            int bytes = Math.min(n - readBytes, remainSize);
            System.arraycopy(buffer, offset, buf, readBytes, bytes);
            offset = (offset + bytes) % 4;
            remainSize -= bytes;
            readBytes += bytes;
        }
        return readBytes;
    }
}