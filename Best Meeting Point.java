A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, 

where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, 

where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.

Hint:

Try to solve it in one dimension first. How can this solution apply to the two dimension case?


1D:  we can use two pointers to solve the 1D problem. left and right are how many people one left/right side of coordinates i/j. 
If we have more people on the left we let j decrease otherwise increase i

For the 2D cases we first need to sum the columns and rows into two vectors and call the 1D algorithm



Java:
public class Solution {
    public int minTotalDistance(int[][] grid) {
        int[] row = new int[grid.length], col = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                row[i] += grid[i][j];
                col[j] += grid[i][j];
            }
        }
        return minDis1D(row) + minDis1D(col);
    }
    
    private int minDis1D(int[] dis) {
        int i = -1, j = dis.length, left = 0, right = 0, d = 0;
        while (i != j) {
            if (left < right) {
                d += left;
                left += dis[++i];
            }
            else {
                d += right;
                right += dis[--j];
            }
        }
        return d;
    }
}

row: 2 0 1
col: 1 0 1 0 1

O(m*n) O(m+n)

Moreover, the solution is still O(mn) with the follow up:
What if there are people sharing same home? In other words the number in the grid can be more than 1

https://leetcode.com/discuss/65464/java-python-40ms-pointers-solution-median-sort-explanation

https://leetcode.com/discuss/65336/14ms-java-solution