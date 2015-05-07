class Solution:
    def check(self, s, dict):               #check函数 判断s是否可以被dict中单词表示出来 同上一题word break
        dp = [False for i in range(len(s)+1)]
        dp[0] = True
        for i in range(1, len(s)+1):
            for k in range(i):
                if dp[k] and s[k:i] in dict:
                    dp[i] = True
        return dp[len(s)]
    
    def dfs(self, s, dict, stringList):
        if self.check(s, dict):         #如果check可以得到结果才继续 否则直接跳过这个dfs
            if len(s) == 0:             #如果最终s的长度为0 说明我们得到一个解 将它append到res中 注意是stringList[1:] 因为第一个字符是空格要跳过
                Solution.res.append(stringList[1:])
            for i in range(1, len(s)+1):        #这里递归dfs 求所有结果
                if s[:i] in dict:
                    self.dfs(s[i:], dict, stringList + ' ' + s[:i])     #要注意加空格
    
    # @param s, a string
    # @param dict, a set of string
    # @return a list of strings
    def wordBreak(self, s, dict):
        Solution.res = []
        self.dfs(s, dict, '')
        return Solution.res

Note：此解法对应于code ganker的brute force解法






原题地址：https://oj.leetcode.com/problems/word-break-ii/

题意：

Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

解题思路：这道题不只像word break那样判断是否可以分割，而且要找到所有的分割方式，那么我们就要考虑dfs了。不过直接用dfs解题是不行的，为什么？

因为决策树太大，如果全部遍历一遍，时间复杂度太高，无法通过oj。那么我们需要剪枝，如何来剪枝呢？使用word break题中的动态规划的结果，

在dfs之前，先判定字符串是否可以被分割，如果不能被分割，直接跳过这一枝。实际上这道题是dp+dfs。

代码：

复制代码
class Solution:
    # @param s, a string
    # @param dict, a set of string
    # @return a list of strings
    def check(self, s, dict):
        dp = [False for i in range(len(s)+1)]
        dp[0] = True
        for i in range(1, len(s)+1):
            for k in range(0, i):
                if dp[k] and s[k:i] in dict:
                    dp[i] = True
        return dp[len(s)]
        
    def dfs(self, s, dict, stringlist):
        if self.check(s, dict):
            if len(s) == 0: Solution.res.append(stringlist[1:])
            for i in range(1, len(s)+1):
                if s[:i] in dict:
                    self.dfs(s[i:], dict, stringlist+' '+s[:i])
    
    def wordBreak(self, s, dict):
        Solution.res = []
        self.dfs(s, dict, '')
        return Solution.res





public class Solution {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> res = new ArrayList<String>();
        if(s==null || s.length()==0)
            return res;
        helper(s, wordDict, 0, "", res);
        return res;
    }
    
    private void helper(String s, Set<String> wordDict, int start, String item, List<String> res) {
        if(start>=s.length()) {
            res.add(item);
            return;
        }
        StringBuilder tmp = new StringBuilder();
        for(int i=start; i<s.length(); i++) {
            tmp.append(s.charAt(i));
            if(wordDict.contains(tmp.toString())) {
                String newItem = item.length()>0?item+" "+tmp.toString():tmp.toString();
                helper(s, wordDict, i+1, newItem, res);
            }
        }
    }
}

用NP套路的递归解法即可 看code ganker评论里面有简练版的dp解



