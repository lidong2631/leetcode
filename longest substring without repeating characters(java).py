class Solution:
    # @return an integer
    def lengthOfLongestSubstring(self, s):
        start = 0
        maxLen = 0
        dict = {}                   #维护一个哈希表
        for i in range(len(s)):         #遍历一遍字符串 将对应字符在哈希表中的值設为－1
            dict[s[i]] = -1
        for i in range(len(s)):         #遍历一遍字符串
            if dict[s[i]]!=-1:              #如果是重复字符
                while start <= dict[s[i]]:      #从start位置开始到重复字符第一次出现的位置结束将中间所有字符在哈希表的值设回为－1
                    dict[s[start]] = -1
                    start+=1
            dict[s[i]] = i                  #如果该字符第一次出现 将其在哈希表的值设为它的位置i
            maxLen = i - start + 1 if i - start + 1 > maxLen else maxLen        #维护一个变量maxLen 如果当前长度大于maxLen就更新它
        return maxLen

Note: 这题的思想在字符串题中很重要 要记住 上面的解跟下面code ganker讲的一样 扩展可以为返回最长的字母相同的substring





题意：Given a string, find the length of the longest substring without repeating characters. 

For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. 

For "bbbbb" the longest substring is "b", with the length of 1.

解题思路：使用一个哈希表，记录字符的索引。例如对于字符串'zwxyabcabczbb'，当检测到第二个'a'时，由于之前已经有一个'a'了，

所以应该从第一个a的下一个字符重新开始测算长度，但是要把第一个a之前的字符在哈希表中对应的值清掉，如果不清掉的话，就会误以为还存在重复的。

比如检测到第二个'z'时，如果第一个'z'对应的哈希值还在，那就出错了，所以要把第一个'a'之前的字符的哈希值都重置才行。

代码：

复制代码
class Solution:
    # @return an integer
    def lengthOfLongestSubstring(self, s):
        start = 0
        maxlen = 0
        hashtable = [-1 for i in range(256)]
        for i in range(len(s)):
            if hashtable[ord(s[i])] != -1:
                while start <= hashtable[ord(s[i])]:
                    hashtable[ord(s[start])] = -1
                    start += 1
            if i - start + 1 > maxlen: maxlen = i - start + 1
            hashtable[ord(s[i])] = i
        return maxlen
复制代码
复制代码
class Solution:
    # @return an integer
    def lengthOfLongestSubstring(self, s):
        start = 0
        maxlen = 0
        dict = {}
        for i in range(len(s)):
            dict[s[i]] = -1
        for i in range(len(s)):
            if dict[s[i]] != -1:
                while start <= dict[s[i]]:
                    dict[s[start]] = -1
                    start += 1
            if i - start + 1 > maxlen: maxlen = i - start + 1
            dict[s[i]] = i
        return maxlen






public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s==null || s.length()==0)
            return 0;
        int left = 0; int right = 0; int maxLen = 0;
        HashSet<Character> set = new HashSet<Character>();
        while(right<s.length())
        {
            if(set.contains(s.charAt(right)))   //如果是重复字符 移动right不会得到更好结果 要移动left
            {
                if(maxLen<right-left)           //先更新一下maxLen
                    maxLen = right - left;
                while(s.charAt(left)!=s.charAt(right))  //只要left和right指向的字符不是同一个字符 说明跟right重复的字符在left后面
                {                                       //可以将left字符从set中remove 同时left跳到下一位 因为里面不会有更好的结果
                    set.remove(s.charAt(left));
                    left++;
                }
                left++;                             //如果left和right指向同一个字符(98行) left和right各跳一位
            }
            else
                set.add(s.charAt(right));     //如果不是重复字符 将这个字符加到set里
            right++;                        //正常情况right移动
        }
        if(maxLen<right-left)           //最后再更新一次maxLen
            maxLen = right - left;
        return maxLen;
    }
}

Note: code ganker解法 这种解法在字符串中很常见 左右窗口移动  类似的还有minimum window, Substring with Concatenation of All Words

记得看下code ganker的评论 在cleancode book 有这个题的更简洁解法





from code ganker:

这道题用的方法是在LeetCode中很常用的方法，对于字符串的题目非常有用。 首先brute force的时间复杂度是O(n^3), 对每个substring都看看是不是有重复的字符，

找出其中最长的，复杂度非常高。优化一些的思路是稍微动态规划一下，每次定一个起点，然后从起点走到有重复字符位置，过程用一个HashSet维护当前字符集，

认为是constant操作，这样算法要进行两层循环，复杂度是O(n^2)。 

最后，我们介绍一种线性的算法，也是这类题目最常见的方法。基本思路是维护一个窗口，每次关注窗口中的字符串，在每次判断中，左窗口和右窗口选择其一向前移动。

同样是维护一个HashSet, 正常情况下移动右窗口，如果没有出现重复则继续移动右窗口，如果发现重复字符，则说明当前窗口中的串已经不满足要求，


继续移动有窗口不可能得到更好的结果，此时移动左窗口，直到不再有重复字符为止，中间跳过的这些串中不会有更好的结果，因为他们不是重复就是更短。

为左窗口和右窗口都只向前，所以两个窗口都对每个元素访问不超过一遍，因此时间复杂度为O(2*n)=O(n),是线性算法。空间复杂度为HashSet的size,也是O(n). 代码如下：

public int lengthOfLongestSubstring(String s) {
    if(s==null || s.length()==0)
        return 0;
    HashSet<Character> set = new HashSet<Character>();
    int max = 0;
    int walker = 0;
    int runner = 0;
    while(runner<s.length())
    {
        if(set.contains(s.charAt(runner)))
        {
            if(max<runner-walker)
            {
                max = runner-walker;
            }
            while(s.charAt(walker)!=s.charAt(runner))
            {
                set.remove(s.charAt(walker));
                walker++;
            }
            walker++;
        }
        else
        {
            set.add(s.charAt(runner));
        }
        runner++;
    }
    max = Math.max(max,runner-walker);
    return max;
}

这道题思想在字符串处理的题中还是比较重要的，实现上主要是HashSet和数组index的操作。

扩展的题目有Substring with Concatenation of All Words，Minimum Window Substring，思路是非常接近的，只是操作上会更加繁琐一些。
