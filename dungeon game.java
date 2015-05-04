public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        int[][] res = new int[m][n];
        res[m-1][n-1] = Math.max(1-dungeon[m-1][n-1], 1);
        
        for(int i=m-2; i>=0; i--)
            res[i][n-1] = Math.max(res[i+1][n-1]-dungeon[i][n-1], 1);

        for(int j=n-2; j>=0; j--)
            res[m-1][j] = Math.max(res[m-1][j+1]-dungeon[m-1][j], 1);
        
        for(int i=m-2; i>=0; i--) {
            for(int j=n-2; j>=0; j--) {
                int down = Math.max(res[i+1][j]-dungeon[i][j], 1);
                int right = Math.max(res[i][j+1]-dungeon[i][j], 1);
                res[i][j] = Math.min(down, right);
            }
        }
        return res[0][0];
    }
}

二维dp问题 http://bookshadow.com/weblog/2015/01/07/leetcode-dungeon-game/