public class Solution {
    public boolean isPowerOfFour(int num) {
        int mask = 0x55555554;                  // 0101...01010100
        return (num == 1) || ((num & (num-1)) == 0) && ((mask & num) != 0);
    }
}

same as Power of Two but also use mask to eliminate those power of two but not four


Another workaround:

public boolean isPowerOfFour(int num) {
    return Integer.toString(num, 4).matches("10*");
}

This solution could be used as any radix

Integer.toString(i, radix)

Integer.toString(2, 4) = 2
Integer.toString(6, 4) = 12  1表示一个4的一次方 2表示2个4的0次方