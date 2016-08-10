public class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int res = 0, m =grid.length, n = grid[0].length;
        int rowCount = 0;
        int[] colCount = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 || grid[i][j-1] == 'W') {        // if at beginning or previous is wall we need to recalculate enemy kills
                    rowCount = 0;
                    for (int k = j; k < n && grid[i][k] != 'W'; k++)
                        rowCount += (grid[i][k] == 'E') ? 1 : 0;
                }
                if (i == 0 || grid[i-1][j] == 'W') {        // same
                    colCount[j] = 0;
                    for (int k = i; k < m && grid[k][j] != 'W'; k++) {
                        colCount[j] += (grid[k][j] == 'E') ? 1 : 0;
                    }
                }
                if (grid[i][j] == '0')                          // check whether current spot is empty
                    res = Math.max(res, rowCount + colCount[j]);
            }
        }
        return res;
    }
}


Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note that you can only put the bomb at an empty cell.

Example:
For the given grid

0 E 0 0
E 0 W E
0 E 0 0

return 3. (Placing a bomb at (1,1) kills 3 enemies)


Walk through the matrix. At the start of each non-wall-streak (row-wise or column-wise), count the number of hits in that streak and remember it

O(mn) O(n)

https://leetcode.com/discuss/109116/short-o-mn-solution