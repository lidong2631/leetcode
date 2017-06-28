Let's play the minesweeper game (Wikipedia, online game)!

You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:

If a mine ('M') is revealed, then the game is over - change it to 'X'.
If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.
Example 1:
Input: 

[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

Click : [3,0]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:

Example 2:
Input: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Click : [1,2]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:

Note:
The range of the input matrix's height and width is [1,50].
The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
The input board won't be a stage when game is over (some mines have been revealed).
For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.
Hide Company Tags Amazon
Hide Tags Depth-first Search Breadth-first Search





public class Solution {
    
    int[][] move = {{-1,0}, {-1,-1}, {-1,1}, {0,1}, {0,-1}, {1,0}, {1,1}, {1,-1}};
    
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length, n = board[0].length;
        int r = click[0], c = click[1];
        
        if (board[r][c] == 'M')
            board[r][c] = 'X';
        else {
            int count = 0;
            for (int i = 0; i < 8; i++) {
                int newR = r + move[i][0], newC = c + move[i][1];
                if (newR < 0 || newC < 0 || newR >= m || newC >= n) continue;
                if (board[newR][newC] == 'M') count++;
            }
            if (count > 0)
                board[r][c] = (char)(count + '0');
            else {
                board[r][c] = 'B';
                for (int i = 0; i < 8; i++) {
                    int newR = r + move[i][0], newC = c + move[i][1];
                    if (newR < 0 || newC < 0 || newR >= m || newC >= n) continue;
                    if (board[newR][newC] == 'E') updateBoard(board, new int[]{newR, newC});
                }
            }
        }
        return board;
    }
}

DFS



public class Solution {
    
    int[][] move = {{-1,0}, {-1,-1}, {-1,1}, {0,1}, {0,-1}, {1,0}, {1,1}, {1,-1}};
    
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length, n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);
        
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0], col = cell[1];
            
            if (board[row][col] == 'M') { // Mine
                board[row][col] = 'X';
            }
            else { // Empty
                // Get number of mines first.
                int count = 0;
                for (int i = 0; i < 8; i++) {
                    int r = row + move[i][0], c = col + move[i][1];
                    if (r < 0 || c < 0 || r >= m || c >= n) continue;
                    if (board[r][c] == 'M') count++;
                }
                
                if (count > 0) { // If it is not a 'B', stop further DFS.
                    board[row][col] = (char)(count + '0');
                }
                else { // Continue BFS to adjacent cells.
                    board[row][col] = 'B';
                    for (int i = 0; i < 8; i++) {
                        int r = row + move[i][0], c = col + move[i][1];
                        if (r < 0 || r >= m || c < 0 || c < 0 || c >= n) continue;
                        if (board[r][c] == 'E') {
                            queue.add(new int[] {r, c});
                            board[r][c] = 'B'; // Avoid to be added again.
                        }
                    }
                }
            }
        }
        
        return board;
    }
}

BFS









