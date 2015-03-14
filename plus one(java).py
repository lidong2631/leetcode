class Solution:
    # @param digits, a list of integer digits
    # @return a list of integer digits
    def plusOne(self, digits):
        carry = 1
        for i in range(len(digits)-1, -1, -1):      #加1 分有进位和无进位处理
            if digits[i] + carry == 10:
                digits[i] = 0
                carry = 1
            else:
                digits[i] = digits[i] + carry
                break
        if digits[0] == 0:                          #如果最大位有进位 扩展list
            digits.insert(0, 1)
        return digits






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




From cleanCode

Q: Could the number be negative
A: No. Assume it is a non-negative number

Q: How are the digits ordered in the list? For example, is the number 12 represented by [1,2] or [2,1]?
A: The digits are stored such that the most significant digit is at the head of the list

Q: Could the number contain leading zeros, such as [0,0,1]?
A: No


public void plusOne(List<Integer> digits) {
    for(int i=digits.size()-1, i>=0; i--) {
        int digit = digits.get(i);
        if(digit<9) {
            digits.set(i, digit+1);
            return;
        }
        else {
            digits.set(i, 0);
        }
    }
    digits.add(0);
    digits.set(i, 1);
}





public class Solution {
    public int[] plusOne(int[] digits) {
        if(digits==null || digits.length==0)
            return digits;
        int carry = 1;              //carry一开始是1 代表要加的1
        int digit = 0;
        for(int i=digits.length-1; i>=0; i--)
        {
            digit = (digits[i]+carry)%10;   ////digit临时存储digits[i]因为它后面会被更新
            carry = (digits[i]+carry)/10;
            digits[i] = digit;
            if(carry==0)      //判读carry等于0要放后面 不然程序会先跳出for循环去new新的数组而不会返回digits  case [0], 1
                return digits;
        }
        int[] res = new int[digits.length+1];       //否则最坏情况 new一个新数组 记得长度为digis.length+1
        res[0] = 1;
        return res;
    }
}

Note: 这题不难 但有几个小细节要注意一下 另外想想扩展如两个数组相加





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



