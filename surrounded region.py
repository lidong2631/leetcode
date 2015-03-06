[leetcode]Surrounded Regions @ Python
原题地址：https://oj.leetcode.com/problems/surrounded-regions/

题意：

Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

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
解题思路：这道题可以使用BFS和DFS两种方法来解决。DFS会超时。BFS可以AC。从边上开始搜索，如果是'O'，那么搜索'O'周围的元素，并将'O'置换为'D'，这样每条边都DFS或者BFS一遍。而内部的'O'是不会改变的。这样下来，没有被围住的'O'全都被置换成了'D'，被围住的'O'还是'O'，没有改变。然后遍历一遍，将'O'置换为'X'，将'D'置换为'O'。

dfs代码，因为递归深度的问题会爆栈：

复制代码
class Solution:
    # @param board, a 9x9 2D array
    # Capture all regions by modifying the input board in-place.
    # Do not return any value.
    def solve(self, board):
        def dfs(x, y):
            if x<0 or x>m-1 or y<0 or y>n-1 or board[x][y]!='O':return
            board[x][y] = 'D'
            dfs(x-1, y)
            dfs(x+1, y)
            dfs(x, y+1)
            dfs(x, y-1)
        
        if len(board) == 0: return
        m = len(board); n = len(board[0])
        for i in range(m):
            dfs(i, 0); dfs(i, n-1)
        for j in range(1, n-1):
            dfs(0, j); dfs(m-1, j)
        for i in range(m):
            for j in range(n):
                if board[i][j] == 'O': board[i][j] == 'X'
                elif board[i][j] == 'D': board[i][j] == 'O'
复制代码
bfs代码：

复制代码
class Solution:
    # @param board, a 9x9 2D array
    # Capture all regions by modifying the input board in-place.
    # Do not return any value.
    def solve(self, board):
        def fill(x, y):
            if x<0 or x>m-1 or y<0 or y>n-1 or board[x][y] != 'O': return
            queue.append((x,y))
            board[x][y]='D'
        def bfs(x, y):
            if board[x][y]=='O':queue.append((x,y)); fill(x,y)
            while queue:
                curr=queue.pop(0); i=curr[0]; j=curr[1]
                fill(i+1,j);fill(i-1,j);fill(i,j+1);fill(i,j-1)
        if len(board)==0: return
        m=len(board); n=len(board[0]); queue=[]
        for i in range(n):
            bfs(0,i); bfs(m-1,i)
        for j in range(1, m-1):
            bfs(j,0); bfs(j,n-1)
        for i in range(m):
            for j in range(n):
                if board[i][j] == 'D': board[i][j] = 'O'
                elif board[i][j] == 'O': board[i][j] = 'X'