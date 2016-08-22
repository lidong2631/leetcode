Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X



public class Solution {
    public void solve(char[][] board) {
        if(board==null || board.length==0 || board[0].length==0)
            return;
        for(int i=0; i<board.length; i++) { //第一步
            for(int j=0; j<board[0].length; j++) {
                if(board[i][j]=='O')
                    board[i][j] = '-';
            }
        }
        for(int i=0; i<board.length; i++) { //第二步
            if(board[i][0]=='-')
                floodFill(board, i, 0, '-', 'O');
        }
        for(int i=0; i<board.length; i++) {
            if(board[i][board[0].length-1]=='-')
                floodFill(board, i, board[0].length-1, '-', 'O');
        }
        for(int i=0; i<board[0].length; i++) {
            if(board[0][i]=='-')
                floodFill(board, 0, i, '-', 'O');
        }
        for(int i=0; i<board[0].length; i++) {
            if(board[board.length-1][i]=='-')
                floodFill(board, board.length-1, i, '-', 'O');
        }
        
        for(int i=0; i<board.length; i++) { //第三步
            for(int j=0; j<board[0].length; j++) {
                if(board[i][j]=='-')
                    board[i][j] = 'X';
            }
        }
    }
    
    private void floodFill(char[][] board, int i, int j, char oldC, char newC) {
        if(i<0 || i>=board.length || j<0 || j>=board[0].length)
            return;
        if(board[i][j]!=oldC)
            return;
        board[i][j] = newC;
        
        floodFill(board, i+1, j, oldC, newC);
        floodFill(board, i-1, j, oldC, newC);
        floodFill(board, i, j+1, oldC, newC);
        floodFill(board, i, j-1, oldC, newC);
    }
}

1) Traverse the given matrix and replace all ‘O’ with a special character ‘-‘.

2) Traverse four edges of given matrix and call floodFill(‘-‘, ‘O’) for every ‘-‘ on edges. 
   The remaining ‘-‘ are the characters that indicate ‘O’s (in the original matrix) to be replaced by ‘X’.

3) Traverse the matrix and replace all ‘-‘s with ‘X’s.

上面是dfs解法 从geeksforgeeks改编 OJ过不了 会栈溢出 Runtime Error Message:  Line 43: java.lang.StackOverflowError
这是因为在图形中通常图片（或者说这里的矩阵）一般会很大，递归很容易导致栈溢出，所以即使要用深度优先搜索，也最好使用非递归的实现方式
see http://www.geeksforgeeks.org/given-matrix-o-x-replace-o-x-surrounded-x/

O(m+n) O(m+n)

顺便看下flood fill algorithm 
http://www.geeksforgeeks.org/flood-fill-algorithm-implement-fill-paint/


public class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = '-';
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (board[0][i] == '-') floodFill(board, 0, i);
        }
        for (int i = 0; i < n; i++) {
            if (board[m-1][i] == '-') floodFill(board, m-1, i);
        }
        for (int i = 0; i < m; i++) {
            if (board[i][0] == '-') floodFill(board, i, 0);
        }
        for (int i = 0; i < m; i++) {
            if (board[i][n-1] == '-') floodFill(board, i, n-1);
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (board[i][j] == '-') board[i][j] = 'X';
            }
        }
    }
    
    private void floodFill(char[][] board, int i, int j) {
        board[i][j] = 'O';
        Queue<Integer> queue = new LinkedList<>();
        int len = board[0].length;
        int pos = i * len + j;
        queue.add(pos);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int r = curr / len, c = curr % len;
            if (r > 0 && board[r-1][c] == '-') {
                queue.add((r-1) * len + c);
                board[r-1][c] = 'O';
            }
            if (c > 0 && board[r][c-1] == '-') {
                queue.add(r * len + c - 1);
                board[r][c-1] = 'O';
            }
            if (r < board.length - 1 && board[r+1][c] == '-') {
                queue.add((r+1) * len + c);
                board[r+1][c] = 'O';
            }
            if (c < board[0].length - 1 && board[r][c+1] == '-') {
                queue.add(r * len + c + 1);
                board[r][c+1] = 'O';
            }
        }
    }
}

