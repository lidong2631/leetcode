Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3




Java:
public class Solution {
    private int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) 
            return 0;
        int count = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (isValid(grid, i, j, visited)) {
                    dfs(grid, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }
    
    private void dfs(char[][] grid, int i, int j, boolean[][] visited) {
        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            if (isValid(grid, i+move[k][0], j+move[k][1], visited)) {
                dfs(grid, i+move[k][0], j+move[k][1], visited);
            }
        }
    }
    
    private boolean isValid(char[][] grid, int i, int j, boolean[][] visited) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == '1' && !visited[i][j]) 
            return true;
        return false;
    }
}

This is an variation of the standard problem: “Counting number of connected components in a undirected graph”.

Time Complexity: O(row*col) 306ms

http://www.geeksforgeeks.org/find-number-of-islands/


Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by 

connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3





bfs解法

public class Solution {
    class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int numIslands(char[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0)
            return 0;
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(grid[i][j]=='1') {
                    bfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int i, int j) {
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.offer(new Pair(i, j));
        while(!queue.isEmpty()) {
            Pair p = queue.poll();
            grid[p.x][p.y] = '#';
            if(isValid(grid, p.x-1, p.y)) {
                grid[p.x-1][p.y] = '#';
                queue.offer(new Pair(p.x-1, p.y));
            }
            if(isValid(grid, p.x+1, p.y)) {
                grid[p.x+1][p.y] = '#';
                queue.offer(new Pair(p.x+1, p.y));    
            }
            if(isValid(grid, p.x, p.y-1)) {
                grid[p.x][p.y-1] = '#';
                queue.offer(new Pair(p.x, p.y-1));
            }
            if(isValid(grid, p.x, p.y+1)) {
                grid[p.x][p.y+1] = '#';
                queue.offer(new Pair(p.x, p.y+1));
            }
        }
    }

    private boolean isValid(char[][] grid, int i, int j) {
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length)
            return false;
        if(grid[i][j]=='1')
            return true;
        return false;
    }
}

bfs 每次遍历完一个land('1')的四周四个点 将符合条件的点加入队列 然后再跳到队列中的下一个点 436ms
http://blog.csdn.net/guorudi/article/details/44997949