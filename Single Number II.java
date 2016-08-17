class Solution:
    # @param A, a list of integer
    # @return an integer
    def singleNumber(self, A):
        one = two = three = 0
        for i in range(len(A)):
            two |= A[i] & one
            one = A[i] ^ one
            three = ~(one & two)
            one &= three
            two &= three
        return one


题意：Given an array of integers, every element appears three times except for one. Find that single one.

要求：和single number一样，线性时间复杂度，不能使用额外空间。

解题思路：这道题就比较难了。也是考察位操作。网上的位操作解法看了好半天也没有得其精髓。由于序列中除了那唯一的一个数之外所有的数都是三个三个出现的。

如果把这些数都转换成二进制，那么二进制数中1的那些位会连续出现三次，我们可以利用这个思路来解题。比如：3331222转换成二进制为：11 11 11 01 10 10 10。

在第1位上，1出现了4次。第2位上，1出现了6次。那么我们把每一位上为1的个数mod 3剩下的1就是我们所求的数，比如这个例子：4 mod 3 = 1; 6 mod 3 = 0。

这样剩下的二进制位为：01。那最后所求的数就是1了。

那怎么实现这个想法呢？使用二进制模拟三进制。在连续来3个1后清0。使用两个bit位，bit1和bit2。初始状态bit1和bit2都是0，即00，在来了第一个1后，

变成了01，来了第二个1后变成了10，来了第三个1后，变成了11，然后清0为00，即：00->01->10->11，然后将11清为00，这个过程就是我们编程的思路。

比如如果输入序列为：1 1 1 1 1 1 1，则变化过程为：00->01->10->11->00->01->10->11->00->01，最后剩下的是1，也就得到了结果。

如果位数多那么以此类推，比如序列为：3 3 3 2 2 2 1。则二进制为：11 11 11 10 10 10 01。则低位为1 1 1 0 0 0 1，

变化过程为：00->01->10->11->00->00->00->00->01，所以低位剩下1。高位为：1 1 1 1 1 1 0，变化过程为：00->01->10->11->00->01->10->11->00->00，

所以高位剩下0。那么最后剩下的是01，也就是1。如果位数更多，可以以此类推。程序中的one相当于bit1，two相当于bit2。

代码：

复制代码
class Solution:
    # @param A, a list of integer
    # @return an integer
    def singleNumber(self, A):
        one = 0; two = 0; three = 0
        for i in range(len(A)):
            two |= A[i] & one              #two为1时，不管A[i]为什么，two都为1
            one = A[i] ^ one               #异或操作，都是1就进位
            three = ~(one & two)　　　　    #以下三步的意思是：如果one和two都为1时，就清0，反之则保持原来状态。
            one &= three
            two &= three
        return one
复制代码
以上是网上的精髓解法，我自己还有个弱智的解法，供参考：

复制代码
def singleNumber(self, A):
        dict = {}
        for i in range(len(A)):
            if A[i] not in dict:
                dict[A[i]] = 1
            else:
                dict[A[i]] += 1
        for word in dict:
            if dict[word] == 1:
                return word




public class Solution {
    public int singleNumber(int[] nums) {
        int[] count=  new int[32];
        for (int n : nums) {
            for (int i = 0; i < 32; i++) {
                count[i] += (n >> i) & 1;       // careful here n needs to move right
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += (count[i] % 3) << i;
        }
        return res;
    }
}



https://leetcode.com/discuss/31595/detailed-explanation-generalization-bitwise-operation-numbers
https://leetcode.com/discuss/44345/java-bit-manipulation-solution
https://leetcode.com/discuss/43377/the-simplest-solution-ever-with-clear-explanation



from code ganker:

这个题比较直接的想法是用一个HashMap对于出现的元素进行统计，key是元素，value是出现个数，如果元素出现三次，则从HashMap中移除，

最后在HashMap剩下来的元素就是我们要求的元素（因为其他元素都出现三次，有且仅有一个元素不是如此）。这样需要对数组进行一次扫描，所以时间复杂度是O(n)，

而需要一个哈希表来统计元素数量，总共有(n+2)/3个元素，所以空间复杂度是O((n+2)/3)=O(n)。这个方法非常容易实现，就不列举代码了。

在LeetCode的题目中要求我们不要用额外空间来实现，也就是O(1)空间复杂度。实现的思路是基于数组的元素是整数，我们通过统计整数的每一位来得到出现次数。

我们知道如果每个元素重复出现三次，那么每一位出现1的次数也会是3的倍数，如果我们统计完对每一位进行取余3，那么结果中就只剩下那个出现一次的元素。

总体只需要对数组进行一次线性扫描，统计完之后每一位进行取余3并且将位数字赋给结果整数，这是一个常量操作（因为整数的位数是固定32位），

所以时间复杂度是O(n)。而空间复杂度需要一个32个元素的数组，也是固定的，因而空间复杂度是O(1)。代码如下：

public int singleNumber(int[] A) {
    int[] digits = new int[32];
    for(int i=0;i<32;i++)
    {
        for(int j=0;j<A.length;j++)
        {
            digits[i] += (A[j]>>i)&1;
        }
    }
    int res = 0;
    for(int i=0;i<32;i++)
    {
        res += (digits[i]%3)<<i;
    }
    return res;
}

这个题目主要是对整数数组的操作，用到的位统计是整数中经常使用的技巧，利用位的固定性来省去一定的时间或者空间复杂度


