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
