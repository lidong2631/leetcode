public class Solution {
    public int[][] generateMatrix(int n) {
        int left = 0, up = 0, right = n - 1, down = n - 1, direction = 0, num = 1;
        int[][] matrix = new int[n][n];
        while (left <= right && up <= down) {
            if (direction == 0) {
                for (int i = left; i <= right; i++)
                    matrix[up][i] = num++;
                up++;
            }
            if (direction == 1) {
                for (int i = up; i <= down; i++)
                    matrix[i][right] = num++;
                right--;
            }
            if (direction == 2) {
                for (int i = right; i >= left; i--)
                    matrix[down][i] = num++;
                down--;
            }
            if (direction == 3) {
                for (int i = down; i >= up; i--)
                    matrix[i][left] = num++;
                left++;
            }
            direction = (direction + 1) % 4;
        }
        return matrix;
    }
}