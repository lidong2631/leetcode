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



public boolean isPowerOfThree(int n) {
    return Integer.toString(n, 3).matches("10*");
}

Integer.toString(2, 3) = 2
Integer.toString(5, 3) = 12

https://leetcode.com/discuss/78708/ternary-number-solution