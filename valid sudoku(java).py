class Solution:
    # @param board, a 9x9 2D array
    # @return a boolean
    def isValidSudoku(self, board):
        def valid(x, y, val):
            for i in range(9):          #判断一行是否有重复
                if board[x][i] == val:
                    return False
            for j in range(9):          #判断一列是否有重复
                if board[j][y] == val:
                    return False
            for i in range(3):          #判断3*3大格子里是否有重复
                for j in range(3):
                    if board[x/3*3+i][y/3*3+j] == val:
                        return False
            return True
            
        for i in range(9):              #循环验证每一个格子的数字
            for j in range(9):
                if board[i][j] == '.':      #如果格子是空的 则不需验证跳过
                    continue
                tmp = board[i][j]
                board[i][j] = 'D'       #否则置当前格子为‘D'这样在做比较时比较的是自己就不会返回false 简练代码
                if valid(i, j, tmp) == False:   #如果有重复 返回false
                    return False
                board[i][j] = tmp       #将当前格子值设置回原来的值
        return True                 #否则返回true

Note: 两种写法相同 只是第二种valid方法在isValidSudoku 外面 要加 self和board

判断valid依据就是一行9个格不能有重复数字 一列9个格不能有重复数字 并且一个3*3大格子里不能有重复数字


class Solution:
    def valid(self, x, y, val, board):
        for i in range(9):
            if board[x][i] == val:
                return False
        for j in range(9):
            if board[j][y] == val:
                return False
        for i in range(3):
            for j in range(3):
                if board[x/3*3+i][y/3*3+j] == val:
                    return False
        return True
    # @param board, a 9x9 2D array
    # @return a boolean
    def isValidSudoku(self, board):        
        for i in range(9):
            for j in range(9):
                if board[i][j] == '.':
                    continue
                tmp = board[i][j]
                board[i][j] = 'D'
                if self.valid(i, j, tmp, board) == False:
                    return False
                board[i][j] = tmp
        return True







public class Solution {
    public boolean isValidSudoku(char[][] board) {
        for(int i=0; i<9; i++)
        {
            for(int j=0; j<9; j++)
            {
                if(board[i][j]=='.')
                    continue;
                char tmp = board[i][j];
                board[i][j] = 'D';          //注意这里 排除判断到自己时相同的情况
                if(valid(i, j, tmp, board)==false)
                    return false;
                board[i][j] = tmp;          //恢复现场
            }
        }
        return true;
    }
    
    private boolean valid(int row, int col, char tmp, char[][] board) {
        for(int i=0; i<9; i++)
            if(board[row][i]==tmp)
                return false;
        for(int j=0; j<9; j++)
            if(board[j][col]==tmp)
                return false;
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                if(board[row/3*3+i][col/3*3+j]==tmp)        //row/3根据row大小把它分到第一二三组然后在加上i 
                    return false;
        return true;
    }
}

Note: python版改编 这题没什么好说的 就是实现上的事 分别判断同一行同一列同一九宫格里没有相同的数就事valid




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

没必要每次检查整个board，只需要看当前加进去的数就可以，有兴趣的朋友可以看看哈。