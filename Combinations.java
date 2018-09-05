Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]



Python:
class Solution:
    def combine(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: List[List[int]]
        """
        res = []
        self.helper(res, n, k, 1, [])
        return res
    
    def helper(self, res, n, k, i, tmp):
        if len(tmp) == k:
            res.append(tmp[:])
            return

        for j in range(i, n+1):
            tmp.append(j)
            self.helper(res, n, k, j+1, tmp)
            tmp.pop()




Java:
public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        helper(res, n, k, 1, new ArrayList<Integer>());
        return res;
    }
    
    private void helper(List<List<Integer>> res, int n, int k, int index, List<Integer> list) {
        if (list.size() == k) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = index; i <= n; i++) {
            list.add(i);
            helper(res, n, k, i+1, list);
            list.remove(list.size()-1);
        }
    }
}



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
