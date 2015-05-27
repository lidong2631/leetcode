public class Solution {
    public int numIslands(char[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0)
            return 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(isValid(grid, i, j, visited)) {
                    dfs(grid, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }
    
    private void dfs(char[][] grid, int i, int j, boolean[][] visited) {
        int[] rowNum = {-1, 0, 0, 1};
        int[] colNum = {0, 1, -1, 0};
        
        visited[i][j] = true;
        for(int k=0; k<4; k++) {
            if(isValid(grid, i+rowNum[k], j+colNum[k], visited))
                dfs(grid, i+rowNum[k], j+colNum[k], visited);
        }
    }
    
    private boolean isValid(char[][] grid, int i, int j, boolean[][] visited) {
        if(i>=0 && i<grid.length && j>=0 && j<grid[0].length && grid[i][j]=='1' && !visited[i][j])
            return true;
        return false;
    }
}

This is an variation of the standard problem: “Counting number of connected components in a undirected graph”.

Time Complexity: O(row*col) 306ms

http://www.geeksforgeeks.org/find-number-of-islands/





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
        int res = 0;
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(grid[i][j]=='1') {
                    bfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
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