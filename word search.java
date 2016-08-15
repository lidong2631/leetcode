public class Solution {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (search(board, 0, i, j, word))
                    return true;
            }
        }
        return false;
    }
    
    private boolean search(char[][] board, int index, int i, int j, String word) {
        if (index == word.length()) return true;
        
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != word.charAt(index)) return false;
        
        char tmp = board[i][j];
        board[i][j] = '#';
        boolean res = search(board, index+1, i+1, j, word)
                    || search(board, index+1, i-1, j, word)
                    || search(board, index+1, i, j+1, word)
                    || search(board, index+1, i, j-1, word);
        board[i][j] = tmp;
        return res;
    }
}


O（m^2*n^2) O(m*n*4^(len(word))) 空间O(1)

递归题 类似于dfs 但复杂度比dfs高 因为访问过的节点还会再被访问 最坏复杂度为O(m*n*4^(len(word))) 意思是一共m*n个点 每个点要走4个方向

每个方向最多可能递归len(word)次 看code ganker评论


https://leetcode.com/discuss/23165/accepted-very-short-java-solution-no-additional-space




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

