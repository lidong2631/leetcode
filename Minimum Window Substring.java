题意：

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"

Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.

解题思路：双指针思想，尾指针不断往后扫，当扫到有一个窗口包含了所有T的字符，然后再收缩头指针，直到不能再收缩为止。最后记录所有可能的情况中窗口最小的。

代码：

复制代码
class Solution:
    # @return a string
    def minWindow(self, S, T):
        count1={}; count2={}
        for char in T:
            if char not in count1: count1[char]=1
            else: count1[char]+=1
        for char in T:
            if char not in count2: count2[char]=1
            else: count2[char]+=1
        count=len(T)
        start=0; minSize=100000; minStart=0
        for end in range(len(S)):
            if S[end] in count2 and count2[S[end]]>0:
                count1[S[end]]-=1
                if count1[S[end]]>=0:
                    count-=1
            if count==0:
                while True:
                    if S[start] in count2 and count2[S[start]]>0:
                        if count1[S[start]]<0:
                            count1[S[start]]+=1
                        else:
                            break
                    start+=1
                if minSize>end-start+1:
                    minSize=end-start+1; minStart=start
        if minSize==100000: return ''
        else:
            return S[minStart:minStart+minSize]



public class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        int minLen = s.length()+1, left = 0, count = 0;
        String res = "";
        for (char c : t.toCharArray()) {
            if (map.containsKey(c))
                map.put(c, map.get(c)+1);
            else map.put(c, 1);
        }
        for (int right = 0; right < s.length(); right++) {
            char cR = s.charAt(right);
            if (map.containsKey(cR)) {
                map.put(cR, map.get(cR)-1);
                if (map.get(cR) >= 0)
                    count++;
            }
            while (count == t.length()) {
                char cL = s.charAt(left);
                if (map.containsKey(cL)) {
                    map.put(cL, map.get(cL)+1);
                    if (map.get(cL) > 0) {
                        if (minLen > right - left + 1) {
                            minLen = right - left + 1;
                            res = s.substring(left, right + 1);
                        }
                        count--;
                    } 
                }
                left++;
            }
        }
        return res;
    }
}

O(n)


from code ganker:

这道题是字符串处理的题目，和Substring with Concatenation of All Words思路非常类似，同样是建立一个字典，然后维护一个窗口。区别是在这道题目中，

因为可以跳过没在字典里面的字符（也就是这个串不需要包含且仅仅包含字典里面的字符，有一些不在字典的仍然可以满足要求），所以遇到没在字典里面的字符可以继续移动窗口右端，

而移动窗口左端的条件是当找到满足条件的串之后，一直移动窗口左端直到有字典里的字符不再在窗口里。在实现中就是维护一个HashMap，一开始key包含字典中所有字符，

value就是该字符的数量，然后遇到字典中字符时就将对应字符的数量减一。算法的时间复杂度是O(n),其中n是字符串的长度，因为每个字符再维护窗口的过程中不会被访问多于两次。

空间复杂度则是O(字典的大小)，也就是代码中T的长度。代码如下：

public String minWindow(String S, String T) {
    if(S == null || T == null || S.length()==0 || T.length()==0)
        return "";
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    for(int i=0;i<T.length();i++)
    {
        if(map.containsKey(T.charAt(i)))
        {
            map.put(T.charAt(i), map.get(T.charAt(i))+1);
        }
        else
            map.put(T.charAt(i), 1);
    }
    int count = 0;
    int pre = 0;
    String res = "";
    int minLen = S.length()+1;
    for(int i=0;i<S.length();i++)
    {
        if(map.containsKey(S.charAt(i)))
        {
            map.put(S.charAt(i),map.get(S.charAt(i))-1);
            if(map.get(S.charAt(i))>=0)
                count++;
            while(count == T.length())
            {
                if(map.containsKey(S.charAt(pre)))
                {
                    map.put(S.charAt(pre),map.get(S.charAt(pre))+1);
                    if(map.get(S.charAt(pre))>0)
                    {
                        if(minLen>i-pre+1)
                        {
                            res = S.substring(pre,i+1);
                            minLen = i-pre+1;
                        }
                        count--;
                    }
                }
                pre++;
            }
        }
    }
    return res;
}

这个方法在Substring with Concatenation of All Words和Longest Substring Without Repeating Characters中都介绍过，属于一种类型的题目，

只要掌握了思路便可以举一反三，都可以将这类问题降低到线性复杂度