O(m+n) O(m+n)




这个题目用到的方法是图形学中的一个常用方法：Flood fill算法，其实就是从一个点出发对周围区域进行目标颜色的填充。

背后的思想就是把一个矩阵看成一个图的结构，每个点看成结点，而边则是他上下左右的相邻点，然后进行一次广度或者深度优先搜索。

接下来我们看看这个题如何用Flood fill算法来解决。首先根据题目要求，边缘上的'O'是不需要填充的，所以我们的办法是对上下左右边缘做Flood fill算法，

把所有边缘上的'O'都替换成另一个字符，比如'#'。接下来我们知道除去被我们换成'#'的那些顶点，

剩下的所有'O'都应该被替换成'X'，而'#'那些最终应该是还原成'O'，如此我们可以做最后一次遍历，然后做相应的字符替换就可以了。

复杂度分析上，我们先对边缘做Flood fill算法，因为只有是'O'才会进行，而且会被替换成'#'，所以每个结点改变次数不会超过一次，

因而是O(m*n)的复杂度，最后一次遍历同样是O(m*n)，所以总的时间复杂度是O(m*n)。空间上就是递归栈（深度优先搜索）或者是队列（广度优先搜索）的空间，

同时存在的空间占用不会超过O(m+n)（以广度优先搜索为例，每次队列中的结点虽然会往四个方向拓展，但是事实上这些结点会有很多重复，假设从中点出发，

可以想象最大的扩展不会超过一个菱形，也就是n/2*2+m/2*2=m+n，所以算法的空间复杂度是O(m+n)）。代码如下：

public void solve(char[][] board) {
    if(board==null || board.length<=1 || board[0].length<=1)
        return;
    for(int i=0;i<board[0].length;i++)
    {
        fill(board,0,i);
        fill(board,board.length-1,i);
    }
    for(int i=0;i<board.length;i++)
    {
        fill(board,i,0);
        fill(board,i,board[0].length-1);
    }
    for(int i=0;i<board.length;i++)
    {
        for(int j=0;j<board[0].length;j++)
        {
            if(board[i][j]=='O')
                board[i][j]='X';
            else if(board[i][j]=='#')
                board[i][j]='O';                
        }
    }
}
private void fill(char[][] board, int i, int j)
{
    if(board[i][j]!='O')
        return;
    board[i][j] = '#';
    LinkedList<Integer> queue = new LinkedList<Integer>();
    int code = i*board[0].length+j;
    queue.offer(code);
    while(!queue.isEmpty())
    {
        code = queue.poll();
        int row = code/board[0].length;
        int col = code%board[0].length;
        if(row>0 && board[row-1][col]=='O')
        {
            queue.offer((row-1)*board[0].length+col);
            board[row-1][col]='#';
        }
        if(row<board.length-1 && board[row+1][col]=='O')
        {
            queue.offer((row+1)*board[0].length+col);
            board[row+1][col]='#';
        }
        if(col>0 && board[row][col-1]=='O')
        {
            queue.offer(row*board[0].length+col-1);
            board[row][col-1]='#';
        }
        if(col<board[0].length-1 && board[row][col+1]=='O')
        {
            queue.offer(row*board[0].length+col+1);
            board[row][col+1]='#';
        }            
    }
}

可以看到上面代码用的是广度优先搜索，用一个队列来维护，当然也可以用深度优先搜索，但是如果使用递归，会发现LeetCode过不了，

这是因为在图形中通常图片（或者说这里的矩阵）一般会很大，递归很容易导致栈溢出，所以即使要用深度优先搜索，也最好使用非递归的实现方式哈