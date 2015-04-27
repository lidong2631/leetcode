/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        char buffer[] = new char[4];    
        int readBytes = 0;              //记录一共读取多少字节
        boolean eof = false;            //标记是否到文件结尾
        while(!eof && readBytes<n) {    // 1 没到文件结尾 2 没有读取到n个字节
            int size = read4(buffer);
            if(size<4)                  //如果小于4说明到了文件结尾
                eof = true;
            int bytes = Math.min(n-readBytes, size);    // 1 read4读到4个字节 但剩余允许读字节小于4(n-readBytes) 2 剩余允许读字节大于4 但已到文件结尾read4读取到的小于4
            System.arraycopy(buffer, 0, buf, readBytes, bytes);   //将当前读取的字节copy到目标数组中
            readBytes+=bytes;                         //更新已读取字节数
        }
        return readBytes;   //最后返回已读取字节数
    }
}

几个变量要记得
readBytes
eof
size
bytes