public class Solution {
    public int calculate(String s) {
        int res = 0, num = 0;
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) num = 10*num + s.charAt(i) - '0';
                
            if ((!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') || i == s.length() - 1) {   // careful cannot write else if and must
                switch(sign) {                                                                  // state !Character.isDigit(s.charAt(i)) ex "42"
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
        while (!stack.isEmpty())
            res += stack.pop();
        return res;
    }
}

O(n) O(n)


https://leetcode.com/discuss/41902/share-my-java-solution


Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.