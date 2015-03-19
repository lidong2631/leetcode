from cleanCode

public class Solution {
    public boolean isNumber(String s) {
        int i=0, n = s.length();
        while(i<n && Character.isWhitespace(s.charAt(i)))	//判断起始位置的whitespace
            i++;
        if(i<n && (s.charAt(i)=='+' || s.charAt(i)=='-'))	//接着判断是否有正负号
            i++;
        boolean isNumeric = false;						//判断字符串中是否有数字
        while(i<n && Character.isDigit(s.charAt(i))) {	//然后判断整数位 如果有将isNumeric设为true
            i++;
            isNumeric = true;
        }
        if(i<n && s.charAt(i)=='.') {	//判断小数点
            i++;
            while(i<n && Character.isDigit(s.charAt(i))) {	//判断紧接小数点后的小数位
                i++;
                isNumeric = true;
            }
        }
        while(i<n && Character.isWhitespace(s.charAt(i)))	//最后判断余下的whitespace
            i++;
        return isNumeric && i==n;
    }
}

这题题目比较模糊 要注意先提问澄清一些情况再着手解题

官方解法思路很清晰 先是简化版的题目不考虑e的情况 判断4个地方 1 开始的whitespace 2 正负号 3 数字 4 末尾的whitespace

对于3 数字 又分为3部分 1 整数部分 2 小数点 4 小数部分 1 3两部分只能包含数字 要注意的是1和3至少有一个部分要有否则无法构成合法的数字

如3.64 2. .2均为合法数字 但单独.就不是数字 着重理解下程序的结构如何将这些逻辑实现出来的 时间O(n) 空间O(1)

对比看下String to Integer那个题






public class Solution {
    public boolean isNumber(String s) {
        int i=0, n = s.length();
        while(i<n && Character.isWhitespace(s.charAt(i)))
            i++;
        if(i<n && (s.charAt(i)=='+' || s.charAt(i)=='-'))
            i++;
        boolean isNumeric = false;
        while(i<n && Character.isDigit(s.charAt(i))) {
            i++;
            isNumeric = true;
        }
        if(i<n && s.charAt(i)=='.') {
            i++;
            while(i<n && Character.isDigit(s.charAt(i))) {
                i++;
                isNumeric = true;
            }
        }
        if(i<n && isNumeric && s.charAt(i)=='e') {	//60-69处理e的情况
            i++;									//跳过e
            if(i<n && (s.charAt(i)=='+' || s.charAt(i)=='-'))	//判断正负号
                i++;
            isNumeric = false;								//将isNumeric设为false
            while(i<n && Character.isDigit(s.charAt(i))) {	//判断后面的是否是合法数字
                i++;
                isNumeric = true;
            }
        }
        while(i<n && Character.isWhitespace(s.charAt(i)))
            i++;
        return isNumeric && i==n;
    }
}

增加了对e的考虑 如1.6347e-4. 2e10 注意e后面的数字可以接正负号 也可以是4.这样