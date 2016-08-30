public class Solution {
    
    int[][] move = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int[][] cache = new int[matrix.length][matrix[0].length];
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                res = Math.max(res, dfs(i, j, matrix, cache));
            }
        }
        return res;
    }
    
    private int dfs(int i, int j, int[][] matrix, int[][] cache) {
        if (cache[i][j] > 0) return cache[i][j];
        int curr = 0;
        for (int k = 0; k < 4; k++) {
            int r = i + move[k][0], c = j + move[k][1];
            if (r >= 0 && c >= 0 && r < matrix.length && c < matrix[0].length && matrix[r][c] > matrix[i][j]) {
                curr = Math.max(curr, dfs(r, c, matrix, cache));
            }
        }
        cache[i][j] = curr + 1;
        return cache[i][j];
    }
}

Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not 

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

O(m*n) O(m*n)

https://leetcode.com/discuss/81235/java-dfs-dp-solution

https://leetcode.com/discuss/81389/15ms-concise-java-solution