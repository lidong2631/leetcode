class Solution:
    def dfs(self, x, y, board, word):
        if len(word) == 0:              #当所有word中的字符都遍历完 说明找到一组匹配
            return True
            
        if x>0 and board[x-1][y] == word[0]:    #判断当前格子上边的值是否和word中字符相等
            tmp = board[x][y]                   #将当前格子的值保存 然后设为# 因为每个格子只能用一次
            board[x][y] = '#'
            if self.dfs(x-1, y, board, word[1:]):       #如果相等 递归继续判断当前格四周的格子值是否有和word下一个字符相等
                return True
            board[x][y] = tmp                           #如果这个方向无法组成word中的字符 应将当前格子值重置为原始值
                
        if y>0 and board[x][y-1] == word[0]:    #左
            tmp = board[x][y]
            board[x][y] = '#'
            if self.dfs(x, y-1, board, word[1:]):
                return True
            board[x][y] = tmp
                
        if x<len(board)-1 and board[x+1][y] == word[0]:     #下
            tmp = board[x][y]
            board[x][y] = '#'
            if self.dfs(x+1, y, board, word[1:]):
                return True
            board[x][y] = tmp
                
        if y<len(board[0])-1 and board[x][y+1] == word[0]:     #右
            tmp = board[x][y]
            board[x][y] = '#'
            if self.dfs(x, y+1, board, word[1:]):
                return True
            board[x][y] = tmp
        return False
    
    # @param board, a list of lists of 1 length string
    # @param word, a string
    # @return a boolean
    def exist(self, board, word):
        for i in range(len(board)):
            for j in range(len(board[0])):      #遍历所有棋格
                if board[i][j] == word[0]:          #如果发现跟第一个字符相等 继续调用dfs()判断前后左右棋格的字符是否跟word相等
                    if self.dfs(i, j, board, word[1:]):
                        return True     #相等返回True
        return False                    #不等返回False

Note: 典型的递归题 像这种棋盘式题目 大都用两层循环遍历所有棋格 里面用递归









题意：

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.

解题思路：使用dfs来搜索，为了避免已经用到的字母被重复搜索，将已经用到的字母临时替换为'#'就可以了。不知道用bfs可行否。

代码：


class Solution:
    # @param board, a list of lists of 1 length string
    # @param word, a string
    # @return a boolean
    def exist(self, board, word):
        def dfs(x, y, word):
            if len(word)==0: return True
            #up
            if x>0 and board[x-1][y]==word[0]:
                tmp=board[x][y]; board[x][y]='#'
                if dfs(x-1,y,word[1:]):
                    return True
                board[x][y]=tmp
            #down
            if x<len(board)-1 and board[x+1][y]==word[0]:
                tmp=board[x][y]; board[x][y]='#'
                if dfs(x+1,y,word[1:]):
                    return True
                board[x][y]=tmp
            #left
            if y>0 and board[x][y-1]==word[0]:
                tmp=board[x][y]; board[x][y]='#'
                if dfs(x,y-1,word[1:]):
                    return True
                board[x][y]=tmp
            #right
            if y<len(board[0])-1 and board[x][y+1]==word[0]:
                tmp=board[x][y]; board[x][y]='#'
                if dfs(x,y+1,word[1:]):
                    return True
                board[x][y]=tmp
            return False
                
        for i in range(len(board)):
            for j in range(len(board[0])):
                if board[i][j]==word[0]:
                    if(dfs(i,j,word[1:])):
                        return True
        return False




public class Solution {
    public boolean exist(char[][] board, String word) {
        if(word==null || word.length()==0)
            return true;
        if(board.length==0 || board[0].length==0 || board==null)
            return false;
        for(int i=0; i<board.length; i++)
        {
            for(int j=0; j<board[0].length; j++)
            {
                if(board[i][j]==word.charAt(0))
                    if(dfs(i, j, board, word.substring(1)))
                        return true;
            }
        }
        return false;
    }
    
    private boolean dfs(int row, int col, char[][] board, String word) {
        if(word.length()==0)
            return true;
        if(row>0 && board[row-1][col]==word.charAt(0))
        {
            char tmp = board[row][col];
            board[row][col] = '#';
            if(dfs(row-1, col, board, word.substring(1)))
                return true;
            board[row][col] = tmp;
        }
        if(col>0 && board[row][col-1]==word.charAt(0))
        {
            char tmp = board[row][col];
            board[row][col] = '#';
            if(dfs(row, col-1, board, word.substring(1)))
                return true;
            board[row][col] = tmp;
        }
        if(row<board.length-1 && board[row+1][col]==word.charAt(0))
        {
            char tmp = board[row][col];
            board[row][col] = '#';
            if(dfs(row+1, col, board, word.substring(1)))
                return true;
            board[row][col] = tmp;
        }
        if(col<board[0].length-1 && board[row][col+1]==word.charAt(0))
        {
            char tmp = board[row][col];
            board[row][col] = '#';
            if(dfs(row, col+1, board, word.substring(1)))
                return true;
            board[row][col] = tmp;
        }
        return false;
    }
}

Note: 这题两种解法都差不多 只是时间复杂度有些想不明白 要多想想 看似是np问题 其实不是




from code ganker:

这道题很容易感觉出来是图的题目，其实本质上还是做深度优先搜索。基本思路就是从某一个元素出发，往上下左右深度搜索是否有相等于word的字符串。

这里注意每次从一个元素出发时要重置访问标记（也就是说虽然单次搜索字符不能重复使用，但是每次从一个新的元素出发，字符还是重新可以用的）。深度优先搜索的算法就不再重复解释了，

不了解的朋友可以看看wiki - 深度优先搜索。我们知道一次搜索的复杂度是O(E+V)，E是边的数量，V是顶点数量，在这个问题中他们都是O(m*n)量级的（因为一个顶点有固定上下左右四条边）。

加上我们对每个顶点都要做一次搜索，所以总的时间复杂度最坏是O(m^2*n^2)，空间上就是要用一个数组来记录访问情况，所以是O(m*n)。代码如下：

public boolean exist(char[][] board, String word) {
    if(word==null || word.length()==0)
        return true;
    if(board==null || board.length==0 || board[0].length==0)
        return false;
    boolean[][] used = new boolean[board.length][board[0].length];
    for(int i=0;i<board.length;i++)
    {
        for(int j=0;j<board[0].length;j++)
        {
            if(search(board,word,0,i,j,used))
                return true;
        }
    }
    return false;
}
private boolean search(char[][] board, String word, int index, int i, int j, boolean[][] used)
{
    if(index == word.length())
        return true;
    if(i<0 || j<0 || i>=board.length || j>=board[0].length || used[i][j] || board[i][j]!=word.charAt(index))
        return false;
    used[i][j] = true;
    boolean res = search(board,word,index+1,i-1,j,used) 
                || search(board,word,index+1,i+1,j,used)
                || search(board,word,index+1,i,j-1,used) 
                || search(board,word,index+1,i,j+1,used);
    used[i][j] = false;
    return res;
}

这道题其实还可以变一变，比如字符可以重复使用。准备的时候多联想还是比较好的，因为面试中常常会做完一道题会变一下问问，虽然经常不用重新写代码，但是想了解一下思路，有兴趣的朋友可以想想哈。





