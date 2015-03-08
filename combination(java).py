<<<<<<< HEAD
class Solution:
    # @return a list of lists of integers
    def combine(self, n, k):
        def dfs(level, start, list):
            if level == k:
                res.append(list)
                return
            for i in range(start, n):
                dfs(level+1, i+1, list+[i+1])   
        res = []
        dfs(0, 0, [])
        return res

class Solution:
    # @return a list of lists of integers
    def combine(self, n, k):
        def dfs(level, start, list):
            if level == k:
                res.append(list)
                return
            for i in range(start, n):
                dfs(level+1, i+1, list+[S[i]])
        res = []
        S = [i for i in range(1, n+1)]
        dfs(0, 0, [])
        return res

Note: 套用subsets那题的解法 同样使用dfs 两个解法区别不大 第一个不用建立list S 稍微简练一些





题意：组合求解问题。

解题思路：这种求组合的问题，需要使用dfs来解决。

代码：


class Solution:
    # @return a list of lists of integers
    def combine(self, n, k):
        def dfs(start, valuelist):
            if self.count == k: ret.append(valuelist); return
            for i in range(start, n + 1):
                self.count += 1
                dfs(i + 1, valuelist + [i])
                self.count -= 1
        ret = []; self.count = 0
        dfs(1, [])
        return ret






public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(n<0 || n<k)
            return res;
        List<Integer> item = new ArrayList<Integer>();
        helper(n, k, 1, item, res);                 //从1开始
        return res;
    }
    
    private void helper(int n, int k, int start, List<Integer> item, List<List<Integer>> res) {
        if(item.size()==k)      //如果item到了k 就将结果加到res中 返回
        {
            res.add(new ArrayList(item));
            return;
        }
        for(int i=start; i<=n; i++)         //每次从start开始循环到n
        {
            item.add(i);
            helper(n, k, i+1, item, res);
            item.remove(item.size()-1);
        }
    }
}

Note: NP问题






from code ganker:

这道题是NP问题的题目，时间复杂度没办法提高，用到的还是N-Queens中的方法：用一个循环递归处理子问题。实现的代码跟Combination Sum非常类似而且更加简练，

因为不用考虑重复元素的情况，而且元素只要到了k个就可以返回一个结果。代码如下：

public ArrayList<ArrayList<Integer>> combine(int n, int k) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if(n<=0 || n<k)
        return res;
    helper(n,k,1,new ArrayList<Integer>(), res);
    return res;
}
private void helper(int n, int k, int start, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res)
{
    if(item.size()==k)
    {
        res.add(new ArrayList<Integer>(item));
        return;
    }
    for(int i=start;i<=n;i++)
    {
        item.add(i);
        helper(n,k,i+1,item,res);
        item.remove(item.size()-1);
    }
}

NP问题在LeetCode中出现的频率很高，例如N-Queens，Sudoku Solver，Combination Sum，Permutations等等。

不过这类题目都是用一种方法而且也没有办法有时间上的提高，所以还是比较好掌握的。
=======
class Solution:
    # @return a list of lists of integers
    def combine(self, n, k):
        def dfs(level, start, list):
            if level == k:
                res.append(list)
                return
            for i in range(start, n):
                dfs(level+1, i+1, list+[i+1])   
        res = []
        dfs(0, 0, [])
        return res

class Solution:
    # @return a list of lists of integers
    def combine(self, n, k):
        def dfs(level, start, list):
            if level == k:
                res.append(list)
                return
            for i in range(start, n):
                dfs(level+1, i+1, list+[S[i]])
        res = []
        S = [i for i in range(1, n+1)]
        dfs(0, 0, [])
        return res

Note: 套用subsets那题的解法 同样使用dfs 两个解法区别不大 第一个不用建立list S 稍微简练一些





题意：组合求解问题。

解题思路：这种求组合的问题，需要使用dfs来解决。

代码：


class Solution:
    # @return a list of lists of integers
    def combine(self, n, k):
        def dfs(start, valuelist):
            if self.count == k: ret.append(valuelist); return
            for i in range(start, n + 1):
                self.count += 1
                dfs(i + 1, valuelist + [i])
                self.count -= 1
        ret = []; self.count = 0
        dfs(1, [])
        return ret






public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(n<0 || n<k)
            return res;
        List<Integer> item = new ArrayList<Integer>();
        helper(n, k, 1, item, res);                 //从1开始
        return res;
    }
    
    private void helper(int n, int k, int start, List<Integer> item, List<List<Integer>> res) {
        if(item.size()==k)      //如果item到了k 就将结果加到res中 返回
        {
            res.add(new ArrayList(item));
            return;
        }
        for(int i=start; i<=n; i++)         //每次从start开始循环到n
        {
            item.add(i);
            helper(n, k, i+1, item, res);
            item.remove(item.size()-1);
        }
    }
}

Note: NP问题






from code ganker:

这道题是NP问题的题目，时间复杂度没办法提高，用到的还是N-Queens中的方法：用一个循环递归处理子问题。实现的代码跟Combination Sum非常类似而且更加简练，

因为不用考虑重复元素的情况，而且元素只要到了k个就可以返回一个结果。代码如下：

public ArrayList<ArrayList<Integer>> combine(int n, int k) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if(n<=0 || n<k)
        return res;
    helper(n,k,1,new ArrayList<Integer>(), res);
    return res;
}
private void helper(int n, int k, int start, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res)
{
    if(item.size()==k)
    {
        res.add(new ArrayList<Integer>(item));
        return;
    }
    for(int i=start;i<=n;i++)
    {
        item.add(i);
        helper(n,k,i+1,item,res);
        item.remove(item.size()-1);
    }
}

NP问题在LeetCode中出现的频率很高，例如N-Queens，Sudoku Solver，Combination Sum，Permutations等等。

不过这类题目都是用一种方法而且也没有办法有时间上的提高，所以还是比较好掌握的。
>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
