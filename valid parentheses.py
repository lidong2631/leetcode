class Solution:
    # @return a boolean
    def isValid(self, s):
        stack = []
        for i in range(len(s)):     #循环遍历s的所有元素
            if s[i] == '(' or s[i] == '{' or s[i] == '[':       #如果是左括号就入栈
                stack.append(s[i])
            elif s[i] == ')':                       #如果是右括号 则和栈顶元素比较 如果匹配弹出栈 继续 否则如果栈为空或不匹配返回flase
                if not stack or stack.pop()!= '(':
                    return False
            elif s[i] == '}':
                if not stack or stack.pop()!= '{':
                    return False
            else:
                if not stack or stack.pop()!= '[':
                    return False
        if not stack:       #遍历完如果栈为空则为true 否则false
            return True
        else:
            return False





题意：

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

解题思路：判断括号匹配的合法性。使用一个栈来解决问题。遇到左括号入栈，遇到右括号，检查栈顶的左括号是否匹配，如果匹配，弹栈，如果不匹配，返回错误。如果栈为空，而遇到右括号，同样返回错误。遍历完后，栈如果不空，同样返回错误。

代码：


class Solution:
    # @return a boolean
    def isValid(self, s):
        stack = []
        for i in range(len(s)):
            if s[i] == '(' or s[i] == '[' or s[i] == '{':
                stack.append(s[i])
            if s[i] == ')':
                if stack == [] or stack.pop() != '(':
                    return False
            if s[i] == ']':
                if stack == [] or stack.pop() != '[':
                    return False
            if s[i] == '}':
                if stack == [] or stack.pop() != '{':
                    return False
        if stack:
            return False
        else:
            return True





from code ganker:

这道题思路比较简单，主要考察对栈数据结构的操作。遇到左括号就入栈，遇到右括号看栈顶左括号是否与当前右括号匹配，匹配继续，否则返回false。

算法的时间复杂度是O(n)，空间复杂度也是O(n)。代码如下： 

public boolean isValid(String s) {
    if(s==null || s.length()==0)
        return true;
    LinkedList<Character> stack = new LinkedList<Character>();
    for(int i=0;i<s.length();i++)
    {
        switch(s.charAt(i))
        {
            case '(':
            case '{':
            case '[':
                stack.push(s.charAt(i));
                break;
            case ')':
                if(stack.isEmpty() || stack.pop()!='(')
                    return false;
                break;
            case '}':
                if(stack.isEmpty() || stack.pop()!='{')
                    return false;
                break;
            case ']':
                if(stack.isEmpty() || stack.pop()!='[')
                    return false;
                break; 
            default:
                break;
        }
    }
    if(stack.isEmpty())
        return true;
    return false;
}

实现中的小陷阱是注意结束的时候要判一下栈是否为空，不为空仍是不合法的。

