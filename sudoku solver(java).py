class Solution:
    # @param board, a 9x9 2D array
    # Solve the Sudoku by modifying the input board in-place.
    # Do not return any value.
    def solveSudoku(self, board):
        def valid(x, y):                        #valid函数check当前格子是否填写有效
            tmp = board[x][y]; board[x][y] = '#'        #tmp保存当前格子的值以做判断 然后置当前格子为'#'这样当判断当前格子和当前格子时不会影响结果
            for i in range(9):              #判断一行
                if board[x][i] == tmp:
                    return False
                    
            for j in range(9):              #判断一列
                if board[j][y] == tmp:
                    return False
                    
            for i in range(3):              #判断9个格子
                for j in range(3):
                    if board[x/3*3+i][y/3*3+j] == tmp:
                        return False
            board[x][y] = tmp           #最后将格子置回原值
            return True
    
        def dfs(board):
            for i in range(9):
                for j in range(9):
                    if board[i][j] == '.':      #循环遍历每一个格子 如果是'.'
                        for k in '123456789':       #需要在1-9 这9个数中选择一个数填入 逐一在9个数中尝试
                            board[i][j] = k
                            if valid(i, j) and dfs(board):      #如果valid()判断合法且递归剩下的格子可以返回true得到解 返回true
                                return True            
                            board[i][j] = '.'           #如果valid()判断无效或递归剩下的格子无法得到解 将board[i][j]设置回'.' 继续判断k下一个值
                        return False            #如果k从1到9都找不到解 返回false 回上一级递归从新选值
            return True                 #递归到最后 如果所有值为'.'的格子都被填入了值 则循环9*9次 每次在26行if判断处都为假 最终来到33行 说明找到解 返回true结束递归 然后一层层回溯 每一层都是在30行返回true 最后返回true
            
        dfs(board)

Note: 以上解法time limit exceed 下面java的解法可以ac 但两种解法基本一样 不知道是不是oj的问题





public class Solution {
    public void solveSudoku(char[][] board) {
        if(board == null || board.length != 9 || board[0].length !=9)
            return;
        helper(board,0,0);
    }
    
    private boolean helper(char[][] board, int i, int j)
    {
        if(j>=9)
            return helper(board,i+1,0);         //这里写j＝＝9也可以 因为j最多到9 然后就开始递归下一行
        if(i==9)
        {
            return true;                        //如果i j都等于9 返回true 所以棋格都被填好
        }
        if(board[i][j]=='.')
        {
            for(int k=1;k<=9;k++)               
            {
                board[i][j] = (char)(k+'0');  //从1到9循环依次尝试填入当前格子
                if(isValid(board,i,j))  //这里递归下一个格子前先isValid判断下当前格子是否符合要求 符合才继续递归
                {
                    if(helper(board,i,j+1))
                        return true;
                }
            }
            board[i][j] = '.';   //如果k从1到9填入递归下一个格子都得不到解 则将这个格子值重置为空 我们要回溯到上一轮换一个数字重试
        }
        else
        {
            return helper(board,i,j+1);         //如果当前格子有数字 就跳过它递归下一个格子
        }
        return false;   //注意这里helper的return值是必要的 当111行if没找到结果就会到这里返回false 然后回溯到上一轮递归118行才可以判断
    }                   //看code ganker评论

    private boolean isValid(char[][] board, int row, int col) {     //这里的写法仍然是上一题里python版写法
        char tmp = board[row][col];
        board[row][col] = '#';  //保存当前格子的值然后设其为'#' 以排除自身的干扰
        for(int i=0; i<9; i++)
            if(board[row][i]==tmp)
                return false;
        for(int j=0; j<9; j++)
            if(board[j][col]==tmp)
                return false;
        for(int i=0; i<3; i++)      //判断小格子的值是否重复 循环3*3次
            for(int j=0; j<3; j++)
                if(board[row/3*3+i][col/3*3+j]==tmp)    //判断跟当前格子在同一个小格子的其他格子
                    return false;
        board[row][col] = tmp;  //判断完再将它设回原始值
        return true;
    }
}

Note: 根据code ganker改编 isValid函数根据python版写的 还是NP问题套路 好好看注释理解






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

