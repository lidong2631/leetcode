class Solution:
    # @param num1, a string
    # @param num2, a string
    # @return a string
    def multiply(self, num1, num2):
        if num1 == '0' or num2 == '0':			#这里排除乘数为0的情况 在19行就不用用while 直接用if即可
            return '0'
        num1 = num1[::-1]; num2 = num2[::-1]					#将两个string reverse方便运算 由于复杂度是O((m+n)^2) 这个复杂度为O(m+n)的操作不会影响总复杂度
        maxLen = len(num1) + len(num2); tmp = [0 for i in range(maxLen)]		#积的最大长度为两个乘数长度和 有进位是这个 没进位会是长度和－1 所以这个是积的最大长度了 tmp存放积的每一位的结果
        for i in range(len(num1)):				#双循环算不带进位积的每一位数 看图理解 看下面leetcodenotes
            for j in range(len(num2)):
                tmp[i+j] += int(num1[i]) * int(num2[j])		#这里将对应为的结果都加到一起
        res = []
        for i in range(maxLen):			#循环 积每一位数字为tmp[i]跟10取余 进位为tmp[i]/10 每次将digit转字符后讲到res头位
            digit = tmp[i] % 10; carry = tmp[i] / 10
            res.insert(0, str(digit))
            if i < maxLen - 1:		#将进位加到tmp下一位中
                tmp[i+1] += carry
        if res[0] == '0':				#如果最后头位为'0' 类似1*1这种情况 就将首位'0'去掉
            #del res[0]					这里用del 或 remove()删除都可以
            res.remove('0')
        return ''.join(res)

Note: 这题看了半天才理解。。要好好理解乘法的规则 看照片




题意：

Given two numbers represented as strings, return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.

解题思路：两个非负数字字符串的相乘。其实就是大数乘法。算法的关键是要先将两个字符串翻转过来，然后按位进行相乘，相乘后的数不要着急进位，而是存储在一个数组里面，

然后将数组中的数对10进行求余（%），就是这一位的数，然后除以10，即/10，就是进位的数。注意最后要将相乘后的字符串前面的0去掉。

代码：

复制代码
class Solution:
    # @param num1, a string
    # @param num2, a string
    # @return a string
    def multiply(self, num1, num2):
        num1 = num1[::-1]; num2 = num2[::-1]
        arr = [0 for i in range(len(num1)+len(num2))]
        for i in range(len(num1)):
            for j in range(len(num2)):
                arr[i+j] += int(num1[i]) * int(num2[j])
        ans = []
        for i in range(len(arr)):
            digit = arr[i] % 10
            carry = arr[i] / 10
            if i < len(arr)-1:
                arr[i+1] += carry
            ans.insert(0, str(digit))
        while ans[0] == '0' and len(ans) > 1:		#它这里用while逐一去0是为了应付 933 * 0 这种情况会得到 000 我的程序前面已经排除num1或num2是0的情况用if就可以了
            del ans[0]
        return ''.join(ans)





from http://leetcodenotes.wordpress.com/2013/10/20/leetcode-multiply-strings-%E5%A4%A7%E6%95%B4%E6%95%B0%E7%9A%84%E5%AD%97%E7%AC%A6%E4%B8%B2%E4%B9%98%E6%B3%95/

这哥们讲的很清楚

