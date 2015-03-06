class Solution:
    # @param num, a list of integer
    # @return a list of lists of integer
    def subsetsWithDup(self, S):
        def dfs(level, start, list):
            if list not in res:         #同上一题一样 只不过多加这一句剪枝条件
                res.append(list)
            if level == len(S):
                return
            for i in range(start, len(S)):
                dfs(level+1, i+1, list+[S[i]])
        S.sort()
        res = []
        dfs(0, 0, [])
        return res





题意：

Given a collection of integers that might contain duplicates, S, return all possible subsets.

Note:

•Elements in a subset must be in non-descending order.
•The solution set must not contain duplicate subsets.
 

For example,
If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]解题思路：和上一道题一样，求一个集合的所有子集。和上一道题不一样的一点是集合可能有重复元素。这道题同样使用dfs来解题，只是需要在dfs函数里加一个剪枝的条件，排除掉同样的子集。

代码：


class Solution:
    # @param num, a list of integer
    # @return a list of lists of integer
    def subsetsWithDup(self, S):
        def dfs(depth, start, valuelist):
            if valuelist not in res: res.append(valuelist)
            if depth == len(S): return
            for i in range(start, len(S)):
                dfs(depth+1, i+1, valuelist+[S[i]])
        S.sort()
        res = []
        dfs(0, 0, [])
        return res