Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22




class Solution:
    # @param tokens, a list of string
    # @return an integer
    def evalRPN(self, tokens):
        stack = []
        for i in tokens:
            if i not in ('+', '-', '*', '/'):   #如果非运算符 就进栈
                stack.append(int(i))
            else:
                op2 = stack.pop()       #否则弹出两个数
                op1 = stack.pop()
                if i == '+':            #根据运算符计算将结果入栈
                    stack.append(op1 + op2)
                elif i == '-':
                    stack.append(op1 - op2)
                elif i == '*':
                    stack.append(op1 * op2)
                else:
                    stack.append(int(op1 * 1.0 / op2))
        return stack[0]

Note: list.append(), list.pop()

python的除法很特殊，和java以及c++不同，如果除数与被除数符号相同，那么结果是一样的，取下整。但是当符号不同时，python还是向下取整，比如真是结果是-0.1，python得出的结果是-1，二java以及c++得出的是0
leetcode-Evaluate Reverse Polish Notation这题就是这种情况，如果用python实现，必须要特殊处理除法的情况。
    if(v1*v2<0):
                return -((-v2)/v1);
            else:
                return v2/v1
这样才不会wrong answer





public class Solution {
    interface Operator {
        int eval(int x, int y);
    }
    
    private static final Map<String, Operator> OPERATORS = 
        new HashMap<String, Operator>() {{
             put("+", new Operator() {
                public int eval(int x, int y) { return x + y; } 
             });
             put("-", new Operator() {
                public int eval(int x, int y) { return x - y; } 
             });
             put("*", new Operator() {
                public int eval(int x, int y) { return x * y; } 
             });
             put("/", new Operator() {
                public int eval(int x, int y) { return x / y; } 
             });
        }};
    
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        for (String token : tokens) {
            if (OPERATORS.containsKey(token)) {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(OPERATORS.get(token).eval(x, y));
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}

Refactored code!



Java:
public class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String str : tokens) {
            switch(str) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    stack.push(-stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int divisor = stack.pop();
                    stack.push(stack.pop() / divisor);
                    break;
                default:
                    stack.push(Integer.parseInt(str));
            }
        }
        return stack.pop();
    }
}




from code ganker:


这道题是逆波兰式的求解，不了解逆波兰式的朋友可以参考一下逆波兰表示法 - wiki。逆波兰式有个优点就是他不需要括号来表示优先级，直接根据式子本身就可以求解。

思路是维护一个运算数栈，读到运算数的时候直接进栈，而每得到一个运算符，就从栈顶取出两个运算数，运算之后将结果做为一个运算数放回栈中，直到式子结束，

此时栈中唯一一个元素便是结果。代码如下：

public int evalRPN(String[] tokens) {
    if(tokens==null || tokens.length==0)
        return 0;
    LinkedList<Integer> stack = new LinkedList<Integer>();
    for(int i=0;i<tokens.length;i++)
    {
        if(tokens[i].equals("+"))
        {
            stack.push(stack.pop()+stack.pop());
        }
        else if(tokens[i].equals("-"))
        {
            stack.push(-stack.pop()+stack.pop());
        }
        else if(tokens[i].equals("*"))
        {
            stack.push(stack.pop()*stack.pop());
        }
        else if(tokens[i].equals("/"))
        {
            int num1 = stack.pop();
            int num2 = stack.pop();
            stack.push(num2/num1);
        }
        else
        {
            stack.push(Integer.parseInt(tokens[i]));
        }
    }
    return stack.pop();
}

以上代码中有一个没有周全的地方是没有对逆波兰式错误的情况进行出错处理，其实也不难，就是每次pop操作检查栈空情况，如果栈空(isEmpty())，则说明出错。还有就是最后检查一下栈的size，

如果不是1也说明运算数多了，返回错误。有兴趣的朋友可以自己补充一下哈。

和这道题类似的，有波兰式求解，中缀表达式求解，这几个其实是表达式的不同表达方式。既然这里出现了逆波兰式，大家还是看看其他两种的求解方法，原理其实近似，

都是通过维护栈来实现，网上也有不少材料，这里就不细说了




