class Solution:
    # @param path, a string
    # @return a string
    def simplifyPath(self, path):
        stack = []; i = 0; res = ''
        while i < len(path):        #遍历一遍path
            end = i + 1         #end从i＋1开始 要跳过'/'
            while end < len(path) and path[end]!='/':   #只要end还小于path的长度 且end还没到下一个'/' end＋1
                end+=1
            subDir = path[i+1:end]      #得到下一个路径subDir 即两个'/'之间的内容
            if len(subDir) > 0:     #如果subDir长度大于0
                if subDir == '..':      #且如果subDir为'..' 判读stack是否为空 非空就pop栈顶元素
                    if stack!=[]:
                        stack.pop()
                elif subDir!='.':       #如果subDir不是'.' 就将内容push入栈 如果是'.'什么也不做
                    stack.append(subDir)
            i = end             #处理完毕后i跳到end处 即下一个'/' 准备处理下一个路径
        if stack == []:         #都遍历完如果栈为空 返回'/'
            return '/'
        for i in range(len(stack)):     #否则循环将栈中所有元素逐个加到res中
            res += '/' + stack[i]
        return res





题意：

Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

click to show corner cases.

Corner Cases:
 

Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
解题思路：
题目的要求是输出Unix下的最简路径，Unix文件的根目录为"/"，"."表示当前目录，".."表示上级目录。

例如：

输入1：

/../a/b/c/./.. 

输出1：

/a/b

模拟整个过程：

1. "/" 根目录

2. ".." 跳转上级目录，上级目录为空，所以依旧处于 "/"

3. "a" 进入子目录a，目前处于 "/a"

4. "b" 进入子目录b，目前处于 "/a/b"

5. "c" 进入子目录c，目前处于 "/a/b/c"

6. "." 当前目录，不操作，仍处于 "/a/b/c"

7. ".." 返回上级目录，最终为 "/a/b"

使用一个栈来解决问题。遇到'..'弹栈，遇到'.'不操作，其他情况下压栈。

代码一：

复制代码
class Solution:
    # @param path, a string
    # @return a string
    def simplifyPath(self, path):
        stack = []
        i = 0
        res = ''
        while i < len(path):
            end = i+1
            while end < len(path) and path[end] != "/":
                end += 1
            sub=path[i+1:end]
            if len(sub) > 0:
                if sub == "..":
                    if stack != []: stack.pop()
                elif sub != ".":
                    stack.append(sub)
            i = end
        if stack == []: return "/"
        for i in stack:
            res += "/"+i
        return res

代码二：

利用python的字符串处理能力, 顺便复习下python字符串函数

复制代码
class Solution:
    # @param path, a string
    # @return a string
    def simplifyPath(self, path):
        path = path.split('/')
        curr = '/'
        for i in path:
            if i == '..':
                if curr != '/':
                    curr = '/'.join(curr.split('/')[:-1])
                    if curr == '': curr = '/'
            elif i != '.' and i != '':
                curr += '/' + i if curr != '/' else i
        return curr



public class Solution {
    public String simplifyPath(String path) {
        int i = 0;
        Stack<String> stack = new Stack<String>();
        while(i<path.length()) {
            int index = i;
            StringBuilder tmp = new StringBuilder();
            while(i<path.length() && path.charAt(i)!='/') { //每次取两个'/'之间的字符
                tmp.append(path.charAt(i));
                i++;
            }
            if(index!=i) {
                String tmpStr = tmp.toString();
                if(tmpStr.equals("..")) {   //如果是".." 弹栈
                    if(!stack.empty())
                        stack.pop();
                }
                else if(!tmpStr.equals("."))    //否则如果不是"." 进栈
                    stack.push(tmpStr);
            }
            i++;
        }
        if(stack.empty())   //最后要看一下栈是空 就输出根路径"/"
            return "/";
        String res = "";
        while(!stack.empty()) {
            res="/"+stack.pop()+res;
        }
        return res;
    }
}
使用一个栈来解决问题。遇到'..'弹栈，遇到'.'不操作，其他情况下压栈
根据code ganker改写的

时间O(n) 空间O(n)







