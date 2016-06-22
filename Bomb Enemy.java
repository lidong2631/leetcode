public class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int res = 0, m =grid.length, n = grid[0].length;
        int rowCount = 0;
        int[] colCount = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 || grid[i][j-1] == 'W') {
                    rowCount = 0;
                    for (int k = j; k < n && grid[i][k] != 'W'; k++)
                        rowCount += (grid[i][k] == 'E') ? 1 : 0;
                }
                if (i == 0 || grid[i-1][j] == 'W') {
                    colCount[j] = 0;
                    for (int k = i; k < m && grid[k][j] != 'W'; k++) {
                        colCount[j] += (grid[k][j] == 'E') ? 1 : 0;
                    }
                }
                if (grid[i][j] == '0')
                    res = Math.max(res, rowCount + colCount[j]);
            }
        }
        return res;
    }
}

Walk through the matrix. At the start of each non-wall-streak (row-wise or column-wise), count the number of hits in that streak and remember it

O(mn) O(n)

https://leetcode.com/discuss/109116/short-o-mn-solution