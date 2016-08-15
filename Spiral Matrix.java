class Solution:
    # @param matrix, a list of lists of integers
    # @return a list of integers
    def spiralOrder(self, matrix):
        if matrix == []:
            return []
        up = 0; down = len(matrix)-1; left = 0; right = len(matrix[0])-1
        direction = 0
        res = []
        while(True):
            if direction == 0:
                for i in range(left, right + 1):
                    res.append(matrix[up][i])
                up += 1
                
            if direction == 1:
                for i in range(up, down + 1):
                    res.append(matrix[i][right])
                right-=1
                
            if direction == 2:
                for i in range(right, left-1, -1):
                    res.append(matrix[down][i])
                down -= 1
                
            if direction == 3:
                for i in range(down, up-1, -1):
                    res.append(matrix[i][left])
                left += 1
                
            if up > down or left > right:
                return res
            direction = (direction + 1) % 4



public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int left = 0, up = 0, right = matrix[0].length - 1, down = matrix.length - 1, direction = 0;
        while (left <= right && up <= down) {
            if (direction == 0) {
                for (int i = left; i <= right; i++)
                    res.add(matrix[up][i]);
                up++;
            }
            if (direction == 1) {
                for (int i = up; i <= down; i++)
                    res.add(matrix[i][right]);
                right--;
            }
            if (direction == 2) {
                for (int i = right; i >= left; i--)
                    res.add(matrix[down][i]);
                down--;
            }
            if (direction == 3) {
                for (int i = down; i >= up; i--)
                    res.add(matrix[i][left]);
                left++;
            }
            direction = (direction + 1) % 4;
        }
        return res;
    }
}