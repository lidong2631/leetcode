The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example:

Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]


Python:
class Solution:
    def totalNQueens(self, n):
        """
        :type n: int
        :rtype: int
        """
        self.total = 0      
        self.board = [-1 for i in range(n)]
        self.dfs(0, n)
        return self.total

    def check(self, k, j):
        for i in range(k):
            if self.board[i] == j or abs(k-i) == abs(self.board[i] - j):
                return False
        return True
        
    def dfs(self, depth, n):
        if depth == n:
            self.total += 1
            return
        for i in range(n):
            if self.check(depth, i):
                self.board[depth] = i
                self.dfs(depth+1, n)

Note: 此解法跟上一题几乎一样 只是换用total来记录解法数目 另本解法开始total总是报 local variable 'total' referenced before assignment



Java:
public class Solution {
    public int totalNQueens(int n) {
        int[] res = new int[1];
        int[] colForRow = new int[n];
        helper(res, n, 0, colForRow);
        return res[0];
    }
    
    private void helper(int[] res, int n, int r, int[] colForRow) {
        if (r == n) {
            res[0]++;
            return;
        }
        for (int i = 0; i < n; i++) {
            colForRow[r] = i;
            if (isValid(r, i, colForRow))
                helper(res, n, r+1, colForRow);
        }
    }
    
    private boolean isValid(int row, int col, int[] colForRow) {
        for (int i = 0; i < row; i++) {
            if (col == colForRow[i] || row - i == Math.abs(col - colForRow[i]))
                return false;
        }
        return true;
    }
}




from code ganker:

这道题跟N-Queens算法是完全一样的，只是把输出从原来的结果集变为返回结果数量而已。思路我们就不在赘述了，大家可以参见N-Queens，

算法的时间复杂度仍然是指数量级的，空间复杂度是O(n)。代码如下：

public int totalNQueens(int n) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    res.add(0);
    helper(n,0,new int[n],res);
    return res.get(0);
}
private void helper(int n, int row, int[] columnForRow, ArrayList<Integer> res)
{
    if(row==n)
    {
        res.set(0,res.get(0)+1);
        return;
    }
    for(int i=0;i<n;i++)
    {
        columnForRow[row]=i;
        if(check(row,columnForRow))
        {
            helper(n,row+1,columnForRow,res);
        }
    }
}
private boolean check(int row, int[] columnForRow)
{
    for(int i=0;i<row;i++)
    {
        if(columnForRow[i]==columnForRow[row] || Math.abs(columnForRow[row]-columnForRow[i])==row-i)
            return false;
    }
    return true;
}

这道题目我个人没有看到从输出结果集变为输出结果数量有什么可提升的空间，不像Unique Paths，输出结果集还是数量是有不同复杂度的解法的。如果这个题大家有什么更优的解法，

可以留言或者发邮件到linhuanmars@gmail.com给我交流一下哈。