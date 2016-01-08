public class Solution {
    public boolean isPowerOfThree(int n) {
        return n>0 && (n==1 || (n%3==0 && isPowerOfThree(n/3)));
    }
}


public boolean isPowerOfThree(int n) {
    while(n>1) {
        if(n%3!=0) return false;
        n /= 3;
    }
    return n<=0 ? false : true;
}

How to check if a integer is a power of some integer?

https://leetcode.com/discuss/78532/a-summary-of-all-solutions