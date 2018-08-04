Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.


Note:

The given board contain only digits 1-9 and the character '.'.
You may assume that the given Sudoku puzzle will have a single unique solution.
The given board size is always 9x9.



Python: (have problem)
class Solution:
    def solveSudoku(self, board):
        """
        :type board: List[List[str]]
        :rtype: void Do not return anything, modify board in-place instead.
        """
        self.helper(board, 0, 0)
        
    def helper(self, board, r, c):
        if r == 9:
            return True
        if c == 9:
            return self.helper(board, r+1, 0)
        if board[r][c] == ".":
            for i in range(1, 10):
                board[r][c] = str(i)
                if self.valid(board, r, c):
                    if self.helper(board, r, c+1):
                        return True
                board[r][c] = "."
        else:
            return self.helper(board, r, c+1)
        return False
    
    def valid(self, board, r, c):
        for i in range(9):
            if i != r and board[i][c] == board[r][c]:
                return False
        for i in range(9):
            if i != c and board[r][i] == board[r][c]:
                return False
        for i in range(r//3*3, r//3*3+3):
            for j in range(c%3*3, c%3*3+3):
                if i != r and j != c and board[i][j] == board[r][c]:
                    return False             
        return True




Java:
public class Solution {
    public void solveSudoku(char[][] board) {
        helper(board, 0, 0);
    }
    
    private boolean helper(char[][] board, int r, int c) {
        if (r == 9) return true;
        if (c == 9) return helper(board, r+1, 0);
        if (board[r][c] == '.') {
            for (int k = 1; k <= 9; k++) {
                board[r][c] = (char)(k + '0');      // careful
                if (isValid(board, r, c)) {
                    if (helper(board, r, c+1))
                        return true;
                }
                board[r][c] = '.';                  // careful need to set it back
            }
        }
        else 
            return helper(board, r, c + 1);         // careful
        return false;
    }
        
    private boolean isValid(char[][] board, int r, int c) {
        for (int i = 0; i < 9; i++)
            if (i != c && board[r][i] == board[r][c]) return false;

        for (int i = 0; i < 9; i++)
            if (i != r && board[i][c] == board[r][c]) return false;
            
        for (int i = r / 3 * 3; i < r / 3 * 3 + 3; i++) {               // careful
            for (int j = c / 3 * 3; j < c / 3 * 3 + 3; j++)             // 
                if (i != r && j != c && board[i][j] == board[r][c]) return false;
        }
        return true;
    }
}




from code ganker:

这道题的方法就是用在N-Queens中介绍的常见套路。简单地说思路就是循环处理子问题，对于每个格子，带入不同的9个数，然后判合法，如果成立就递归继续，结束后把数字设回空。

大家可以看出代码结构和N-Queens是完全一样的。判合法可以用Valid Sudoku做为subroutine，但是其实在这里因为每次进入时已经保证之前的board不会冲突，所以不需要判断整个盘，

只需要看当前加入的数字和之前是否冲突就可以，这样可以大大提高运行效率，毕竟判合法在程序中被多次调用。实现的代码如下：

public void solveSudoku(char[][] board) {
    if(board == null || board.length != 9 || board[0].length !=9)
        return;
    helper(board,0,0);
}
private boolean helper(char[][] board, int i, int j)
{
    if(j>=9)
        return helper(board,i+1,0);
    if(i==9)
    {
        return true;
    }
    if(board[i][j]=='.')
    {
        for(int k=1;k<=9;k++)
        {
            board[i][j] = (char)(k+'0');
            if(isValid(board,i,j))
            {
                if(helper(board,i,j+1))
                    return true;
            }
            board[i][j] = '.';
        }
    }
    else
    {
        return helper(board,i,j+1);
    }
    return false;
}
private boolean isValid(char[][] board, int i, int j)
{
    for(int k=0;k<9;k++)
    {
        if(k!=j && board[i][k]==board[i][j])
            return false;
    }
    for(int k=0;k<9;k++)
    {
        if(k!=i && board[k][j]==board[i][j])
            return false;
    }        
    for(int row = i/3*3; row<i/3*3+3; row++)
    {
        for(int col=j/3*3; col<j/3*3+3; col++)
        {
            if((row!=i || col!=j) && board[row][col]==board[i][j])
                return false;
        }
    }
    return true;
}

再强调一下哈，以上方法是一个非常典型的套路，大部分NP问题的都是可以这个方法，比如N-Queens，Combination Sum，Combinations，Permutations等。

