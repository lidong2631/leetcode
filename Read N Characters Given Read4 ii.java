/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    private char buffer[] = new char[4];  //buffer即是每次调用read4读取的缓存
    private int offset = 0, remainSize = 0; //offset记录下一次copy中原缓存中开始的index remainSize表示缓存中的实际字节数
    
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int readBytes = 0;
        boolean eof = false;
        while(!eof && readBytes<n) {  //跟i一样循环
            if(remainSize==0) {       //要先判断remainSize是否为0 是说明缓存中没有剩余的未读取字节 可以调用read4读取新的字节进缓存 否则要先处理之前剩余的字节
                remainSize = read4(buffer);   //将调用read4读取到的字节数存入remainSize中
                eof = remainSize<4;   //如果读取到的字节数小于4 说明已到文件末尾 要置eof为true
            }
            int bytes = Math.min(n-readBytes, remainSize);  //当前读取的字节 3种情况可以是 1之前剩余的字节 2从read4读取到的字节 3 n-readBytes
            System.arraycopy(buffer, offset, buf, readBytes, bytes);  //copy字节到目的缓存 注意原缓存从offset位置开始
            offset = (offset+bytes)%4;  //每次更新offset 一般应是0 如果文件已读到末尾或读到n个字节处就不再是0
            remainSize-=bytes;  //更新remainSize和readBytes
            readBytes+=bytes;
        }
        return readBytes;
    }
}

因为可以调用多次read方法 所以如果上一次读取时n-readBytes小于remainSize的大小 就会有剩余字节在remainSize中 下一次再调用read时要先把remainSize中的字节

清空掉再继续调用read4读取 这里就会涉及到保存状态的事 用remainSize表示上一次call是否有剩余字节 offset新一次的call应该从那个位置开始