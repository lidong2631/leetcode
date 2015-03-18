public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int i=0; int res = 0;
        while(i<32) {
            res = (res<<1) + ((n>>i)&1);
            i++;
        }
        return res;
    }
}


我们只需要把要翻转的数从右向左一位位的取出来，然后加到新生成的数的最低位即可

java中运算符优先级 http://blog.csdn.net/xiaoli_feng/article/details/4567184