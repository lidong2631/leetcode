class Solution:
    # @return a string
    def minWindow(self, S, T):
        GlobalDict = {}; tmpDict = {}
        for i in T:
            if i not in GlobalDict:
                GlobalDict[i] = 1
            else:
                GlobalDict[i]+=1
        tmpDict = GlobalDict
        start, end, count, minSize, minStart = 0, 0, len(T), 100000, 0
        for end in range(len(S)):
            if S[end] in GlobalDict:
                tmpDict[S[end]]-=1
                if tmpDict[S[end]] >= 0:
                    count-=1
            if count == 0:
                while True:
                    if S[start] in GlobalDict:
                        if tmpDict[S[start]] < 0:
                            tmpDict[S[start]]+=1
                        else:
                            break
                    start+=1
                if minSize > end - start + 1:
                    minSize = end - start + 1
                    minStart = start
        if minSize == 100000:
            return ''
        return S[minStart:minStart+minSize]





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
    public String minWindow(String S, String T) {
        if(S==null || S.length()==0 || T==null || T.length()==0)
            return "";
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i=0; i<T.length(); i++)             //先将字典里的字映射到map里 map value为对应字符在字典出现了几次
        {
            if(map.containsKey(T.charAt(i)))
                map.put(T.charAt(i), map.get(T.charAt(i))+1);
            else
                map.put(T.charAt(i), 1);
        }
        int leftP = 0; int count = 0; int minLen = S.length()+1;        //记得minLen初始值为S.length()+1
        String res = "";
        for(int i=0; i<S.length(); i++)
        {
            if(map.containsKey(S.charAt(i)))
            {
                map.put(S.charAt(i), map.get(S.charAt(i))-1);   //每次发现一个在字典里的字符 将字典对应的value－1
                if(map.get(S.charAt(i))>=0)   //如果字典中此字符的值仍大于等于0 则是有效的构成子串的字符count应加1 否则小于0说明此字符的数量已超过子串需要的数量 我们不能将count+1
                    count++;
                while(count==T.length())                //如果count等于T.length()则我们已经在S中凑齐字典里的词 开始移动左窗口
                {
                    if(map.containsKey(S.charAt(leftP)))    //如果字典有这个字符则要做对应处理 否则不是子串的字符我们可以跳过
                    {
                        map.put(S.charAt(leftP), map.get(S.charAt(leftP))+1);   //开始压缩 先把map中对应的值＋1 说明左窗口移动过了此字符
                        if(map.get(S.charAt(leftP))>0)  //如果此时字典中此字符的数量大于0 说明已经压缩到头 否则如果小于等于0说明仍有大于等于1个此字符在两指针中间
                        {
                            if(minLen>i-leftP+1)        //满足上面条件就可以看一下当前长度是否小于minLen 是就更新结果
                            {
                                res = S.substring(leftP, i+1);  //更新结果字符串res和minLen长度
                                minLen = i-leftP+1;
                            }
                            count--;        //count减1 可以跳出111行的循环了
                        }
                    }
                    leftP++;
                }
            }
        }
        return res;
    }
}
Note: 根据code ganker版写的 思路还是蛮清楚的 只是细节上要多注意 这种题大体思路就是维护一个窗口 然后左右窗口边选择其一移动






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






