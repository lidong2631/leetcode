class Solution:
    # @return an integer
    def totalNQueens(self, n):
        self.total = 0      
        self.board = [-1 for i in range(n)]
        self.dfs(0,n)
        return self.total

    def check(self, k, j):
        for i in range(k):
            if self.board[i] == j or abs(k-i) == abs(self.board[i] - j):
                return False
        return True
    
        
    def dfs(self, depth, n):
        if depth == n:
            self.total += 1
            return
        for i in range(n):
            if self.check(depth,i):
                self.board[depth] = i
                self.dfs(depth+1,n)

s = Solution()
num = s.totalNQueens(4)
print(num)