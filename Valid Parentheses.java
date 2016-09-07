public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) stack.push(c);
            else if (stack.isEmpty() || map.get(stack.pop()) != c) return false;
        }
        return stack.isEmpty();
    }
}



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
