public class Solution {
    public int getSum(int a, int b) {
        if (b == 0) return a;
        int sum = 0, carry = 0;
        sum = a ^ b;
        carry += (a & b) << 1;
        return getSum(sum, carry);
    }
}

https://discuss.leetcode.com/category/455/sum-of-two-integers