class Solution:
    # @param s, a string
    # @return a string
    def reverseWords(self, s):
        return " ".join(s.split()[::-1])

Note: join(), split(), slicing operation [::-1]





public class Solution {
    public String reverseWords(String s) {
        if(s == null || s.length() == 0)
            return "";
        String[] strArr = s.split(" ");         //split空格 将s分解成string array
        StringBuilder sb = new StringBuilder();
        for(int i=strArr.length-1; i>=0; i--)       //逆序遍历 如果非"" 就append进sb 记得加一个空格
        {
            if(!strArr[i].equals(""))
                sb.append(strArr[i]+" ");
        }
        return sb.length() == 0 ? "" : sb.substring(0, sb.length()-1);  //最后判断如果sb长度为0 返回"" 否则返回substring(0, sb.length()-1) sb.length()-1是为了去掉最后的空格
    }
}

Note: 根据programcreek 用split()函数 

要特别注意 split()只分割第一个空格 如果s为"b   a"则会得到strArr = {"b", "", "", "a"} 具体见reverseWord.java

所以21行要判断是不是equals("")

另外24行sb取substring要去掉最后一个空格 到sb.length()-1即可











public class Solution {
    public String reverseWords(String s) {
        s = s.trim();                       //trim
        return helper(0, s).toString();
    }
    
    private StringBuilder helper(int index, String s) {
        if(index>=s.length())
            return new StringBuilder();         //递归到底 返回一个new StringBuilder()
        int lastIndex = index;
        StringBuilder sb = new StringBuilder();
        while(index<s.length() && s.charAt(index)!=' ')     //每次递归将下一个空格前的字符append到sb
        {
            sb.append(s.charAt(index));
            index++;
        }
        while(index<s.length() && s.charAt(index)==' ') //处理连续空格
            index++;
        if(lastIndex==0)                                //lastIndex用来判断是不是最后一个单词 如果是就不用append空格
            return helper(index, s).append(sb);
        return helper(index, s).append(sb).append(" ");
    }
}

Note: 不用split()函数 根据code ganker改编 递归做法

T:O(n) S:O(n)









from code ganker:

这道题是字符串处理的题目，我们先介绍一种很直接的做法，就是类似于java中String::split函数做的操作，把字符串按空格分开，

不过我们把重复的空格直接忽略过去。接下来就是把得到的结果单词反转过来得到结果。因为过程中就是一次扫描得到字符串，然后再一次扫描得出结果，

所以时间复杂度是O(n)。空间上要用一个数组来存，所以是O(n)。实现思路比较清晰，这里就不列举迭代实现的代码了，有兴趣的朋友可以练习一下哈。

下面的代码是这种方法的递归实现： 

public String reverseWords(String s) {
    s = s.trim();
    return helper(s,0).toString();
}
private StringBuilder helper(String s, int index)
{
    if(index>=s.length())
        return new StringBuilder(); 
    StringBuilder cur = new StringBuilder();
    int lastIndex = index;
    while(index < s.length() && s.charAt(index)!=' ')
    {
        cur.append(s.charAt(index++));
    }
    while(index < s.length() && s.charAt(index)==' ')
        index++;
    if(lastIndex == 0)
        return helper(s,index).append(cur);
    return helper(s,index).append(cur).append(' ');
}

接下来我们再介绍另一种方法，思路是先把整个串反转并且同时去掉多余的空格，然后再对反转后的字符串对其中的每个单词进行反转，

比如"the sky is blue"，先反转成"eulb si yks eht"，然后在对每一个单词反转，得到"blue is sky the"。这种方法先反转的时间复杂度是O(n)，

然后再对每个单词反转需要扫描两次（一次是得到单词长度，一次反转单词）,所以总复杂度也是O(n)，比起上一种方法并没有提高，甚至还多扫描了一次，

不过空间上这个不需要额外的存储一份字符串，不过从量级来说也还是O(n)。代码如下：

public String reverseWords(String s) {
    if(s==null) return null;
    s = s.trim();
    if(s.length()==0) return "";
    StringBuilder res = new StringBuilder();
    for(int i=s.length()-1; i>=0; i--){
        if(i!=s.length()-1 && s.charAt(i)==' ' && s.charAt(i)==s.charAt(i+1)) continue;
        res.append(s.charAt(i));
    }
    int left=0;
    int right=0;
    while(right<res.length()){
        while(right<res.length() && res.charAt(right)!=' '){
     right++;
        }
        int next = right+1;
        right--;
        while(left<right){
            char temp = res.charAt(left);
            res.setCharAt(left++, res.charAt(right));
            res.setCharAt(right--, temp);
        }
        left = next;
        right = next;
    }
    return res.toString();
}

可以看出，两种方法并没有哪种有明显优势。之所以列出第二种方法是因为有些题用这种方法确实不错，有时候可以借鉴一下这个想法，就是先反转整体，再反转局部。比如说左旋转一个数组，用这种方法可以用常量空间搞定，大家可以想想哈。










from programCreek

思路：

1 调用split得到字符数组

2 new个新的StringBuilder

3 for循环从后往前如果字符串不为""就append进sb

4 最后判断 返回""或sb.substring

我的写法
public class Solution {
    public String reverseWords(String s) {
        if(s == null || s.length() == 0)
            return "";
        String[] strArr = s.split(" ");			//split空格 将s分解成string array
        StringBuilder sb = new StringBuilder();
        for(int i=strArr.length-1; i>=0; i--)		//逆序遍历 如果非"" 就append进sb 记得加一个空格
        {
            if(!strArr[i].equals(""))
                sb.append(strArr[i]+" ");
        }
        return sb.length() == 0 ? "" : sb.substring(0, sb.length()-1);	//最后判断如果sb长度为0 返回"" 否则返回substring(0, sb.length()-1) sb.length()-1是为了去掉最后的空格
    }
}

Note: 这种解法是调用split()函数 

顺便复习equals方法
