public class Solution {
    public int calculate(String s) {
        if(s==null || s.length()==0)
            return 0;
        return helper(s.toCharArray(), 0, s.length());
    }
    
    public int helper(char[] s, int start, int end) {
        int pos = start, result = 0, current = 0, sign = 1;
        while(true){
            if(pos == end){
                return result += current * sign;
            }
            else if(s[pos] == ')'){
                return result;
            }
            else if(s[pos] == '('){ //碰到括号 截取2个括号中间的东西 注意start包含左括号 pos最终指向对应右括号的后一个字符
                start = ++pos;
                int count = 0;      //count记录两个括号中有几个子括号
                while(s[pos] != ')' || count != 0){
                    if(s[pos] == '(')
                        count++;
                    else if(s[pos] == ')')
                        count--;
                    pos++;
                }
                result += sign * helper(s, start, pos); //递归括号中的内容
            }
            else if(s[pos] >= '0' && s[pos] <= '9'){    //处理数字
                current = current * 10 + s[pos] - '0';
            }
            else if(s[pos] != ' '){     //处理加减符号
                result += current * sign;
                current = 0;
                sign = s[pos] == '-'? -1 : 1;
            }
            pos++;
        }
    }
}

递归解

https://leetcode.com/discuss/39481/concise-solution-using-recursion-postfix-convertion-needed




先转postfix notation 再利用evaluate reverse polish那题计算结果

https://leetcode.com/discuss/39454/accepted-infix-postfix-based-solution-explaination-600ms