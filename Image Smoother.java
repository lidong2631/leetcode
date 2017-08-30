Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of 
all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.

Example 1:
Input:
[[1,1,1],
 [1,0,1],
 [1,1,1]]
Output:
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]
Explanation:
For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
For the point (1,1): floor(8/9) = floor(0.88888889) = 0
Note:
The value in the given matrix is in the range of [0, 255].
The length and width of the given matrix are in the range of [1, 150].



class Solution {
    public int[][] imageSmoother(int[][] M) {
        int row = M.length, col = M[0].length;
        int[][] move = {{0,0}, {0,1}, {1,0}, {-1,0}, {0,-1}, {-1,1}, {-1,-1}, {1,-1}, {1,1}};
        int[][] res = new int[row][col];
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int count = 0, sum = 0;
                for (int[] m : move) {
                    if (isValid(i+m[0], j+m[1], row, col)) {
                        count++;
                        sum += M[i+m[0]][j+m[1]];
                    }
                }
                res[i][j] = sum / count;
            }
        }
        return res;
    }
    
    private boolean isValid(int i, int j, int row, int col) {
        return i >= 0 && i < row && j >=0 && j < col;
    }
}

O(mn) O(mn)