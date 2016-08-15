题意：

Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.

解题思路：这只一个flag标志位就可以了。

代码：

复制代码
class Solution:
    # @param digits, a list of integer digits
    # @return a list of integer digits
    def plusOne(self, digits):
        flag = 1
        for i in range(len(digits)-1, -1, -1):
            if digits[i] + flag == 10:
                digits[i] = 0
                flag = 1
            else:
                digits[i] = digits[i] + flag
                flag = 0
        
        if flag == 1:
            digits.insert(0, 1)
        return digits



public class Solution {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i] += 1;
                return digits;
            }
            else digits[i] = 0;         // careful need to specify this [8,9,9]
        }
        int[] res = new int[digits.length+1];
        res[0] = 1;
        return res;
    }
}



from code ganker:

这是一道比较简单的题目，对一个数组进行加一操作。但是可不要小看这个题，这个题被称为“Google最喜欢的题”，因为在google面试中出现的频率非常高。

我们先说说这道题的解法。思路是维护一个进位，对每一位进行加一，然后判断进位，如果有继续到下一位，否则就可以返回了，因为前面不需要计算了。

有一个小细节就是如果到了最高位进位仍然存在，那么我们必须重新new一个数组，然后把第一个为赋成1（因为只是加一操作，其余位一定是0，否则不会进最高位）。

因为只需要一次扫描，所以算法复杂度是O(n)，n是数组的长度。而空间上，一般情况是O(1)，但是如果数是全9，那么是最坏情况，需要O(n)的额外空间。代码如下：

public int[] plusOne(int[] digits) {
    if(digits == null || digits.length==0)
        return digits;
    int carry = 1;
    for(int i=digits.length-1;i>=0;i--)
    {
        int digit = (digits[i]+carry)%10; 
        carry = (digits[i]+carry)/10;
        digits[i] = digit;
        if(carry==0)
            return digits;    
    }
    int [] res = new int[digits.length+1];    
    res[0] = 1;
    return res;
}

我自己在Google的电面中就遇到了这道题，我觉得为什么Google喜欢的原因是后续可以问一些比较基本的扩展问题，比如可以扩展这个到两个数组相加，或者问一些OO设计，

假设现在要设计一个BigInteger类，那么需要什么构造函数，然后用什么数据结构好，用数组和链表各有什么优劣势。这些问题虽然不是很难，但是可以考到一些基本的理解，

所以平时准备有机会还是可以多想想哈。



