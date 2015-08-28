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





public class Solution {
    public int calculate(String s) {
        return evalRPN(InfixToPostFix(s));
    }
    
    private int rank(char op) {

        switch(op) {
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            default:
                return 0;   //'('
        }
    }
    private List<String> InfixToPostFix(String s) {
        Stack<Character> stack = new Stack<Character>();
        List<String> res = new LinkedList<>();
    
        int num = 0;
        boolean isOperand = false;
        for(char c : s.toCharArray()) {
            if(c>='0' && c<='9') {
                num = 10*num + c-'0';
                isOperand = true;
            }
            else {
                if(isOperand)
                    res.add(String.valueOf(num));
                num = 0;
                isOperand = false;
    
                if(c==' ' || c=='\t')
                    continue;
    
                if(c=='(') {
                    stack.push('(');
                }
                else if(c==')') {
                    while(stack.peek()!='(')
                        res.add(String.valueOf(stack.pop()));
                    stack.pop();  //pop '('
                }
                else {
                    while(!stack.empty() && rank(stack.peek())>=rank(c))
                        res.add(String.valueOf(stack.pop()));
                    stack.push(c);
                }
            }
        }
        if(isOperand)
            res.add(String.valueOf(num));
        while(!stack.empty())
            res.add(String.valueOf(stack.pop()));
        return res;
    }
    
    private int evalRPN(List<String> tokens) {
        if(tokens == null || tokens.size() == 0)
        {
            return 0;
        }
        LinkedList<Integer> stack = new LinkedList<Integer>();
        for(int i=0; i<tokens.size(); i++)
        {
            if(tokens.get(i).equals("+")) 
                stack.push(stack.pop()+stack.pop());
            
            else if(tokens.get(i).equals("-"))
                stack.push(-stack.pop()+stack.pop());
            else if(tokens.get(i).equals("*"))
                stack.push(stack.pop()*stack.pop());
                
            else if(tokens.get(i).equals("/"))
            {
                int op2 = stack.pop();
                int op1 = stack.pop();
                stack.push(op1/op2);
            }
            
            else
                stack.push(Integer.parseInt(tokens.get(i)));
        }
        return stack.pop();
    }
}

超时

注意理解infix to postfix的过程
http://scriptasylum.com/tutorials/infix_postfix/algorithms/infix-postfix/



int rank(char op) {

    switch(op) {
        case '+':
            return 1;
        case '-':
            return 1;
        case '*':
            return 2;
        case '/':
            return 2;
        default:
            return 0;   //'('
    }
}


List< InfixToPostFix(String s) {
    Stack<Character> stack = new Stack<Character>();
    List<> res = new LinkedList<>();

    int num = 0;
    boolean isOperand = false;
    for(char c : s.toCharArray()) {
        if(c>='0' && c<='9') {
            num = 10*num + c-'0';
            isOperand = true;
        }
        else {
            if(isOperand)
                res.add(num);
            num = 0;
            isOperand = false;

            if(c==' ' || c=='\t')
                continue;

            if(c=='(') {
                stack.push('(');
            }
            else if(c==')') {
                while(!stack.peek()!='(')
                    res.add(stack.pop());
                res.pop();  //pop '('
            }
            else {
                while(!stack.empty() && rank(stack.peek())>=rank(c))
                    res.add(stack.pop());
                stack.push(c);
            }
        }
    }
    if(isOperand)
        res.add(num);
    while(!stack.empty())
        res.add(stack.pop());
    return res;
}

int evaluatePostfix(List<Object> postfix) {
    Stack<Integer> operands = new Stack<Integer>();
    int a = 0, b = 0;
    for (Object s : postfix) {
        if(s instanceof Character){
            char c = (Character) s;
            b = operands.pop();
            a = operands.pop();
            switch (c) {
                case '+': operands.push(a + b); break;
                case '-': operands.push(a - b); break;
                case '*': operands.push(a * b); break;
                default : operands.push(a / b); 
            }
        }else { // instanceof Integer
            operands.push((Integer)s);
        }
    }
    return operands.pop();
}

public int calculate(String s) {
    return evaluatePostfix(infixToPostfix(s));
}

先转postfix notation 再利用evaluate reverse polish那题计算结果

https://leetcode.com/discuss/39454/accepted-infix-postfix-based-solution-explaination-600ms