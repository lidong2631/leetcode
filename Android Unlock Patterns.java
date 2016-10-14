public class Solution {
    public int numberOfPatterns(int m, int n) {
        int[][] jump = new int[10][10];
        jump[1][3] = jump[3][1] = 2;                                // get middle num between two nums if it exists
        jump[1][7] = jump[7][1] = 4;
        jump[2][8] = jump[8][2] = 5;
        jump[3][9] = jump[9][3] = 6;
        jump[7][9] = jump[9][7] = 8;
        jump[4][6] = jump[6][4] = 5;
        jump[1][9] = jump[9][1] = jump[3][7] = jump[7][3] = 5;
        boolean[] visited = new boolean[10];
        int count = 0;
        count += dfs(jump, visited, 1, 1, 0, m, n) * 4;             // 1 3 7 9
        count += dfs(jump, visited, 2, 1, 0, m, n) * 4;             // 2 4 6 8
        count += dfs(jump, visited, 5, 1, 0, m, n);                 // 5
        return count;
    }
    
    private int dfs(int[][] jump, boolean[] visited, int start, int len, int count, int m, int n) {
        if (len >= m) count++;  // careful this goes first
        len++;
        if (len > n) return count;  // careful 
        visited[start] = true;
        for (int i = 1; i <= 9; i++) {
            int tmp = jump[start][i];
            if (!visited[i] && (tmp == 0 || visited[tmp]))          // if not visited and no middle num or the middle num has been visited
                count = dfs(jump, visited, i, len, count, m, n);    // careful not "count +=" because line 13 14 we multiply 4
        }
        visited[start] = false;
        return count;
    }
}


Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns 

of the Android lock screen, which consist of minimum of m keys and maximum n keys.

Rules for a valid pattern:
Each pattern must connect at least m keys and at most n keys.

All the keys must be distinct.

If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously 

selected in the pattern. No jumps through non selected key is allowed.

The order of keys used matters.


| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |

Invalid move: 4 - 1 - 3 - 6 
Line 1 - 3 passes through key 2 which had not been selected in the pattern.

Invalid move: 4 - 1 - 9 - 2
Line 1 - 9 passes through key 5 which had not been selected in the pattern.

Valid move: 2 - 4 - 1 - 3 - 6
Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

Valid move: 6 - 5 - 4 - 1 - 9 - 2
Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.

Example:
Given m = 1, n = 1, return 9.


https://leetcode.com/discuss/104688/simple-and-concise-java-solution-in-69ms