public class Solution {
    public String simplifyPath(String path) {
        if(path==null || path.length()==0)
            return "";
        StringBuilder res = new StringBuilder();
        LinkedList<String> stack = new LinkedList<String>();
        int i=0;
        
        while(i<path.length())  //扫一遍字符串
        {
            int index = i;
            StringBuilder tmp = new StringBuilder();
            
            while(i<path.length()&&path.charAt(i)!='/')     //这里要注意要先写i<path.length() 不然会报indexoutofbound exception
            {    
                tmp.append(path.charAt(i));
                i++;
            }
            if(index!=i)    //如果index不等于i 说明两个/ /间有东西 将它push到stack中
            {
                String tmpStr = tmp.toString();
                if(tmpStr.equals(".."))
                {
                    if(!stack.isEmpty())
                        stack.pop();
                }
                else if(!tmpStr.equals("."))
                    stack.push(tmpStr);
            }
            i++;
        }
        if(!stack.isEmpty())    //最后出栈输出获得结果
        {
            String[] str = stack.toArray(new String[stack.size()]); //这里是按stack的顺序 后进的元素在前面 所有要从后往前遍历
            for(int j=str.length-1; j>=0; j--)
            {
                res.append("/"+str[j]);
            }
        }
        if(res.length()==0)
            return "/";
        return res.toString();
    }
}

Note: 根据code ganker版改编 这题有好几个细节要注意 见注释 注意LinkedList toArray()方法






from code ganker:

这道题目是Linux内核中比较常见的一个操作，就是对一个输入的文件路径进行简化。思路比较明确，就是维护一个栈，对于每一个块（以‘/’作为分界）进行分析，

如果遇到‘../’则表示要上一层，那么就是进行出栈操作，如果遇到‘./’则是停留当前，直接跳过，其他文件路径则直接进栈即可。

最后根据栈中的内容转换成路径即可（这里是把栈转成数组，然后依次添加）。时间上不会超过两次扫描（一次是进栈得到简化路径，一次是出栈获得最后结果），

所以时间复杂度是O(n)，空间上是栈的大小，也是O(n)。代码如下

public String simplifyPath(String path) {
    if(path == null || path.length()==0)
    {
        return "";
    }
    LinkedList<String> stack = new LinkedList<String>();
    StringBuilder res = new StringBuilder();
    int i=0;
    
    while(i<path.length())
    {
        int index = i;
        StringBuilder temp = new StringBuilder();
        while(i<path.length() && path.charAt(i)!='/')
        {
            temp.append(path.charAt(i));
            i++;
        }
        if(index!=i)
        {
            String str = temp.toString();
            if(str.equals(".."))
            {
                if(!stack.isEmpty())
                    stack.pop();
            }
            else if(!str.equals("."))
            {
                stack.push(str);
            }
        }
        i++;
    }
    if(!stack.isEmpty())
    {
        String[] strs = stack.toArray(new String[stack.size()]);
        for(int j=strs.length-1;j>=0;j--)
        {
          res.append("/"+strs[j]);
        }
    }
    if(res.length()==0)
        return "/";
    return res.toString();
}

这道题其实还有一种做法，不需要维护栈，也就是不用额外空间，但是要对字符位置进行比较好的记录或者回溯，可能会多扫描一次，但是不会增加时间复杂度的量级。

不过那个方法虽然对于空间上有提高，但是有很多细节的操作，并且没有什么算法思想，属于纯字符串操作，个人觉得意义不是很大，而且在面试中也很难正确写出来，还是比较推荐上述解法。


from lexi leetcode:


先用/来split string

然后看每一小段，若是”.”或者是“”（说明两个/连着），不入栈；若是”..”，pop；若是正常，push

最后再用string builder把”/”和栈中元素一个一个连起来。

public String simplifyPath(String path) {
    Stack<String> s = new Stack<String>();
    String[] split = path.split("/");
    for (String a : split) {
        if (!a.equals(".") && !a.isEmpty()) {
            if (a.equals("..")) {
                if (!s.isEmpty()) {
                    s.pop();
                }
            } else {
                s.push(a);
            }
        }
    }
    StringBuilder sb = new StringBuilder();
    while (!s.isEmpty()) {
        sb.insert(0, s.pop());
        sb.insert(0, "/");
    }
    return sb.length() == 0 ? "/" : sb.toString();
}