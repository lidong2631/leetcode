public class Solution {
    public int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length, count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') continue;
                if (i > 0 && board[i-1][j] == 'X') continue;
                if (j > 0 && board[i][j-1] == 'X') continue;
                count++;
            }
        }
        return count;
    }
}

https://discuss.leetcode.com/topic/62970/simple-java-solution


Given an 2D board, count how many different battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:

You receive a valid board, made of only battleships or empty slots.
Battleships can only be placed horizontally or vertically.
Battleships come in different sizes.
At least one space of horizontal or vertical separates between two battleships - no adjacent battleships will be given.
Example:
X..X
...X
...X
In the above board there are 2 battleships.
Your algorithm should not modify the value of the board.