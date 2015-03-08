<<<<<<< HEAD
class Solution:
    
    def isPalindrome(self, s):      #判断一个字符串是否为Palindrome
        for i in range(len(s)/2):
            if s[i]!=s[len(s)-1-i]: #两部 1 批半 2 两两判断是否相等
                return False
        return True
        
    def dfs(self, s, stringlist):        #dfs递归调用    当len（s）为0时即表示我们已找到一个符合条件的list 将它append到result
        if len(s) == 0:
            Solution.result.append(stringlist)
        for i in range(1, len(s)+1):            
            if self.isPalindrome(s[:i]):        #如果这个0到i的字串是回文字符串
                self.dfs(s[i:], stringlist+[s[:i]])     #则继续递归i后面的字符串 并将当前结果加到stringlist
                
    # @param s, a string
    # @return a list of lists of string
    def partition(self, s):
        Solution.result = []        #class variable存储结果
        self.dfs(s, [])
        return Solution.result


Note： 1 应记住isPalindrome的套路代码 不管是对字符串还是数字  

2 不是很明白为什么要在partition（）函数里初始化result 为什么不能直接在函数定义上就写result=[]





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




解题思路：（跟DFS深度遍历有点像!）

字符串Str = "aab";

分析了题目的数据之后，我们知道它的结果，可能是 a, a, b 或者  aa, b 这样的情况！

也就是说，我们需要去考虑 i = 1,  2 ....  n 的情况，比如

Str = "aaa"

结果集


[[a, a, a], [a, aa], [aa, a], [aaa]] 

根据这样的情况，我们用类似于DFS的算法

str1 = str.substr(0, i); 取出前面下标从 0 开始到 i 结束的子串，判断str1是否满足回文字符串的要求，

1. 满足：这样子，有可能成为一种分割的情况，所以我们new出一个list集合，把str1放入到list中，然后我们求出str剩余的子串  str2 = str.substr(i); 取出前面下标从 i 开始到整个字符串结尾的子串， 然后将str2 作为新的字符串，同时把list集合也传入到函数中，递归调用。递归的结束条件就是到传入的这个字符串的长度为0（这样就意味着已经到了字符串的末尾了），此时证明这种情况下得到的list集合是满足条件的，我们把这个list集合 加入到 结果集合result中。

2. 不满足的话，继续 i++， 直到 i == str.length();

3. 全部结束之后，返回 最终的结果集合 result







public class Solution {
    public List<List<String>> partition(String s) {         //题目要求的是返回List<List<String>>类型
        if(s==null || s.length()==0)
            return null;
        ArrayList<String> tmpItem = new ArrayList<String>();
        List<List<String>> res = new ArrayList<List<String>>();     //这里声明要和题目要求返回类型一样 new仍可以new一个ArrayList 多态 ??? 泛型里如果要求list不知道写ArrayList可不可以
        helper(s, getDict(s), 0, tmpItem, res);
        return res;
    }
    
    private void helper(String str, boolean[][] dict, int start, ArrayList<String> item, List<List<String>> res) {
        if(start==str.length())             //wordbreak II 98行 看来可以写== 感觉>=用不上
        {
            res.add(new ArrayList(item));       //这里要加new ArrayList 不太清楚为什么一定要加new ArrayList ???
            return;
        }
        for(int i=start; i<str.length(); i++)
        {
            if(dict[start][i])
            {
                item.add(str.substring(start, i+1));
                helper(str, dict, i+1, item, res);          //这里是i＋1 注意
                item.remove(item.size()-1);             //每次递归完要把元素清掉 不然会影响下次循环 ???
            }
        }
    }
    
    private boolean[][] getDict(String str) {
        boolean[][] dict = new boolean[str.length()][str.length()];
        for(int i=str.length()-1; i>=0; i--)            //这个套路要记得 外循环从后往前
        {
            for(int j=i; j<str.length(); j++)       //内循环从i往后遍历
            {
                if((str.charAt(i)==str.charAt(j)) && ((j-i<2) || dict[i+1][j-1]))       //条件
                    dict[i][j] = true;
            }
        }
        return dict;
    }
}

Note: 这题小细节太多了 被搞死了 java基础不行啊 复习要多注意 泛型要再看看

这题有几个地方没搞懂 分别是 107行泛型 115行new ArrayList 124行item.remove()




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

