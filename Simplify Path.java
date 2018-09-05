Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

Corner Cases:

Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".



Python:
class Solution(object):
    def simplifyPath(self, path):
        places = [p for p in path.split("/") if p != "." and p != ""]
        stack = []
        for p in places:
            if p == "..":
                if len(stack) > 0:
                    stack.pop()
            else:
                stack.append(p)
        return "/" + "/".join(stack)



Java:
class Solution {
    public String simplifyPath(String path) {
        String[] p = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String s : p) {
            if (s.equals("..")) {
                if (!stack.isEmpty())
                    stack.pop();
            } else if (!s.equals(".") && !s.equals("")) {
                stack.push(s);
            }
        }
        if (stack.isEmpty())
            return "/";
        String res = "";
        while (!stack.isEmpty()) {
            res = "/" + stack.pop() + res;
        }
        return res;
    }
}



public class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        int i = 0;
        while (i < path.length()) {
            StringBuffer sb = new StringBuffer();
            int left = i;
            while (i < path.length() && path.charAt(i) != '/') {
                sb.append(path.charAt(i));
                i++;
            }
            if (i != left) {                                // careful need to check if there is something
                String tmp = sb.toString();
                if (tmp.equals("..")) {                     // careful cannot write if (!stack.isEmpty() && tmp.equals(".."))
                    if (!stack.isEmpty()) stack.pop();
                }
                else if (!tmp.equals(".")) stack.push(tmp);    
            }
            i++;
        }
        if (stack.isEmpty()) return "/";
        String res = "";
        while (!stack.isEmpty())
            res = "/" + stack.pop() + res;
        return res;
    }
}




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