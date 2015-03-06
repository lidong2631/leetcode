class Solution:
    # @param a, a string
    # @param b, a string
    # @return a string
    def addBinary(self, a, b):
        indexA = len(a)-1; indexB = len(b)-1
        carry = 0
        res = ''
        while indexA>=0 and indexB>=0:              #如果a，b都还有数字
            num = carry + int(a[indexA]) + int(b[indexB])       #计算和
            carry = num/2                                       #计算进位
            num = num % 2                                       #计算和的数字
            res = str(num) + res                                #将新计算的数加到原有res字符串中
            indexA-=1; indexB-=1                                #分别递减a，b的index
        while indexA>=0:            #如果a还有剩余
            num = carry + int(a[indexA])
            carry = num/2
            num = num % 2
            res = str(num) + res
            indexA-=1
        while indexB>=0:            #如果b还有剩余
            num = carry + int(b[indexB])
            carry = num/2
            num = num % 2
            res = str(num) + res
            indexB-=1
        if carry:               #最后如果最后还有进位
            res = '1' + res
        return res

Note: 基本二进制加法实现题 要熟记套路




题意：

Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".

解题思路：提供两种实现方式吧。

代码一：

复制代码
class Solution:
    # @param a, a string
    # @param b, a string
    # @return a string
    def addBinary(self, a, b):
        aIndex = len(a)-1; bIndex = len(b)-1
        flag = 0
        s = ''
        while aIndex>=0 and bIndex>=0:
            num = int(a[aIndex])+int(b[bIndex])+flag
            flag = num/2; num %= 2
            s = str(num) + s
            aIndex -= 1; bIndex -= 1
        while aIndex>=0:
            num = int(a[aIndex])+flag
            flag = num/2; num %= 2
            s = str(num) + s
            aIndex -= 1
        while bIndex>=0:
            num = int(b[bIndex])+flag
            flag = num/2; num %= 2
            s = str(num) + s
            bIndex -= 1
        if flag == 1:
            s = '1' + s
        return s





public class Solution {
    public String addBinary(String a, String b) {
        if(a==null || a.length()==0)
            return b;
        if(b==null || b.length()==0)
            return a;
        int carry=0;
        StringBuilder sb = new StringBuilder();
        int indexA = a.length()-1; int indexB = b.length()-1;
        while(indexA>=0 && indexB>=0)
        {
            int num = (int)(a.charAt(indexA)-'0'+b.charAt(indexB)-'0'+carry);
            carry = num/2;              //
            num = num%2;                //
            sb.append(num);             //
            indexA--; indexB--;
        }
        while(indexA>=0)
        {
            int num = (int)(a.charAt(indexA)-'0'+carry);
            carry = num/2;
            num = num%2;
            sb.append(num);
            indexA--;
        }
        while(indexB>=0)
        {
            int num = (int)(b.charAt(indexB)-'0'+carry);
            carry = num/2;
            num = num%2;
            sb.append(num);
            indexB--;
        }
        if(carry==1)
            sb.append(1);
        return sb.reverse().toString();         //
    }
}

Note: 算法没什么好说的 主要是实现细节几个地方注意 仿python版写的 code ganker版差不多








from code ganker:

这道题跟Add Two Numbers很类似，代码结构很接近。从低位开始，一直相加并且维护进位。和Add Two Numbers的区别是这个题目低位在后面，所以要从string的尾部往前加。

时间复杂度是O(m+n)，m和n分别是两个字符串的长度，空间复杂度是结果的长度O(max(m,n))。代码如下：

public String addBinary(String a, String b) {
    if(a==null || a.length()==0)
        return b;
    if(b==null || b.length()==0)
        return a;
    int i=a.length()-1;
    int j=b.length()-1;
    int carry = 0;
    StringBuilder res = new StringBuilder();
    while(i>=0&&j>=0)
    {
        int digit = (int)(a.charAt(i)-'0'+b.charAt(j)-'0')+carry;
        carry = digit/2;
        digit %= 2;
        res.append(digit);
        i--;
        j--;
    }
    while(i>=0)
    {
        int digit = (int)(a.charAt(i)-'0')+carry;
        carry = digit/2;
        digit %= 2;
        res.append(digit);
        i--;
    }
    while(j>=0)
    {
        int digit = (int)(b.charAt(j)-'0')+carry;
        carry = digit/2;
        digit %= 2;
        res.append(digit);
        j--;
    }      
    if(carry>0)
    {
        res.append(carry);
    }
    return res.reverse().toString();
}

最后有一个小细节要注意一下，就是我们维护的res是把低位放在前面，为了满足结果最后要进行一次reverse。



