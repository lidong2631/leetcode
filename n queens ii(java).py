class Solution:
    # @return an integer
    def totalNQueens(self, n):
        self.total = 0      
        self.board = [-1 for i in range(n)]
        self.dfs(0,n)
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
            if self.check(depth,i):
                self.board[depth] = i
                self.dfs(depth+1,n)

Note: 此解法跟上一题几乎一样 只是换用total来记录解法数目 另本解法开始total总是报 local variable 'total' referenced before assignment



此题还有另一种解法用非递归的方式 如下：





public class Solution {
    public int totalNQueens(int n) {
        List<Integer> res = new ArrayList<Integer>();
        res.add(0);
        if(n<=0)
            res.get(0);
        int[] colForRow = new int[n];
        helper(n, 0, colForRow, res);
        return res.get(0);
    }
    
    private void helper(int n, int row, int[] colForRow, List<Integer> res) {
        if(row==n)
        {
            res.set(0, res.get(0)+1);
            return;
        }
        for(int i=0; i<n; i++)    
        {    
            colForRow[row] = i;
            if(check(row, colForRow))
            {
                helper(n, row+1, colForRow, res);
            }
        }
    }
    
    private boolean check(int row, int[] colForRow) {
        for(int i=0; i<row; i++)
        {
            if(colForRow[row]==colForRow[i])
                return false;
            else if(Math.abs(row-i)==Math.abs(colForRow[row]-colForRow[i]))
                return false;
        }
        return true;
    }
}

Note: 思路跟上一题完全一样 只是输出改为有几个解 这里要注意下arraylist的set方法 它是replace固定位置的值 如果那个位置没有值 会报exception

所以一定要先附值在用set 还有这个是一定要用arraylist存结果 如果直接传int变量是不行的 因为java都是值传递 没有引用传递 这个问题要好好搞明白





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