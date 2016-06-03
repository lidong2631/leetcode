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
        if (len >= m) count++;
        len++;
        if (len > n) return count;
        visited[start] = true;
        for (int i = 1; i <= 9; i++) {
            int tmp = jump[start][i];
            if (!visited[i] && (tmp == 0 || visited[tmp]))          // if not visited and no middle num or the middle num has been visited
                count = dfs(jump, visited, i, len, count, m, n);    // dfs
        }
        visited[start] = false;
        return count;
    }
}


https://leetcode.com/discuss/104688/simple-and-concise-java-solution-in-69ms