public class Solution {
    public void gameOfLife(int[][] board) {
        if(board==null || board.length==0 || board[0].length==0)
            return;
        int[][] d = {{-1,1},{0,1},{1,1},{-1,0},{1,0},{-1,-1},{0,-1},{1,-1}};
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                int lives = 0;
                for(int[] tmp : d) {
                    if(tmp[1]+i<0 || tmp[1]+i>=board.length || tmp[0]+j<0 || tmp[0]+j>=board[0].length)
                        continue;
                    if(board[tmp[1]+i][tmp[0]+j]==1 || board[tmp[1]+i][tmp[0]+j]==2)
                        lives++;
                }
                if(board[i][j]==0 && lives==3)
                    board[i][j] = 3;
                if(board[i][j]==1 && (lives<2 || lives>3))
                    board[i][j] = 2;
            }
        }
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                board[i][j]%=2;
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