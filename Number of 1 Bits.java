public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += (n >> i) & 1;
        }
        return count;
    }
}

最普通的解法 一位一位移动计算1的位数 一共要循环32位

但是此种方法较慢，对于0x1ffffff的判定需要循环32次才能计算出结果，超时。 
故转换思路，举例： 
n = 0x110100 n-1 = 0x110011 n&(n - 1) = 0x110000 
n = 0x110000 n-1 = 0x101111 n&(n - 1) = 0x100000 
n = 0x100000 n-1 = 0x011111 n&(n - 1) = 0x0 
看到这里已经得到了一种新的解法，n中本来有3个1，按照此种思路只需要循环3此即可求出最终结果，比第一种暴力枚举的解法要少很多次




https://leetcode.com/discuss/27609/short-code-of-c-o-m-by-time-m-is-the-count-of-1s
"n &= n - 1" is used to delete the right "1" of n. For example:

if n = 5 (101), then n-1 = 100, so n & (n-1) = 100, the right "1" is deleted;
if n = 6,(110), then n-1 = 101, so n & (n-1) = 100, the right "1" is also deleted;
and so on...
So time complexity is O(m), and m is the count of 1, also m is less than or equal to 32.


public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}

优化的解法 有几个1循环几次