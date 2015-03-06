class Solution:
    # @param s, a string
    # @return an integer
    def longestValidParentheses(self, s):
        maxLen = 0				#初始化最大长度maxLen, 有效开始位置validStart 一个栈stack
        validStart = -1
        stack = []
        for i in range(len(s)):			#循环扫一遍string s
            if s[i] == '(':			#如果是左括号 就一直push入栈 注意入栈的是左括号在string s中的索引值
                stack.append(i)
            else:					#如果是右括号 要判断
                if stack == []:			#如果当前栈为空 则加入的右括号肯定无法凑出括号对 将validStart设为i
                    validStart = i
                else:					#如果栈不为空
                    stack.pop()		#首先pop一个左括号出栈
                    if stack == []:			#如果pop完后stack变为空 说明当前可以得到合法括号对 长度为i-validStart 更新maxLen 注意要和maxLen自身比较去两者大值 如"(()))())("
                        maxLen = max(maxLen, i - validStart)
                    else:			#如果pop完stack不为空 当前合法序列的长度为当前栈顶元素的位置下一位到当前元素的距离 同样要和maxLen自身比较取较大值更新maxLen
                        maxLen = max(maxLen, i - stack[len(stack)-1])
        return maxLen

Note: 这题的这种用剩余栈的栈顶元素位置信息作为当前合法数据的判断依据是比较重要的技巧，在Largest Rectangle in Histogram这道题里面也用到了

要好好记住




public class Solution {
    public int longestValidParentheses(String s) {
        if(s==null || s.length()==0)
            return 0;
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int start = 0;
        int max = 0;
        for(int i=0; i<s.length(); i++)
        {
            if(s.charAt(i)=='(')
                stack.push(i);
            else
            {
                if(stack.isEmpty())
                    start = i+1;
                else
                {
                    stack.pop();
                    if(stack.isEmpty())
                        max = Math.max(max, i-start+1);     //感觉这句很少会碰到 一旦有')'在空栈时插入 这句就再也不会被执行了
                    else max = Math.max(max, i-stack.peek());
                }
            }
        }
        return max;
    }
}

Note: python版和code ganker版一样 不难理解







题意：

Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

解题思路：返回括号串中合法括号串的长度。使用栈。这个解法比较巧妙，开辟一个栈，压栈的不是括号，而是未匹配左括号的索引！

代码：


class Solution:
    # @param s, a string
    # @return an integer
    def longestValidParentheses(self, s):
        maxlen = 0
        stack = []
        last = -1
        for i in range(len(s)):
            if s[i]=='(':
                stack.append(i)     # push the INDEX into the stack!!!!
            else:
                if stack == []:
                    last = i
                else:
                    stack.pop()
                    if stack == []:
                        maxlen = max(maxlen, i-last)
                    else:
                        maxlen = max(maxlen, i-stack[len(stack)-1])
        return maxlen






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