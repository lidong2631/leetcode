class Solution:
    # @param board, a 2D array
    # Capture all regions by modifying the input board in-place.
    # Do not return any value.
    def solve(self, board):
        def fill(x, y):
            if x<0 or x>m-1 or y<0 or y>n-1 or board[x][y] != 'O': 
                return
            queue.append((x,y))
            board[x][y]='D'
        def bfs(x, y):
            if board[x][y]=='O':        #如果是'0' 执行fill函数 就是将点入队列 并置board对应值为'D'
                fill(x,y)
            while queue:                #只要队列不为空 每次弹一个元素 判读它周围是否有'0'
                curr=queue.pop(0); i=curr[0]; j=curr[1]
                fill(i+1,j);fill(i-1,j);fill(i,j+1);fill(i,j-1)
        if len(board)==0: 
            return
        m=len(board); n=len(board[0]); queue=[]
        for i in range(n):          #遍历第一行和最后一行
            bfs(0,i); bfs(m-1,i)        #这里注意 dfs和bfs不同
        for j in range(1, m-1):     #遍历第一列和最后一列
            bfs(j,0); bfs(j,n-1)        #这里注意 dfs和bfs不同
        for i in range(m):
            for j in range(n):
                if board[i][j] == 'D': board[i][j] = 'O'
                elif board[i][j] == 'O': board[i][j] = 'X'

Note: 这题dfs会报runtime error 而bfs不会可以ac 原因是dfs递归深度太大会爆栈 可以试想例子'0000000' 每次dfs检查右边还是'0'会继续递归栈空间会越来越大 而bfs以广度递增

每次会pop出元素再继续检查栈空间没有dfs递增的大

利用这个题可以再复习一下dfs和bfs


class Solution:
    # @param board, a 2D array
    # Capture all regions by modifying the input board in-place.
    # Do not return any value.
    def solve(self, board):
        def dfs(x, y):
            if x<0 or x>m-1 or y<0 or y>n-1 or board[x][y]!='O':
                return
            board[x][y] = 'D'
            dfs(x+1,y)
            dfs(x-1,y)
            dfs(x,y+1)
            dfs(x,y-1)
        if len(board)==0: 
            return
        m=len(board); n=len(board[0])
        for i in range(m):
            dfs(i,0); dfs(i,n-1)
        for j in range(1, n-1):
            dfs(0,j); dfs(m-1,j)
        for i in range(m):
            for j in range(n):
                if board[i][j] == 'D': board[i][j] = 'O'
                elif board[i][j] == 'O': board[i][j] = 'X'




from code ganker:

这个题目用到的方法是图形学中的一个常用方法：Flood fill算法，其实就是从一个点出发对周围区域进行目标颜色的填充。背后的思想就是把一个矩阵看成一个图的结构，

每个点看成结点，而边则是他上下左右的相邻点，然后进行一次广度或者深度优先搜索。

接下来我们看看这个题如何用Flood fill算法来解决。首先根据题目要求，边缘上的'O'是不需要填充的，所以我们的办法是对上下左右边缘做Flood fill算法，

把所有边缘上的'O'都替换成另一个字符，比如'#'。接下来我们知道除去被我们换成'#'的那些顶点，剩下的所有'O'都应该被替换成'X'，而'#'那些最终应该是还原成'O'，

如此我们可以做最后一次遍历，然后做相应的字符替换就可以了。复杂度分析上，我们先对边缘做Flood fill算法，因为只有是'O'才会进行，而且会被替换成'#'，

所以每个结点改变次数不会超过一次，因而是O(m*n)的复杂度，最后一次遍历同样是O(m*n)，所以总的时间复杂度是O(m*n)。

空间上就是递归栈（深度优先搜索）或者是队列（广度优先搜索）的空间，同时存在的空间占用不会超过O(m+n)（以广度优先搜索为例，每次队列中的结点虽然会往四个方向拓展，

但是事实上这些结点会有很多重复，假设从中点出发，可以想象最大的扩展不会超过一个菱形，也就是n/2*2+m/2*2=m+n，所以算法的空间复杂度是O(m+n)）。代码如下：

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

这是因为在图形中通常图片（或者说这里的矩阵）一般会很大，递归很容易导致栈溢出，所以即使要用深度优先搜索，也最好使用非递归的实现方式
