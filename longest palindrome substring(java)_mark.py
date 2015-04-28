class Solution:
    def getLongestPalindrome(self, s, left, right):
        while left >= 0 and right < len(s) and s[left] == s[right]:
            left-=1; right+=1
        return s[left+1:right]
    
    # @return a string
    def longestPalindrome(self, s):
        if s == None or len(s) == 0:
            return ''
        maxLen = 0
        palindrome = ''
        for i in range(2*len(s)-1):
            left = i/2; right = i/2
            if i%2 == 1:
                right+=1
            tmpStr = self.getLongestPalindrome(s, left, right)
            if maxLen < len(tmpStr):
                max = len(tmpStr)
                palindrome = tmpStr
        return palindrome

Note: 南郭的解法有time limit exceed 我上面解法根据code ganker第一种解法改的也没ac 应该是python运行速度比java慢吧 先这样理解思路就好

注意这题可以扩展 看code ganker解释


题意：Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, 
    
and there exists one unique longest palindromic substring.

解题思路：最长回文子串求解。

代码：

复制代码
class Solution:
    # @return a string
    def getlongestpalindrome(self, s, l, r):
        while l >= 0 and r < len(s) and s[l] == s[r]:
            l -= 1; r += 1
        return s[l+1 : r]
    def longestPalindrome(self, s):
        palindrome = ''
        for i in range(len(s)):
            len1 = len(self.getlongestpalindrome(s, i, i))
            if len1 > len(palindrome): palindrome = self.getlongestpalindrome(s, i, i)
            len2 = len(self.getlongestpalindrome(s, i, i + 1))
            if len2 > len(palindrome): palindrome = self.getlongestpalindrome(s, i, i + 1)
        return palindrome



from cleanCode
public class Solution {
    public String longestPalindrome(String s) {
        int start = 0, end = 0;
        for(int i=0; i<s.length(); i++) {
            int len1 = expand(s, i, i);
            int len2 = expand(s, i, i+1);
            int len = Math.max(len1, len2);
            if(len>end-start) {
                start = i-(len-1)/2;
                end = i+len/2;
            }
        }
        return s.substring(start, end+1);
    }
    
    private int expand(String s, int left, int right) {
        while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)) {
            left--;
            right++;
        }
        return right-left-1;
    }
}

O(n^2) O(1)


from code ganker dp Solution

public class Solution {
    public String longestPalindrome(String s) {
        boolean[][] isValid = new boolean[s.length()][s.length()];
        int maxLen = 0;
        String res = "";
        for(int i=s.length()-1; i>=0; i--) {
            for(int j=i; j<s.length(); j++) {
                if(s.charAt(i)==s.charAt(j) && (j-i<=2 || isValid[i+1][j-1])) {
                    isValid[i][j] = true;
                    if(maxLen<j-i+1) {
                        maxLen = j-i+1;
                        res = s.substring(i, j+1);
                    }
                }
            }
        }
        return res;
    }
}

O(n^2) O(n^2)

更牛逼的算法Manacher O(n)搞定

扩展 如果要返回所有最长回文串，只需要稍做变化就可以，维护一个集合，如果等于当前最大的，

即加入集合，否则，如果更长，则清空集合，加入当前这个





public class Solution {
    public String longestPalindrome(String s) {
        if(s==null || s.length()==0)
            return null;
        int left = 0;
        int right = 0;
        String tmp = "";
        String res = "";
        int maxLen = 0;
        for(int i=0; i<2*s.length()-1; i++)
        {
            left = i/2;                     //遍历每一个位置 字符或字符间的空隙 因为加上了空隙就是i 要除以2 如果是空隙right要加1
            right = i/2;
            if(i%2==1) right+=1;
            tmp = lengthOfPalin(s, left, right);    //这里注意string对象本身是不可改变的 但指向它的引用可以改变 见下面
            if(tmp.length()>maxLen)
            {
                maxLen = tmp.length();
                res = tmp;
            }
        }
        return res;
    }
    
