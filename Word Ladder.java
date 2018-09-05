Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.




Java:
public class Solution {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int last = 1, curr = 0, level = 1;
        queue.add(beginWord);
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            String str = queue.poll();
            last--;
            for (int i = 0; i < str.length(); i++) {
                char[] strArr = str.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    strArr[i] = c;
                    String newStr = new String(strArr);
                    if (newStr.equals(endWord)) return level + 1;
                    if (wordList.contains(newStr) && !visited.contains(newStr)) {
                        curr++;
                        queue.add(newStr);
                        visited.add(newStr);
                    }
                }
            }
            if (last == 0) {
                last = curr;
                curr = 0;
                level += 1;
            }
        }
        return 0;
    }
}

时间复杂度 感觉是26*L*size(dict) 看code ganker评论

其实可以不用visited 每次在字典中找到一个单词就删除他 参考下面
http://www.cnblogs.com/TenosDoIt/p/3443512.html

Given two words (beginWord and endWord), and a dictionary word list, find the length of shortest transformation sequence from beginWord to endWord, 
such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.



from code ganker:

这道题看似一个关于字符串操作的题目，其实要解决这个问题得用图的方法。我们先给题目进行图的映射，顶点则是每个字符串，

然后两个字符串如果相差一个字符则我们进行连边。接下来看看这个方法的优势，注意到我们的字符集只有小写字母，而且字符串长度固定，假设是L。

那么可以注意到每一个字符可以对应的边则有25个（26个小写字母减去自己），那么一个字符串可能存在的边是25*L条。接下来就是检测这些边对应的字符串是否在字典里，

就可以得到一个完整的图的结构了。根据题目的要求，等价于求这个图一个顶点到另一个顶点的最短路径，

一般我们用广度优先搜索（不熟悉搜索的朋友可以看看Clone Graph）即可。这个算法中最坏情况是把所有长度为L的字符串都看一下，或者把字典中的字符串都看一下，

而长度为L的字符串总共有26^L，所以时间复杂度是O(min(26^L, size(dict))，空间上需要存储访问情况，也是O(min(26^L, size(dict))。代码如下：

public int ladderLength(String start, String end, HashSet<String> dict) {
    if(start==null || end==null || start.length()==0 || end.length()==0 || start.length()!=end.length())
        return 0;
    LinkedList<String> queue = new LinkedList<String>();
    HashSet<String> visited = new HashSet<String>();
    int level= 1;
    int lastNum = 1;
    int curNum = 0;
    queue.offer(start);
    visited.add(start);
    while(!queue.isEmpty())
    {
        String cur = queue.poll();
        lastNum--;
        for(int i=0;i<cur.length();i++)
        {
            char[] charCur = cur.toCharArray();
            for(char c='a';c<='z';c++)
            {
                charCur[i] = c;
                String temp = new String(charCur);
                if(temp.equals(end))
                    return level+1;
                if(dict.contains(temp) && !visited.contains(temp))
                {
                    curNum++;
                    queue.offer(temp);
                    visited.add(temp);
                }
            }
        }
        if(lastNum==0)
        {
            lastNum = curNum;
            curNum = 0;
            level++;
        }
    }
    return 0;
}

可以看出代码框架其实就是广度优先搜索的基本代码，就是判断边的时候需要换字符和查字典的操作，对于这些图的搜索等基本算法，还是要熟悉哈