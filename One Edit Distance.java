From cleanCode

public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(); int n = t.length();
        if(m>n)                                 //如果m大于n 交换他们的位置 总是保证m<=n 这样方便处理
            return isOneEditDistance(t, s);
        if(n-m>1)       //如果两个字符串长度相差大于1 返回false
            return false;
        int i = 0;
        while(i<m && s.charAt(i)==t.charAt(i))  //逐个比较字符
            i++;
        if(i==m)        //跳出循环时如果i等于m说明 所有字符都相等 看一下如果n比m多一个字符则是append的情况 返回true 否则返回false
            return n-m==1;
        if(n==m)      //如果i不等于m 根据m和n的差值继续比较剩余的字符 如果是modify或insert的情况最后i可以等于m 否则返回false
            i++;
        while(i<m && s.charAt(i)==t.charAt(i+n-m))
            i++;
        return i==m;
    }
}

这题首先通过swap保证m一定小于等于n 然后判断m和n是否是三种关系(append, modify, insert)中的一种 思路还是比较清晰的 时间O(n) 空间O(1)