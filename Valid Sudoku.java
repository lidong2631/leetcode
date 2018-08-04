Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

Example 1:

Input:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: true
Example 2:

Input:
[
  ["8","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being 
    modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.
The given board contain only digits 1-9 and the character '.'.
The given board size is always 9x9.



Python:
def isValidSudoku(self, board):
    return (self.is_row_valid(board) and
            self.is_col_valid(board) and
            self.is_square_valid(board))

def is_row_valid(self, board):
    for row in board:
        if not self.is_unit_valid(row):
            return False
    return True

def is_col_valid(self, board):
    for col in zip(*board):
        if not self.is_unit_valid(col):
            return False
    return True
    
def is_square_valid(self, board):
    for i in (0, 3, 6):
        for j in (0, 3, 6):
            square = [board[x][y] for x in range(i, i + 3) for y in range(j, j + 3)]
            if not self.is_unit_valid(square):
                return False
    return True
    
def is_unit_valid(self, unit):
    unit = [i for i in unit if i != '.']
    return len(set(unit)) == len(unit)




Java:
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            boolean[] check = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (check[board[i][j] - '1']) return false;
                    check[board[i][j] - '1'] = true;
                }
            }
        }
        
        for (int i = 0; i < 9; i++) {
            boolean[] check = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[j][i] != '.') {                       // careful board[j][i]
                    if (check[board[j][i] - '1']) return false;
                    check[board[j][i] - '1'] = true;            //
                }
            }
        }
        for (int k = 0; k < 9; k++) {
            boolean[] check = new boolean[9];
            for (int i = k / 3 * 3; i < k / 3 * 3 + 3; i++) {       // remember
                for (int j = k % 3 * 3 ; j < k % 3 * 3 + 3; j++) {
                    if (board[i][j] != '.') {
                        if (check[board[i][j] - '1']) return false;
                        check[board[i][j] - '1'] = true;
                    }
                }
            }
        }
        return true;
    }
}




Golang:
func isValidSudoku(board [][]byte) bool {
    for i := 0; i < 9; i++ {
        m := make(map[string]bool)
        for j := 0; j < 9; j++ {
            if string(board[i][j]) != "." {
                if m[string(board[i][j])] {
                    return false
                }
                m[string(board[i][j])] = true
            }
        }
    }
    for i := 0; i < 9; i++ {
        m := make(map[string]bool)
        for j := 0; j < 9; j++ {
            if string(board[j][i]) != "." {
                if m[string(board[j][i])] {
                    return false
                }
                m[string(board[j][i])] = true
            }
        }
    }
    for k := 0; k < 9; k++ {
        m := make(map[string]bool)
        for i := k / 3 * 3; i < k / 3 * 3 + 3; i++ {
            for j := k % 3 * 3; j < k % 3 * 3 + 3; j++ {
                if string(board[i][j]) != "." {
                    if m[string(board[i][j])] {
                        return false
                    }
                    m[string(board[i][j])] = true
                }
            }
        }
    }
    return true
}






from code ganker:

这道题是Sudoku Solver的一个子问题，在解数独的时候我们需要验证当前数盘是否合法。其实思路比较简单，也就是用brute force。对于每一行，每一列，每个九宫格进行验证，

总共需要27次验证，每次看九个元素。所以时间复杂度就是O(3*n^2), n=9。代码如下： 

public boolean isValidSudoku(char[][] board) {
    if(board==null || board.length!=9 || board[0].length!=9)
        return false;
    for(int i=0;i<9;i++)
    {
        boolean[] map = new boolean[9];
        for(int j=0;j<9;j++)
        {
            if(board[i][j] != '.')
            {
                if(map[(int)(board[i][j]-'1')])
                {
                    return false;
                }
                map[(int)(board[i][j]-'1')] = true;
            }
        }
    }
    for(int j=0;j<9;j++)
    {
        boolean[] map = new boolean[9];
        for(int i=0;i<9;i++)
        {
            if(board[i][j] != '.')
            {
                if(map[(int)(board[i][j]-'1')])
                {
                    return false;
                }
                map[(int)(board[i][j]-'1')] = true;
            }
        }
    }        
    for(int block=0;block<9;block++)
    {
        boolean[] map = new boolean[9];
        for(int i=block/3*3;i<block/3*3+3;i++)
        {
            for(int j=block%3*3;j<block%3*3+3;j++)
            {
                if(board[i][j] != '.')
                {
                   if(map[(int)(board[i][j]-'1')])
                   {
                      return false;
                   }
                   map[(int)(board[i][j]-'1')] = true;    
                }
            }
        }
    }
    return true;
}


这道题其实没有什么好的算法，基本上就是遍历去检查，实现上就是数组的操作，属于Sudoku Solver的subroutine，但是在Sudoku Solver中实现上又可以进行优化，
