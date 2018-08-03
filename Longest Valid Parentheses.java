Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"



Python:
class Solution:
    def longestValidParentheses(self, s):
        """
        :type s: str
        :rtype: int
        """
        maxLen, i, j = 0, 0, 0
        stack = []
        while (i < len(s)):
            if stack == [] and s[i] == ')':
                j = i + 1
            elif s[i] == '(':
                stack.append(i)
            else:
                stack.pop()
                if stack == []:
                    maxLen = max(maxLen, i-j+1)
                else:
                    maxLen = max(maxLen, i-stack[len(stack)-1])
            i += 1
        return maxLen



Java:
public class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int maxLen = 0, i = 0, j = 0;
        while (i < s.length()) {
            if (stack.isEmpty() && s.charAt(i) == ')')
                j = i + 1;
            else if (s.charAt(i) == '(') 
                stack.push(i);
            else {
                stack.pop();
                if (stack.isEmpty()) maxLen = Math.max(maxLen, i-j+1);
                else maxLen = Math.max(maxLen, i-stack.peek());
            }
            i++;
        }
        return maxLen;
    }
}

O(n) O(n)



Golang:
func longestValidParentheses(s string) int {
    var stack []int
    maxLen, i, j := 0, 0, 0
    for i < len(s) {
        if string(s[i]) == ")" && len(stack) == 0 {
            j = i + 1
        } else if string(s[i]) == "(" {
            stack = append(stack, i)
        } else {
            stack = stack[:len(stack)-1]
            if len(stack) == 0 {
                maxLen = max(maxLen, i-j+1)
            } else {
                maxLen = max(maxLen, i-stack[len(stack)-1])
            }
        }
        i++
    }
    return maxLen
}

func max(x, y int) int {
    if (x > y) {
        return x
    } else {
        return y
    }
}







from code ganker:

这道题是求最长的括号序列，比较容易想到用栈这个数据结构。基本思路就是维护一个栈，遇到左括号就进栈，遇到右括号则出栈，

并且判断当前合法序列是否为最长序列。不过这道题看似思路简单，但是有许多比较刁钻的测试集。具体来说，

主要问题就是遇到右括号时如何判断当前的合法序列的长度。比较健壮的方式如下：

(1) 如果当前栈为空，则说明加上当前右括号没有合法序列（有也是之前判断过的）；

(2) 否则弹出栈顶元素，如果弹出后栈为空，则说明当前括号匹配，我们会维护一个合法开始的起点start，合法序列的长度即为当前元素的位置-start+1；

	否则如果栈内仍有元素，则当前合法序列的长度为当前栈顶元素的位置下一位到当前元素的距离，因为栈顶元素后面的括号对肯定是合法的，而且左括号出过栈了。

因为只需要一遍扫描，算法的时间复杂度是O(n)，空间复杂度是栈的空间，最坏情况是都是左括号，所以是O(n)。实现的代码如下：

public int longestValidParentheses(String s) {
    if(s==null || s.length()==0)
        return 0;
    LinkedList<Integer> stack = new LinkedList<Integer>();
    int start = 0;
    int max = 0;
    for(int i=0;i<s.length();i++)
    {
        if(s.charAt(i)=='(')
        {
            stack.push(i);
        }
        else
        {
            if(stack.isEmpty())
            {
                start = i+1;
            }
            else
            {
                stack.pop();
                max = stack.isEmpty()?Math.max(max,i-start+1):Math.max(max,i-stack.peek());
            }
        }
    }
    return max;
}

这种用剩余栈的栈顶元素位置信息作为当前合法数据的判断依据是比较重要的技巧，在Largest Rectangle in Histogram这道题里面也用到了