=======
class Solution:
    
    def isPalindrome(self, s):      #判断一个字符串是否为Palindrome
        for i in range(len(s)/2):
            if s[i]!=s[len(s)-1-i]: #两部 1 批半 2 两两判断是否相等
                return False
        return True
        
    def dfs(self, s, stringlist):        #dfs递归调用    当len（s）为0时即表示我们已找到一个符合条件的list 将它append到result
        if len(s) == 0:
            Solution.result.append(stringlist)
        for i in range(1, len(s)+1):            
            if self.isPalindrome(s[:i]):        #如果这个0到i的字串是回文字符串
                self.dfs(s[i:], stringlist+[s[:i]])     #则继续递归i后面的字符串 并将当前结果加到stringlist
                
    # @param s, a string
    # @return a list of lists of string
    def partition(self, s):
        Solution.result = []        #class variable存储结果
        self.dfs(s, [])
        return Solution.result


Note： 1 应记住isPalindrome的套路代码 不管是对字符串还是数字  

2 不是很明白为什么要在partition（）函数里初始化result 为什么不能直接在函数定义上就写result=[]





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




解题思路：（跟DFS深度遍历有点像!）

字符串Str = "aab";

分析了题目的数据之后，我们知道它的结果，可能是 a, a, b 或者  aa, b 这样的情况！

也就是说，我们需要去考虑 i = 1,  2 ....  n 的情况，比如

Str = "aaa"

结果集


[[a, a, a], [a, aa], [aa, a], [aaa]] 

根据这样的情况，我们用类似于DFS的算法

str1 = str.substr(0, i); 取出前面下标从 0 开始到 i 结束的子串，判断str1是否满足回文字符串的要求，

1. 满足：这样子，有可能成为一种分割的情况，所以我们new出一个list集合，把str1放入到list中，然后我们求出str剩余的子串  str2 = str.substr(i); 取出前面下标从 i 开始到整个字符串结尾的子串， 然后将str2 作为新的字符串，同时把list集合也传入到函数中，递归调用。递归的结束条件就是到传入的这个字符串的长度为0（这样就意味着已经到了字符串的末尾了），此时证明这种情况下得到的list集合是满足条件的，我们把这个list集合 加入到 结果集合result中。

2. 不满足的话，继续 i++， 直到 i == str.length();

3. 全部结束之后，返回 最终的结果集合 result







public class Solution {
    public List<List<String>> partition(String s) {         //题目要求的是返回List<List<String>>类型
        if(s==null || s.length()==0)
            return null;
        ArrayList<String> tmpItem = new ArrayList<String>();
        List<List<String>> res = new ArrayList<List<String>>();     //这里声明要和题目要求返回类型一样 new仍可以new一个ArrayList 多态 ??? 泛型里如果要求list不知道写ArrayList可不可以
        helper(s, getDict(s), 0, tmpItem, res);
        return res;
    }
    
    private void helper(String str, boolean[][] dict, int start, ArrayList<String> item, List<List<String>> res) {
        if(start==str.length())             //wordbreak II 98行 看来可以写== 感觉>=用不上
        {
            res.add(new ArrayList(item));       //这里要加new ArrayList 不太清楚为什么一定要加new ArrayList ???
            return;
        }
        for(int i=start; i<str.length(); i++)
        {
            if(dict[start][i])
            {
                item.add(str.substring(start, i+1));
                helper(str, dict, i+1, item, res);          //这里是i＋1 注意
                item.remove(item.size()-1);             //每次递归完要把元素清掉 不然会影响下次循环 ???
            }
        }
    }
    
    private boolean[][] getDict(String str) {
        boolean[][] dict = new boolean[str.length()][str.length()];
        for(int i=str.length()-1; i>=0; i--)            //这个套路要记得 外循环从后往前
        {
            for(int j=i; j<str.length(); j++)       //内循环从i往后遍历
            {
                if((str.charAt(i)==str.charAt(j)) && ((j-i<2) || dict[i+1][j-1]))       //条件
                    dict[i][j] = true;
            }
        }
        return dict;
    }
}

Note: 这题小细节太多了 被搞死了 java基础不行啊 复习要多注意 泛型要再看看

这题有几个地方没搞懂 分别是 107行泛型 115行new ArrayList 124行item.remove()




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

>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
这道题扩展还有Palindrome Partitioning II，虽然求解的问题类似，但是因为一些细节的不同，复杂度会有很大的变化，有兴趣的朋友可以看看哈