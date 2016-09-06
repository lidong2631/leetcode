public class Solution {
    public int getSum(int a, int b) {
        return b == 0 ? a : getSum(a^b, (a&b)<<1);
    }
}

public int getSum(int a, int b) {
    if(b == 0) return a;
    int carry = (a & b) << 1;
    int sum = a ^ b;
    return getSum(sum, carry);
}

https://discuss.leetcode.com/topic/49870/one-liner-with-detailed-explanation