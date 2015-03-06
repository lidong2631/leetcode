class Solution:
    # @return an integer
    def reverse(self, x):
        if x >= 0:          #保留符号
            sign = 1
        else:
            sign = -1
        x = abs(x)          #将x取绝对值参与翻转
        answer = 0
        while x > 0:        #套路 每次从x剥离一位 补上相应位数的0 放到answer里 循环直到所有位数都放完
            answer = answer * 10 + x % 10
            x/=10
        return sign * answer    #最后结果乘上符号位





Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

click to show spoilers.

Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!

If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?

Throw an exception? Good, but what if throwing an exception is not an option? You would then have to re-design the function (ie, add an extra parameter).