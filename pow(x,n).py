class Solution:
    # @param x, a float
    # @param n, a integer
    # @return a float
    def pow(self, x, n):
        if n == 0:
            return 1.0
        res = x
        for i in range(2, n+1):
            res = res * x
        if n > 0:
            return res
        else:
            return 1/res

Note: 第一种解法 抱runtime error
        Runtime Error Message:  Line 9: MemoryError
        Last executed input:    0.00001, 2147483647

AC解

class Solution:
    # @param x, a float
    # @param n, a integer
    # @return a float
    def pow(self, x, n):
        if n == 0:
            return 1.0
        half = pow(x, int(1.0*n/2))     #将x^n分解成x^(n/2) * x^(n/2)的形式
        if n % 2 == 0:          #如果n可以整除 是正（大于0）或负偶数(小于0) 则x^n = half * half
            return half * half
        elif n > 0:             #如果n不能整除 是正奇数 则x^n = half * half * x
            return half * half * x
        else:                       #如果n是负奇数 half一定是小于0的数 则x^n = half / x * half
            return half / x * half

这题要注意越界问题 数值计算的题都要注意越界边界问题

接下来我们介绍二分法的解法，如同我们在Sqrt(x)的方法。不过这道题用递归来解比较容易理解，把x的n次方划分成两个x的n/2次方相乘，
然后递归求解子问题，结束条件是n为0返回1。因为是对n进行二分，算法复杂度和上面方法一样，也是O(logn)

以上代码比较简洁，不过这里有个问题是没有做越界的判断，因为这里没有统一符号，所以越界判断分的情况比较多，
不过具体也就是在做乘除法之前判断这些值会不会越界，有兴趣的朋友可以自己填充上哈，这里就不写太啰嗦的代码了。
不过实际应用中健壮性还是比较重要的，而且递归毕竟会占用递归栈的空间，所以我可能更推荐第一种解法。




下面的解法我没看懂

题意：Implement pow(x, n).

解题思路：求幂函数的实现。使用递归，类似于二分的思路，解法来自Mark Allen Weiss的《数据结构与算法分析》。

代码：

复制代码
class Solution:
    # @param x, a float
    # @param n, a integer
    # @return a float
    def pow(self, x, n):
        if n == 0:
            return 1
        elif n == 1:
            return x
        elif n % 2:
            return pow(x*x,n/2)*x
        else:
            return pow(x*x,n/2)