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




from cleanCode

问问题
Make sure you communicate with your interviewer if the string can have characters other than 'a'-'z' (Digits? Upper case letter?

Does it contain ASCII characters only? Or even unicode character sets?)

第一种解法 左右窗口思路同code ganker 默认是ASCII 用了一个256的数组存储字符 code很简洁

public int lengthOfLongestSubstring(String s) {
    boolean[] exist = new boolean[256];
    int i = 0, maxLen = 0;      //i相当于左窗口
    for(int j=0; j<s.length(); j++) {   //j相当于右窗口
        while(exist[s.charAt(j)]) {        //这里while不可以改成if 错误answer "abba"
            exist[s.charAt(i)] = false;
            i++;
        }
        exist[s.charAt(j)] = true;  //不是重复字符 将这个标记下来 并检查是否大于maxLen
        maxLen = Math.max(maxLen, j-i+1);
    }
    return maxLen;
}

扫两遍数组O(2*n) = O(n)  空间O(1)

如果不是ASCII字符 就要用set 下面第二种解法






public int lengthOfLongestSubstring(String s) {
    int[] charMap = new int[256];   //charMap里保存这个字符在s中的index
    Arrays.fill(charMap, -1);
    int i = 0, maxLen = 0;
    for(int j=0; j<s.length(); j++) {
        if(charMap[s.charAt(j)]>=i) {   //因为i初始为0 charMap里的元素都初始为-1 如果charMap里的元素大于i 说明是重复字符
            i = charMap[s.charAt(j)] + 1;   //i跳到重复字符的下一位
        }
        charMap[s.charAt(j)] = j;   //非重复字符 将这个字符在charMap中的值设为j 并更新maxLen
        maxLen = Math.max(maxLen, j-i+1);
    }
    return maxLen;
}

只需要遍历一遍数组 charMap中存的是字符在s中的索引 如果出现重复字符i直接跳到下一个字符





public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s==null || s.length()==0)
            return 0;
        HashSet<Character> set = new HashSet<Character>();
        int i = 0, maxLen = 0;
        for(int j=0; j<s.length(); j++) {
            while(set.contains(s.charAt(j))) {
                set.remove(s.charAt(i));
                i++;
            }
            set.add(s.charAt(j));
            maxLen = Math.max(maxLen, j-i+1);
        }
        return maxLen;
    }
}

用Hashset代替256位array 可以处理非ASCII情况 这题用上述3种解法就可以了 code ganker版没有这个简洁






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
