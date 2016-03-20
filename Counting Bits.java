public class Solution {
    public int[] countBits(int num) {
        int[] res = new int[num+1];
        for(int i=0; i<=num; i++) {
            res[i] = res[i>>1] + i%2;
        }
        return res;
    }
}

O(n)

f[i] = f[i/2] + i%2

  0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15
0000 0001 0010 0011 0100 0101 0110 0111 1000 1001 1010 1011 1100 1101 1110 1111
  0    1    1    2    1    2    2    3    1    2    2    3    2    3    3    4

ex 1010 1011 对应 0101 当i是偶数后面加0 i是奇数后面加1 所以是f[i] = f[i/2] + i%2

https://leetcode.com/discuss/92609/three-line-java-solution