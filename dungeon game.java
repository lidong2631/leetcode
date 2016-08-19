public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        int[][] res = new int[m][n];
        res[m-1][n-1] = Math.max(1, 1 - dungeon[m-1][n-1]);
        
        for (int i = m - 2; i >= 0; i--) {
            res[i][n-1] = Math.max(1, res[i+1][n-1] - dungeon[i][n-1]);
        }
        for (int i = n - 2; i >= 0; i--) {
            res[m-1][i] = Math.max(1, res[m-1][i+1] - dungeon[m-1][i]);
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int down = Math.max(1, res[i+1][j] - dungeon[i][j]);
                int right = Math.max(1, res[i][j+1] - dungeon[i][j]);
                res[i][j] = Math.min(down, right);
            }
        }
        return res[0][0];
    }
}




public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        int[] res = new int[n+1];
        res[n] = 1;
        for(int i=n-1; i>=0; i--)
            res[i] = Math.max(res[i+1]-dungeon[m-1][i], 1);
            
        for(int i=m-2; i>=0; i--) {
            for(int j=n-1; j>=0; j--) {
                if(j==n-1)
                    res[j] = Math.max(res[j]-dungeon[i][j], 1);
                else
                    res[j] = Math.min(Math.max(res[j]-dungeon[i][j], 1), Math.max(res[j+1]-dungeon[i][j], 1));
            }
        }
        return res[0];
    }
}

第二遍写的 空间只需要上一行信息 用滚动数组优化为一维 可以最优化为O(min(m,n))

O(m*n) O(n)