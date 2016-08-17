题意：

Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]解题思路：回文的分割问题。同样是需要穷举出所有符合条件的集合，那么我们还是使用dfs。

代码：


class Solution:
    # @param s, a string
    # @return a list of lists of string
    def isPalindrome(self, s):
        for i in range(len(s)):
            if s[i] != s[len(s)-1-i]: return False
        return True
    
    def dfs(self, s, stringlist):
        if len(s) == 0: Solution.res.append(stringlist)
        for i in range(1, len(s)+1):
            if self.isPalindrome(s[:i]):
                self.dfs(s[i:], stringlist+[s[:i]])
            
    def partition(self, s):
        Solution.res = []
        self.dfs(s, [])
        return Solution.res




public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        helper(s, getDict(s), 0, new ArrayList<String>(), res);
        return res;
    }
    
    private void helper(String s, boolean[][] dict, int index, List<String> list, List<List<String>> res) {
        if (index == s.length()) {
            res.add(new ArrayList<String>(list));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (dict[index][i]) {
                list.add(s.substring(index, i + 1));
                helper(s, dict, i + 1, list, res);
                list.remove(list.size() - 1);
            }
        }
    }
    
    private boolean[][] getDict(String s) {
        boolean[][] dict = new boolean[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(j) == s.charAt(i) && (j - i < 2 || dict[i+1][j-1])) dict[i][j] = true;     // careful put j-i>2 first
            }
        }
        return dict;
    }
}

Word Break II + Longest Palindromic Substring



from code ganker:

这道题是求一个字符串中回文子串的切割，并且输出切割结果，其实是Word Break II和Longest Palindromic Substring结合，

该做的我们都做过了。首先我们根据Longest Palindromic Substring中的方法建立一个字典，得到字符串中的任意子串是不是回文串的字典，

不熟悉的朋友可以先看看哈。接下来就跟Word Break II一样，根据字典的结果进行切割，然后按照循环处理递归子问题的方法，

如果当前的子串满足回文条件，就递归处理字符串剩下的子串。如果到达终点就返回当前结果。算法的复杂度跟Word Break II一样，取决于结果的数量，

最坏情况是指数量级的。代码如下：

public ArrayList<ArrayList<String>> partition(String s) {
    ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
    if(s==null || s.length()==0)
        return res;
    helper(s, getDict(s),0,new ArrayList<String>(), res);
    return res;
}
private void helper(String s, boolean[][] dict, int start, ArrayList<String> item, ArrayList<ArrayList<String>> res)
{
    if(start==s.length())
    {
        res.add(new ArrayList<String>(item));
        return;
    }
    for(int i=start;i<s.length();i++)
    {
        if(dict[start][i])
        {
            item.add(s.substring(start,i+1));
            helper(s,dict,i+1,item,res);
            item.remove(item.size()-1);
        }
    }
}
private boolean[][] getDict(String s)
{
    boolean[][] dict = new boolean[s.length()][s.length()];
    for(int i=s.length()-1;i>=0;i--)
    {
        for(int j=i;j<s.length();j++)
        {
            if(s.charAt(i)==s.charAt(j) && ((j-i<2)||dict[i+1][j-1]))
            {
                dict[i][j] = true;
            }
        }
    }
    return dict;
}

同样，这里同Word Break II一样也可以使用动态规划的方法，但是要对所有中间结果进行存储，花费大量的空间，这里就不列举代码了。

这道题扩展还有Palindrome Partitioning II，虽然求解的问题类似，但是因为一些细节的不同，复杂度会有很大的变化，有兴趣的朋友可以看看哈