Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true





Python:
class Solution:
    def isValid(self, s):
        """
        :type s: str
        :rtype: bool
        """
        stack = []
        m = {}
        m['('] = ')'
        m['['] = ']'
        m['{'] = '}'
        
        for c in s:
            if c in m:
                stack.append(c)
            elif len(stack) == 0 or m[stack.pop()] != c:
                return False
        return len(stack) == 0



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

O(n) O(n)



Golang:
func isValid(s string) bool {
    var stack []string
    m := make(map[string]string)
    m["("] = ")"
    m["["] = "]"
    m["{"] = "}"
    
    for _, v := range s {
        curr := string(v)
        if _, ok := m[curr]; ok {
            stack = append(stack, curr)
        } else if len(stack) == 0 || m[stack[len(stack)-1]] != curr {
            return false
        } else {
            stack = stack[:len(stack)-1]
        }
    }
    return len(stack) == 0
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
