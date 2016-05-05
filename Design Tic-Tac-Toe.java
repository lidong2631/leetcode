public class TicTacToe {

    private int[] rows;
    private int[] cols;
    private int diagonal;
    private int anti_diagonal;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        diagonal = 0;
        anti_diagonal = 0;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int toAdd = (player == 1) ? 1 : -1;
        rows[row] += toAdd;
        cols[col] += toAdd;
        int n = rows.length;
        
        if (row == col) diagonal += toAdd;
        if (n - row - 1 == col) anti_diagonal += toAdd;
        
        if (rows[row] == n || rows[row] == -n)
            return rows[row] > 0 ? 1 : 2;
        if (cols[col] ==n || cols[col] == -n)
            return cols[col] > 0 ? 1 : 2;
        if (diagonal == n || diagonal == -n)
            return diagonal > 0 ? 1 : 2;
        if (anti_diagonal == n || anti_diagonal == -n)
            return anti_diagonal > 0 ? 1 : 2;
        
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */


O(1) O(n)

https://leetcode.com/discuss/101144/java-o-1-solution-easy-to-understand