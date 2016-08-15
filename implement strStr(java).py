题意：实现字符串匹配函数，并返回一个指针，这个指针指向原字符串中第一次出现待匹配字符串的位置。如：haystack='aabbaa'; needle='bb'。如果使用python实现，则最后返回的应该是一个字符串，即：'bbaa'。

解题思路：这道题我是使用KMP算法写的，到现在KMP算法都不是很懂，只是按照《算法导论》上的伪代码机械的实现了一遍。

代码：


class Solution:
    # @param haystack, a string
    # @param needle, a string
    # @return a string or None
    # @KMP algorithms
    def ComputePrefixFunction(self, needle):
        Pi = [0 for i in range(len(needle))]
        m = len(needle)
        Pi[0] = 0
        k = 0
        for q in range(1, m):
            while k > 0 and needle[k] != needle[q]:
                k = Pi[k - 1]
            if needle[k] == needle[q]:
                k = k + 1
            Pi[q] = k
        return Pi
    
    def strStr(self, haystack, needle):
        n = len(haystack)
        m = len(needle)
        if m == 0:
            return haystack
        Pi = self.ComputePrefixFunction(needle)
        q = 0
        for i in range(0, n):
            while q > 0 and needle[q] != haystack[i]:
                q = Pi[q - 1]
            if needle[q] == haystack[i]:
                q = q + 1
            if q == m:
                return haystack[i - m + 1:]
        return None



public class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack.length() == needle.length() && haystack.equals(needle)) return 0;
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            boolean flag = true;
            for (int j = 0; j < needle.length(); j++) {
                if (needle.charAt(j) != haystack.charAt(i+j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) return i;
        }
        return -1;
    }
}

O(mn) O(1)



From cleanCode

1 needle or haystack is empty. If needle is empty, always return 0. If haystack is empty, then there will be always be no match
unless needle is also empty which 0 is returned.
2 needle's length is greater than haystack's length. should always return -1.
3 needle is located at the end of haystack. For example, "aaaba" and "ba". Catch possible off-by-one errors.
4 needle occur multiple times in haystack. For example, "mississippi" and "issi". It should return index 2
5 Image two very long string of equal lengths = n, haystack = "aaa..aa" and needle = "aaa...ab". You should not do more than n
characters comparisons, or else your code will get TLE

public class Solution {
    public int strStr(String haystack, String needle) {
        for(int i=0; ; i++) {
            for(int j=0; ; j++) {
                if(j==needle.length())
                    return i;
                if(i+j==haystack.length())
                    return -1;
                if(needle.charAt(j)!=haystack.charAt(i+j))
                    break;
            }
        }
    }
}

这个题有时间要再看看Rabin-Karp algorithm, KMP algorithm, Boyer-Moore algorithm





from code ganker:

这是算法中比较经典的问题，判断一个字符串是否是另一个字符串的子串。这个题目最经典的算法应该是KMP算法，

不熟悉的朋友可以参见Knuth–Morris–Pratt algorithm。KMP算法是最优的线性算法，复杂度已经达到这个问题的下限。但是KMP算法比较复杂，

很难在面试的短时间里面完整正确的实现。所以一般在面试中并不要求实现KMP算法。

下面我们先说说brute force的算法，假设原串的长度是n，匹配串的长度是m。思路很简单，就是对原串的每一个长度为m的字串都判断是否跟匹配串一致。

总共有n-m+1个子串，所以算法时间复杂度为O((n-m+1)*m)=O(n*m)，空间复杂度是O(1)。代码如下：

public String strStr(String haystack, String needle) {
    if(haystack==null || needle == null || needle.length()==0)
        return haystack;
    if(needle.length()>haystack.length())
        return null;
    for(int i=0;i<=haystack.length()-needle.length();i++)
    {
        boolean successFlag = true;
        for(int j=0;j<needle.length();j++)
        {
            if(haystack.charAt(i+j)!=needle.charAt(j))
            {
                successFlag = false;
                break;
            }
        }
        if(successFlag)
            return haystack.substring(i);
    }
    return null;
}

接下来介绍一种比较容易理解的线性算法，称为rolling hash，想具体了解的朋友可以参见Rolling hash - Wikipedia。

基本思想是用一个hashcode来表示一个字符串，为了保证hash的唯一性，我们用比字符集大的素数为底，以这个素数的幂为基。举例来说，

字符集是小写字母集，取素数29为底。比如字符串abacd,转化为hashcode=1+2*29+1*29^2+3*29^3+4*29^4。然后是如何在前进一步的时候计算新的hashcode，

比如匹配串是原串是”abacde“，匹配串长度为5，根据以上的方法计算”abacd“的hashcode=h，那么下一步”bacde“的hashcode=h/29+5*29^4。

这是一个constant的操作，所以检测所有子串的时间复杂度只需要O(m+n-m)=O(n)，也是一个线性算法。代码如下：

public String strStr(String haystack, String needle) {
    if(haystack==null || needle==null) return null;
    if(haystack.length()==0){
        return needle.length()==0?"":null;
    }
    if(needle.length()==0) return haystack;
    if(haystack.length()<needle.length()) return null;

 int base = 29;
 long patternHash = 0;
    long tempBase = 1;

    for(int i=needle.length()-1; i>=0; i--){
     patternHash += (int)needle.charAt(i)*tempBase;
     tempBase *= base;
    }

    long hayHash = 0;
    tempBase = 1;
    for(int i=needle.length()-1; i>=0; i--){
     hayHash += (int)haystack.charAt(i)*tempBase;
     tempBase *= base;
    }
    tempBase /= base;

    if(hayHash == patternHash){
     return haystack;
    }

    for(int i=needle.length(); i<haystack.length(); i++){
     hayHash = (hayHash - (int)haystack.charAt(i-needle.length())*tempBase)*base+(int)haystack.charAt(i);
        if(hayHash == patternHash){
      return haystack.substring(i-needle.length()+1);
     }
    }
    return null;
} 

比较细心的朋友可能看出来了，这个方法的hashcode比较容易越界，因为以素数为底的幂会很大，解决的办法可以用BigInteger,

或者如同Rabin–Karp algorithm - Wikipedia一样对于hashcode进行取余，但是可能存在多个字符串映射到同一hashcode的问题，尽管是很少数的情况。


