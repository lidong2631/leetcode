According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

Example:

Input: 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output: 
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
Follow up:

Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?



Java:
public class Solution {
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return;
        int[][] d = {{-1,1}, {0,1}, {1,1}, {-1,0}, {1,0}, {-1,-1}, {0,-1}, {1,-1}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int lives = 0;
                for (int[] tmp : d) {
                    if (tmp[1] + i < 0 || tmp[1] + i >= board.length || tmp[0] + j < 0 || tmp[0] + j >= board[0].length)
                        continue;
                    if (board[tmp[1]+i][tmp[0]+j] == 1 || board[tmp[1]+i][tmp[0]+j] == 2)
                        lives++;
                }
                if (board[i][j] == 0 && lives == 3)
                    board[i][j] = 3;
                if (board[i][j] == 1 && (lives < 2 || lives > 3))
                    board[i][j] = 2;
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] %= 2;
            }
        }
    }
}


Bit Manipulation
2代表next generation要die的cell(live to dead)  3代表next generation要变live的cell(dead to live) 最后用mod 2更新整个table

O(mn) O(1)

https://leetcode.com/discuss/61910/clean-o-1-space-o-mn-time-java-solution
https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life

follow up for an infinite board
https://leetcode.com/discuss/62185/infinite-board-solution