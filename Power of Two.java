public class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n==0 || n==Integer.MIN_VALUE)    // careful
            return false;
        return (n&(n-1))==0;
    }
}

cc150原题 5.4 利用位运算 注意判断下是不是最小整数
http://www.geeksforgeeks.org/write-one-line-c-function-to-find-whether-a-no-is-power-of-two/


public class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n==0)
            return false;
        while(n!=1) {
            if(n%2==1)
                return false;
            n/=2;
        }
        return true;
    }
}

超时！