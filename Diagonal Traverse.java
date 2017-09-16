Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

Example:
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output:  [1,2,4,7,5,3,6,8,9]
Explanation:

Note:
The total number of elements of the given matrix will not exceed 10,000.


class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) 
            return new int[0];
        int m = matrix.length, n = matrix[0].length;
        int[] res = new int[m*n];
        int row = 0, col = 0, d = 0;
        int[][] move = {{-1,1}, {1,-1}};
        
        for (int i = 0; i < m * n; i++) {
            res[i] = matrix[row][col];
            row += move[d][0]; col += move[d][1];
            
            if (row >= m) {
                row = m - 1; col += 2; d = 1 - d;
            }
            if (col >= n) {
                col = n - 1; row += 2; d = 1 - d;
            }
            if (row < 0) {
                row = 0; d = 1 - d;
            }
            if (col < 0) {
                col = 0; d = 1 - d;
            }
        }
        return res;
    }
}

O(mn)

If out of bottom border (row >= m) then row = m - 1; col += 2; change walk direction.
if out of right border (col >= n) then col = n - 1; row += 2; change walk direction.
if out of top border (row < 0) then row = 0; change walk direction.
if out of left border (col < 0) then col = 0; change walk direction.
Otherwise, just go along with the current direction.