Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Update (2015-02-12):
For C programmers: Try to solve it in-place in O(1) space.

click to show clarification.

Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.


public class Solution {
    public String reverseWords(String s) {
        StringBuffer sb = new StringBuffer();
        int j = s.length();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') j = i;
            else if (i == 0 || s.charAt(i-1) == ' ') {
                if (sb.length() == 0) sb.append(s.substring(i,j));
                else sb.append(" " + s.substring(i,j));
            }
        }
        return sb.toString();
    }
}

Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".



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

可以看出，两种方法并没有哪种有明显优势。之所以列出第二种方法是因为有些题用这种方法确实不错，有时候可以借鉴一下这个想法，就是先反转整体，再反转局部。

比如说左旋转一个数组，用这种方法可以用常量空间搞定，大家可以想想哈。