这个题第二遍做code就写的挺完美的，高兴～～几个要点：
直接乘会溢出，所以每次都要两个single digit相乘，最大81，不会溢出。
比如385 * 97, 就是个位=5 * 7，十位=8 * 7 + 5 * 9 ，百位=3 * 7 + 8 * 9 …
可以每一位用一个Int表示，存在一个int[]里面。
这个数组最大长度是num1.len + num2.len，比如99 * 99，最大不会超过10000，所以4位就够了。
这种个位在后面的，不好做（10的0次方，可惜对应位的数组index不是0而是n-1），
所以干脆先把string reverse了代码就清晰好多。
最后结果前面的0要清掉。
public String multiply(String num1, String num2) {
    num1 = new StringBuilder(num1).reverse().toString();
    num2 = new StringBuilder(num2).reverse().toString();
    // even 99 * 99 is < 10000, so maximaly 4 digits
    int[] d = new int[num1.length() + num2.length()];
    for (int i = 0; i < num1.length(); i++) {
        int a = num1.charAt(i) - '0';
        for (int j = 0; j < num2.length(); j++) {
            int b = num2.charAt(j) - '0';
            d[i + j] += a * b;
        }
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < d.length; i++) {
        int digit = d[i] % 10;
        int carry = d[i] / 10;
        sb.insert(0, digit);
        if (i < d.length - 1)
            d[i + 1] += carry;
        }
    //trim starting zeros
    while (sb.length() > 0 && sb.charAt(0) == '0') {
        sb.deleteCharAt(0);
    }
    return sb.length() == 0 ? "0" : sb.toString();
}




public class Solution {
    public String multiply(String num1, String num2) {
        if(num1==null || num2==null || num1.length()==0 || num2.length()==0)
            return "";
        if(num1.charAt(0)=='0' || num2.charAt(0)=='0')
            return "0";
        StringBuilder res = new StringBuilder();
        int num = 0;
        for(int i=num1.length()+num2.length(); i>0; i--) {
            for(int j=Math.min(i-1, num1.length()); j>0; j--) { //每次比较i-1和第一个数的长度 决定从第一个数的第几位开始算
                if(i-j<=num2.length())  //判断第二个数是否还能右移
                    num+=(int)(num1.charAt(j-1)-'0')*(int)(num2.charAt(i-j-1)-'0');
            }
            if(i!=1 || num!=0)  //最高一位是不是0
                res.append(num%10);
            num/=10;
        }
        return res.reverse().toString();    //最后要进行一次reverse，因为是一个O(m+n)的操作，不会影响算法复杂度
    }
}

i从右往左扫
0 0 0 1 2 1
0 0 0 1 2 1

时间O((m+n)*n) ??



from code ganker:

这道题属于数值操作的题目，其实更多地是考察乘法运算的本质。基本思路是和加法运算还是近似的，只是进位和结果长度复杂一些。我们仍然是从低位到高位对每一位进行计算，

假设第一个数长度是n，第二个数长度是m，我们知道结果长度为m+n或者m+n-1（没有进位的情况）。对于某一位i，要计算这个位上的数字，我们需要对所有能组合出这一位结果的位进行乘法，

即第1位和第i-1位，第2位和第i-2位，... ，然后累加起来，最后我们取个位上的数值，然后剩下的作为进位放到下一轮循环中。这个算法两层循环，每层循环次数是O(m+n)，

所以时间复杂度是O((m+n)^2)。算法中不需要额外空间，只需要维护一个进位变量即可，所以空间复杂度是O(1)。代码如下：

public String multiply(String num1, String num2) {
    if(num1 == null || num2 == null || num1.length()==0 || num2.length()==0)
        return "";
    if(num1.charAt(0)=='0')
        return "0";
    if(num2.charAt(0)=='0')
        return "0";
    StringBuilder res = new StringBuilder();
    int num = 0;
    for(int i=num1.length()+num2.length();i>0;i--)
    {
        for(int j=Math.min(i-1,num1.length());j>0;j--)
        {
            if(i-j<=num2.length())
            {
                num += (int)(num1.charAt(j-1)-'0')*(int)(num2.charAt(i-1-j)-'0');
            }
        }
        if(i!=1 || num>0)
            res.append(num%10);
        num = num/10;
    }
    return res.reverse().toString();
}

实现中有两个小细节，一个是循环中最后有一个if判断，其实就是看最高一位是不是0（最高第二位不可能是0，除非两个源字符串最高位带有0），如果是就不需要加入字符串中了。

另一个小问题是我们是由低位到高位放入结果串的，所以最后要进行一次reverse，因为是一个O(m+n)的操作，不会影响算法复杂度

