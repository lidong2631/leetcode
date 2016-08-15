题意：经典的N皇后问题。

解题思路：这类型问题统称为递归回溯问题，也可以叫做对决策树的深度优先搜索（dfs）。N皇后问题有个技巧的关键在于棋盘的表示方法，

这里使用一个数组就可以表达了。比如board=[1, 3, 0, 2]，这是4皇后问题的一个解，意思是：在第0行，皇后放在第1列；在第1行，皇后放在第3列；在第2行，

皇后放在第0列；在第3行，皇后放在第2列。这道题提供一个递归解法，下道题使用非递归。check函数用来检查在第k行，皇后是否可以放置在第j列。

代码：


class Solution:
    # @return a list of lists of string
    def solveNQueens(self, n):
        def check(k, j):  # check if the kth queen can be put in column j!
            for i in range(k):
                if board[i]==j or abs(k-i)==abs(board[i]-j):
                    return False
            return True
        def dfs(depth, valuelist):
            if depth==n: res.append(valuelist); return
            for i in range(n):
                if check(depth,i): 
                    board[depth]=i
                    s='.'*n
                    dfs(depth+1, valuelist+[s[:i]+'Q'+s[i+1:]])
        board=[-1 for i in range(n)]
        res=[]
        dfs(0,[])
        return res



public class Solution {
    public List<List<String>> solveNQueens(int n) {
        int[] colForRow = new int[n];
        List<List<String>> res = new ArrayList<List<String>>();
        helper(res, n, 0, colForRow);
        return res;
    }
    
    private void helper(List<List<String>> res, int n, int r, int[] colForRow) {
        if (r == n) {
            List<String> oneSolution = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuffer oneLine = new StringBuffer();
                for (int j = 0; j < n; j++) {
                    if (j == colForRow[i]) oneLine.append('Q');
                    else oneLine.append('.');
                }
                oneSolution.add(oneLine.toString());
            }
            res.add(oneSolution);
            return;
        }
        for (int i = 0; i < n; i++) {
            colForRow[r] = i;                   // first set a queen and then check if valid
            if (isValid(i, r, colForRow)) {
                helper(res, n, r+1, colForRow);
            }
        }
    }
    
    private boolean isValid(int col, int row, int[] colForRow) {
        for (int i = 0; i < row; i++) {
            if (col == colForRow[i] || (row - i == Math.abs(col - colForRow[i])))   // careful for col need to take abs
                return false;
        }
        return true;
    }
}




from code ganker:

N皇后问题是非常经典的问题了，记得当时搞竞赛第一道递归的题目就是N皇后。因为这个问题是典型的NP问题，所以在时间复杂度上就不用纠结了，肯定是指数量级的。

下面我们来介绍这个题的基本思路。

主要思想就是一句话：用一个循环递归处理子问题。这个问题中，在每一层递归函数中，我们用一个循环把一个皇后填入对应行的某一列中，如果当前棋盘合法，我们就递归处理先一行，

找到正确的棋盘我们就存储到结果集里面。

这种题目都是使用这个套路，就是用一个循环去枚举当前所有情况，然后把元素加入，递归，再把元素移除，这道题目中不用移除的原因是我们用一个一维数组去存皇后在对应行的哪一列，

因为一行只能有一个皇后，如果二维数组，那么就需要把那一行那一列在递归结束后设回没有皇后，所以道理是一样的。

这道题最后一个细节就是怎么实现检查当前棋盘合法性的问题，因为除了刚加进来的那个皇后，前面都是合法的，我们只需要检查当前行和前面行是否冲突即可。检查是否同列很简单，

检查对角线就是行的差和列的差的绝对值不要相等就可以。代码如下： 

public ArrayList<String[]> solveNQueens(int n) {
    ArrayList<String[]> res = new ArrayList<String[]>();
    helper(n,0,new int[n], res);
    return res;
}
private void helper(int n, int row, int[] columnForRow, ArrayList<String[]> res)
{
    if(row == n)
    {
        String[] item = new String[n];
        for(int i=0;i<n;i++)
        {
            StringBuilder strRow = new StringBuilder();
            for(int j=0;j<n;j++)
            {
                if(columnForRow[i]==j)
                    strRow.append('Q');
                else
                    strRow.append('.');
            }
            item[i] = strRow.toString();
        }
        res.add(item);
        return;
    }
    for(int i=0;i<n;i++)
    {
        columnForRow[row] = i;
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
        if(columnForRow[row]==columnForRow[i] || Math.abs(columnForRow[row]-columnForRow[i])==row-i)
            return false;
    }
    return true;
}

这道题实现的方法是一个非常典型的套路，有许多题都会用到，基本上大部分NP问题的求解都是用这个方式，

比如Sudoku Solver，Combination Sum，Combinations，Permutations，Word Break II，Palindrome Partitioning等，所以大家只要把这个套路掌握熟练，那些题就都不在话下哈。



