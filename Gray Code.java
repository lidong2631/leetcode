public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<Integer>();
        res.add(0);
        for(int i=1; i<=n; i++) {
            int size = res.size();
            for(int j=size-1; j>=0; j--)
                res.add(res.get(j)+(1<<(i-1)));
        }
        return res;
    }
}

O(2^n) O(2^n)

//n=0  -> 0
//n=1  -> 0, 1
//n=2  -> (00,  01),  (10,  11)
//n=3  -> (000, 001, 010, 011), (111, 110, 101, 100)

from 2 to 3
(00, 01), (10, 11) -> add 0 (000, 001, 010, 011) -> reverse (011, 010, 001, 000) -> add 1 to 1st digit (111, 110, 101, 100)
(000, 001, 010, 011), (111, 110, 101, 100)

由n-1到n 首先在n-1位gray code前填0作为前半部分 然后翻转n-1位 gray code再在前面加1既是后半部分
首先因为是倒序过来，所以中间两个元素除去第一位其他都是一样的（因为原来最后一个，现在倒序过来就是第一个），而他们第一位分别是0和1，满足格雷码的要求。
而剩下的元素因为我们是n-1位的格雷码倒序排列，所以两两都是满足要求的，加上前面都一样的位1，仍然满足条件

我们只需要做一次位数的循环，每次根据上面结果构造当前位数的结果即可。算法复杂度是O(2+2^2+...+2^n-1)=O(2^n)，所以是指数量级的，因为是结果数量无法避免。
空间复杂度则是结果的大小，也是O(2^n)

http://blog.csdn.net/linhuanmars/article/details/24511221
https://leetcode.com/discuss/62988/1ms-java-solution-with-explaination


The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.