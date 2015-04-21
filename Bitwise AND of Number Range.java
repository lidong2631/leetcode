public class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int mask = 0xFFFFFFFF;
        while((m&mask)!=(n&mask)) {
            mask<<=1;
        }
        return mask&m;
    }
}

最后的数是该数字范围内所有的数的左边共同1的部分
http://www.cnblogs.com/grandyang/p/4431646.html
时间O(1) 空间O(1)