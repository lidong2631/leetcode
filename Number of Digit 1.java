public class Solution {
    public int countDigitOne(int n) {
        if (n <= 0) return 0;           // careful need to consider negative number
        long factor = 1, high = 0, low = 0;     // careful long need to be used
        int curr = 0, count = 0;
        while (n / factor != 0) {
            low = n % factor;
            curr = (int)(n / factor % 10);  // careful need to change to int
            high = n / (factor * 10);
            switch (curr) {
                case 0:
                    count += high * factor;
                    break;
                case 1:
                    count += high * factor + low + 1;
                    break;
                default:
                    count += (high + 1) * factor;
            }
            factor *= 10;
        }
        return count;
    }
}

这个题其实和题目1491：求1和2的个数完全一样。
不一样的地方只是输入。一个输入的是数字，一个是字符串，处理方式完全一样。
来分析一下思路。尽量简单一点。

1)、假设有一个五位数，32145
那么从1到32145一共包含多少个1呢。
我们对百位数1开始分析。也就是百位数为1的个数一共有多少个呢。
对于它的高位来说，是32。一共有00100~00199，01100~01199，02100~02199，.....，31100~31199，一共是32*100个数。
对于它的地位来说，是45。一共有100~145，46个数。
加起来是highNum*100+lowNum+1；

2)、那如果百位不是1呢，是0怎么算呢。
如果是32045，
对于它的高位来说，是32。一共有00100~00199，01100~01199，02100~02199，.....，31100~31199，一共是32*100个数。
对于它的地位来说，是45。无论怎么样，都不可能让百位出现1，
个数加起来是highNum*100

3)、如果是32445，
对于它的高位来说，是32。一共有00100~00199，01100~01199，02100~02199，.....，31100~31199，一共是32*100个数。
对于它的地位来说，是45。一共有100~199，100个数。
个数加起来是（highNum+1）*100

所以总结起来就是
如果当前位为0，该位出现1的情况仅仅取决于高位
如果当前位为1，该位出现1的情况为高位*倍数 + 低位 + 1;
如果当前位大于1，该位出现1的情况仅仅取决于高位+1 乘以倍数；
所以结果就应该是对每个位求1出现的个数，加起来就是最终的结果。


http://blog.csdn.net/u013027996/article/details/17126977