    private String lengthOfPalin(String str, int left, int right) {
        while(left>=0 && right<str.length() && str.charAt(left) == str.charAt(right))
        {
            left--;
            right++;
        }
        return str.substring(left+1, right);
    }
}

Note: 这个解法就是code ganker第一种解法 注意char比较可以用'=='

扩展 如果要返回所有最长回文串，只需要稍做变化就可以，维护一个集合，如果等于当前最大的，即加入集合，否则，如果更长，则清空集合，加入当前这个



接上
因为String类型的对象在创建完成后实际在内存中是存放于数据空间中(data segment),改变String类型引用指向新的字符串实际是改变了String对象的引用地址，而非实际的值。

比如:String s = "abc";s = "123";内存中实际在data segment区域先初始化一个"abc"然后 将引用赋值给s这个string对象，第二句，

是再在data segment区域初始化一个"123"然后将引用传递给s，实际上并不是直接将"abc"的值改为"123",这就是问题所说的string不能改变。





from code ganker:

这道题是比较常考的题目，求回文子串，一般有两种方法。 第一种方法比较直接，实现起来比较容易理解。基本思路是对于每个子串的中心

（可以是一个字符，或者是两个字符的间隙，比如串abc,中心可以是a,b,c,或者是ab的间隙，bc的间隙）往两边同时进行扫描，直到不是回文串为止。

假设字符串的长度为n,那么中心的个数为2*n-1(字符作为中心有n个，间隙有n-1个）。对于每个中心往两边扫描的复杂度为O(n),

所以时间复杂度为O((2*n-1)*n)=O(n^2),空间复杂度为O(1)，代码如下：

public String longestPalindrome(String s) {
    if(s == null || s.length()==0)
        return "";
    int maxLen = 0;
    String res = "";
    for(int i=0;i<2*s.length()-1;i++)
    {
        int left = i/2;
        int right = i/2;
        if(i%2==1)
            right++;
        String str = lengthOfPalindrome(s,left,right);
        if(maxLen<str.length())
        {
            maxLen = str.length();
            res = str;
        }
    }
    return res;
}
private String lengthOfPalindrome(String s, int left, int right)
{
    
    while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right))
    {
        left--;
        right++;
    }
    return s.substring(left+1,right);
} 

而第二种方法是用动态规划，思路比较复杂一些，但是实现代码会比较简短。基本思路是外层循环i从后往前扫，内层循环j从i当前字符扫到结尾处。

过程中使用的历史信息是从i+1到n之间的任意子串是否是回文已经被记录下来，所以不用重新判断，只需要比较一下头尾字符即可。这种方法使用两层循环，

时间复杂度是O(n^2)。而空间上因为需要记录任意子串是否为回文，需要O(n^2)的空间，代码如下：

public String longestPalindrome(String s) {
    if(s == null || s.length()==0)
        return "";
    boolean[][] palin = new boolean[s.length()][s.length()];
    String res = "";
    int maxLen = 0;
    for(int i=s.length()-1;i>=0;i--)
    {
        for(int j=i;j<s.length();j++)
        {
            if(s.charAt(i)==s.charAt(j) && (j-i<=2 || palin[i+1][j-1]))
            {
                palin[i][j] = true;
                if(maxLen<j-i+1)
                {
                    maxLen=j-i+1;
                    res = s.substring(i,j+1);
                }
            }
        }
    }
    return res;
} 

综上所述，两种方法的时间复杂度都是O(n^2),只是第一种方法的常数会大一些。而空间上来看第一种方法是常量的，比第二种方法优。

这个题目中假设最长回文子串只有一个，实际面试中一般不做这种假设，如果要返回所有最长回文串，只需要稍做变化就可以，维护一个集合，如果等于当前最大的，

即加入集合，否则，如果更长，则清空集合，加入当前这个。实际面试会有各种变体，感觉平常还是要多想才行