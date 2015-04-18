public class Solution {
    public int titleToNumber(String s) {
        int count = 0;
        int res = 0;
        for(int i=s.length()-1; i>=0; i--) {
            res+=(s.charAt(i)-'A'+1)*Math.pow(26, count++);
        }
        return res;
    }
}

进制转换问题 跟Excel Sheet Column Title相反　这里特别注意 java中没有^操作符 要进行乘方运算要用Math.pow()函数

时间O(n) 空间O(1)