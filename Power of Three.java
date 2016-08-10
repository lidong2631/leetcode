public class Solution {
    public boolean isPowerOfThree(int n) {
        return n>0 && (n==1 || (n%3==0 && isPowerOfThree(n/3)));
    }
}

Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion?


public boolean isPowerOfThree(int n) {
    while(n>1) {
        if(n%3!=0) return false;
        n /= 3;
    }
    return n<=0 ? false : true;
}


public class Solution {
    public boolean isPowerOfThree(int n) {
        // 1162261467 is 3^19,  3^20 is bigger than int  
        return ( n>0 &&  1162261467%n==0);
    }
}
https://discuss.leetcode.com/topic/36150/1-line-java-solution-without-loop-recursion



How to check if a integer is a power of some integer?

https://leetcode.com/discuss/78532/a-summary-of-all-solutions



public boolean isPowerOfThree(int n) {
    return Integer.toString(n, 3).matches("10*");
}

Integer.toString(2, 3) = 2
Integer.toString(5, 3) = 12

https://leetcode.com/discuss/78708/ternary-number-solution