ppublic class Solution {
    public List<String> wordBreak(String s, Set<String> dict) {
        if(s==null || s.length()==0) return null;
        ArrayList<String> res = new ArrayList<String>();
        helper(s, dict, 0, "", res);
        return res;
    }
    
    private void helper(String s, Set<String> dict, int start, String tmpRes, ArrayList<String> res) {
        if(wordBreakI(s, dict))
        {
            if(start>=s.length())
            {
                res.add(tmpRes);
                return;
            }
            StringBuilder sb = new StringBuilder();
            for(int i=start; i<s.length(); i++)     //注意是i从start开始循环
            {
                sb.append(s.charAt(i));     //每次循环sb都会加上新的一个字符
                if(dict.contains(sb.toString()))
                {
                    String newtmpRes = start==0 ? sb.toString() : tmpRes+ " " + sb.toString();  //判断是不是第一个加字符串
                    helper(s, dict, i+1, newtmpRes, res);   //i+1
                }
            }
        }
    }
    
    public boolean wordBreakI(String s, Set<String> dict) {
        if(s==null || s.length()==0) return false;
        int strLen = s.length();
        boolean[] dp = new boolean[strLen+1];
        dp[0] = true;
        for(int i=1; i<strLen+1; i++)
        {
            for(int j=0; j<=i; j++)
            {
                if(dp[j] && dict.contains(s.substring(j, i)))
                {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[strLen];
    }
}

Note: 严重注意 java substring() IndexOutOfBoundsException - if beginIndex or endIndex are negative, if endIndex is greater than length(), or if beginIndex is greater than startIndex

所以在110行不可以用s.substring(i)当i大于s.length()时会报exception 而递归的结束条件s.length()==0永远也不会执行到 这里跟python不一样 要注意

NP问题套路






from code ganker:

这道题目要求跟Word Break比较类似，不过返回的结果不仅要知道能不能break，如果可以还要返回所有合法结果。

一般来说这种要求会让动态规划的效果减弱很多，因为我们要在过程中记录下所有的合法结果，中间的操作会使得算法的复杂度不再是动态规划的两层循环，

因为每次迭代中还需要不是constant的操作，最终复杂度会主要取决于结果的数量，而且还会占用大量的空间，因为不仅要保存最终结果，

包括中间的合法结果也要一一保存，否则后面需要历史信息会取不到。所以这道题目我们介绍两种方法，一种是直接brute force用递归解，

另一种是跟Word Break思路类似的动态规划。

对于brute force解法，代码比较简单，每次维护一个当前结果集，然后遍历剩下的所有子串，如果子串在字典中出现，则保存一下结果，

并放入下一层递归剩下的字符。思路接近于我们在N-Queens这些NP问题中经常用到的套路。代码如下：

public ArrayList<String> wordBreak(String s, Set<String> dict) {
    ArrayList<String> res = new ArrayList<String>();
    if(s==null || s.length()==0)
        return res;
    helper(s,dict,0,"",res);
    return res;
}
private void helper(String s, Set<String> dict, int start, String item, ArrayList<String> res)
{
    if(start>=s.length())
    {
        res.add(item);
        return;
    }
    StringBuilder str = new StringBuilder();
    for(int i=start;i<s.length();i++)
    {
        str.append(s.charAt(i));
        if(dict.contains(str.toString()))
        {
            String newItem = item.length()>0?(item+" "+str.toString()):str.toString();
            helper(s,dict,i+1,newItem,res);
        }
    }
}


接下来我们列出动态规划的解法，递推式跟Word Break是一样的，只是现在不只要保存true或者false，还需要保存true的时候所有合法的组合，

以便在未来需要的时候可以得到。不过为了实现这点，代码量就增大很多，需要一个数据结构来进行存储，同时空间复杂度变得非常高，

因为所有中间组合都要一直保存。时间上还是有提高的，就是当我们需要前面到某一个元素前的所有合法组合时，我们不需要重新计算了。

不过最终复杂度还是主要取决于结果的数量。代码如下：

class Interval {
    int start;
    int end;
    public Interval(int start, int end) {
        this.start = start; this.end = end;
    }
    public Interval(Interval i) {
        start = i.start;
        end = i.end;
 }
}
ArrayList<ArrayList<Interval>> deepCopy(ArrayList<ArrayList<Interval>> paths) {
    if (paths==null) return null;
    ArrayList<ArrayList<Interval>> res = new ArrayList<ArrayList<Interval>>(paths.size());
    for (int i=0; i<paths.size(); i++) {
     ArrayList<Interval> path = paths.get(i);
     ArrayList<Interval> copy = new ArrayList<Interval>(path.size());
        for (int j=0; j<path.size(); j++) {
         copy.add(new Interval(path.get(j)));
     }
     res.add(copy);
    }
    return res;
}
public ArrayList<String> wordBreak(String s, Set<String> dict) {
    ArrayList<String> res = new ArrayList<String>();
    if (s==null || s.length()==0) return res;
    Map<Integer, ArrayList<ArrayList<Interval>>> dp = new HashMap<Integer, ArrayList<ArrayList<Interval>>>();
    dp.put(0, new ArrayList<ArrayList<Interval>>());
    dp.get(0).add(new ArrayList<Interval>());
    for (int i=1; i<=s.length(); i++) {
        for (int j=0; j<i; j++) {
            String cur = s.substring(j, i);
            ArrayList<ArrayList<Interval>> paths = null;
            if (dp.containsKey(j) && dict.contains(cur)) {
                paths = deepCopy(dp.get(j));
                Interval interval = new Interval(j, i);
                for (ArrayList<Interval> path:paths) {
                    path.add(interval);
                }
            }
            if (paths != null) {
                if (!dp.containsKey(i)) {
                    dp.put(i, paths);
                } 
                else {
              dp.get(i).addAll(paths);
             }
            }
        }
    }
    if (!dp.containsKey(s.length())) {
        return res;
    }
    ArrayList<ArrayList<Interval>> paths = dp.get(s.length());
    for (int j=0; j<paths.size(); j++) {
        ArrayList<Interval> path = paths.get(j);
        StringBuilder str = new StringBuilder();
        for (int i=0; i<path.size(); i++) {
            if (i!=0) {str.append(" ");}
            int start = path.get(i).start;
            int end = path.get(i).end;
            str.append(s.substring(start, end));
        }
        res.add(str.toString());
    }
    return res;
}

可以看出，用动态规划的代码复杂度要远远高于brute force的解法，而且本质来说并没有很大的提高，甚至空间上还是一个暴涨的情况。

所以这道题来说并不是一定要用动态规划，有一个朋友在面Amazon时遇到这道题，面试官并没有要求动态规划，用brute force即可，

不过两种方法时间上和空间上的优劣还是想清楚比较好，面试官可能想听听理解。实现的话可能主要是递归解法。

还有一点需要指出的是，上面的两个代码放到LeetCode中都会超时，原因是LeetCode中有一个非常tricky的测试case，其实是不能break的，

但是又很长，出现大量的记录和回溯，因此一般通过这个的解法是把Word Break先跑一遍，判断是不是能break，如果可以再跑上面的代码。

这样做法其实比较傻，但是为了过这个只能这样了，这一点我觉得LeetCode没必要把超时设得这么严格，实际意义不大，只是把AC率给拉了下来
