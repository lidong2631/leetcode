public class Solution {
    public int getSum(int a, int b) {
        return b == 0 ? a : getSum(a^b, (a&b)<<1);
    }
}

https://discuss.leetcode.com/topic/49870/one-liner-with-detailed-explanation