class Solution:
    # @param s, a string
    # @return a boolean
    def isPalindrome(self, s):
        if s == '':                             #empty string is palindrome according to question
            return True
        sTmp = ''
        for i in range(0, len(s)):
            if s[i]>='0' and s[i]<='9' or s[i]>='A' and s[i]<='Z' or s[i]>='a' and s[i]<='z':
                sTmp+=s[i]                      #put all alphanumeric character into temp string
        sTmp = sTmp.lower()                     #change into lowercase
        for i in range(len(sTmp)/2):            #check if palindrome
            if sTmp[i] != sTmp[len(sTmp)-1-i]:
                return False
        return True



题意：

Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.

解题思路：将不是字母的字符去掉，然后转换成小写，然后简单的回文判断。

代码：


class Solution:
    # @param s, a string
    # @return a boolean
    def isPalindrome(self, s):
        if s == '':
            return True
        else:
            sTmp = ''
            for i in range(0, len(s)):
                if s[i] >= 'a' and s[i] <= 'z' or s[i] >= '0' and s[i] <= '9' or s[i] >= 'A' and s[i] <= 'Z':
                    sTmp += s[i]
            sTmp = sTmp.lower()
            for i in range(0, len(sTmp)/2):
                if sTmp[i] != sTmp[len(sTmp)-1-i]:
                    return False
            return True








public class Solution {
    public boolean isPalindrome(String s) {
        if(s==null || s.length()==0)
            return true;
        int left = 0;
        int right = s.length()-1;
        while(left<right)           //奇数字符最终left==right 偶数字符最终left>right
        {
            if(!isValid(s.charAt(left)))    //不是合法字符就将对应指针+1 continue下一轮循环
            {
                left++;
                continue;
            }
            if(!isValid(s.charAt(right)))
            {
                right--;
                continue;
            }
            if(!isSame(s.charAt(left), s.charAt(right)))    //两边字符不想等 返回false
                return false;
            left++;         //否则左右指针各+1
            right--;
        }
        return true;
    }
    
    private boolean isValid(char c) {
        if((c>='A'&&c<='Z') || (c>='a'&&c<='z') || (c>='0'&&c<='9'))
            return true;
        return false;
    }
    
    private boolean isSame(char c1, char c2) {
        if(c1>='A'&&c1<='Z')
            c1 = (char)(c1-'A'+'a');    //注意这里要(char)将数字转成char
        if(c2>='A'&&c2<='Z')
            c2 = (char)(c2-'A'+'a');    //大写字符转小写字符 要记住 先减去'A' 再加'a'
        return (c1==c2);
    }
}

Note: 这题要注意细节 题不算难

顺便记得ASCII中 排序是常用字符(space, '(', '#') < 数字 < 大写字符 < 小写字符



from code ganker:

这道题是判断一个字符串是不是回文串。因为只是看一个字符串，算法还是比较简单，就是从两头出发，往中间走，进行两两匹配。

这里面的小问题就是在这个题目要求中，只判断字母和数字类型的字符，其他字符直接跳过即可。因此我们要写一个函数判断他是不是合法字符，

而且因为忽略大小写，我们在判断两个字符是不是相同的时候如果是大写，要转成相应的小写字母。这个算法从两边扫描，到中间相遇，只需要一次线性扫描，

复杂度是O(n)，空间上是O(1)。代码如下：

public boolean isPalindrome(String s)
{
    if(s==null || s.length()==0)
        return true;
    int l = 0;
    int r = s.length()-1;
    while(l<r)
    {
        if(!isValid(s.charAt(l)))
        {
            l++;
            continue;
        }
        if(!isValid(s.charAt(r)))
        {
            r--;
            continue;
        }
        if(!isSame(s.charAt(l),s.charAt(r)))
        {
            return false;
        }
        l++;
        r--;
    }
    return true;
}
private boolean isValid(char c)
{
    if(c>='a' && c<='z' || c>='A' && c<='Z' || c>='0' && c<='9')
        return true;
    return false;
}
private boolean isSame(char c1, char c2)
{
    if(c1>='A' && c1<='Z')
        c1 = (char)(c1-'A'+'a');    
    if(c2>='A' && c2<='Z')
        c2 = (char)(c2-'A'+'a');
    return c1==c2;
       
}

回文串的判断和搜索是面试中经常遇到的基本问题，我在Facebook的面试中就遇到了这道题。

在LeetCode中类似的题目还有Palindrome Number，Longest Palindromic Substring，Palindrome Partitioning等，

Palindrome Number跟这道题比较类似，只是判断的是整数，而后面两道还是有一定难度的，有兴趣的朋友可以看看哈。




