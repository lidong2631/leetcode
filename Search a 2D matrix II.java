public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m=matrix.length, n=matrix[0].length, i=0, j=n-1;
        while (i<m && j>=0) {
            if (matrix[i][j]==target) return true;
            else if (matrix[i][j]<target) i++;
            else j--;
        }
        return false;
}

https://leetcode.com/discuss/66657/java-short-code-o-m-n



public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        return false;

    int n = matrix.length, m = matrix[0].length;
    int i = 0, j = m - 1;

    while (i < n && j >= 0) {
        int num = matrix[i][j];

        if (num == target)
            return true;
        else if (num > target)
            j--;
        else
            i++;
    }

    return false;
}

If we stand on the top-right corner of the matrix and look diagonally, it is kind of like a BST, 
we can go through this matrix to find the target like how we traverse the BST.

O(m+n) O(1)


public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int left = 0, right = matrix.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (matrix[mid][0] == target) return true;
            else if (matrix[mid][0] < target) left = mid + 1;
            else right = mid - 1;
        }
        if (right < 0) return false;
        int row = right, col = 1;
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] < target) col++;
            else row--;
        }
        return false;
    }
}

O(m+n) O(1)