public class Solution {
    public int[] countBits(int num) {
        int[] res = new int[num+1];
        for(int i=0; i<=num; i++) {
            res[i] = res[i>>1] + i%2;
        }
        return res;
    }
}

Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1 in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
Hint:

You should make use of what you have produced already.
Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on. And try to generate new range from previous.
Or does the odd/even status of the number help you in calculating the number of 1s?


O(n)

f[i] = f[i/2] + i%2

  0    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15
0000 0001 0010 0011 0100 0101 0110 0111 1000 1001 1010 1011 1100 1101 1110 1111
  0    1    1    2    1    2    2    3    1    2    2    3    2    3    3    4

ex 1010 1011 对应 0101 当i是偶数后面加0 i是奇数后面加1 所以是f[i] = f[i/2] + i%2

https://leetcode.com/discuss/92609/three-line-java-solution