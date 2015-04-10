public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        char[][] chararr= {{'1'}};
        int n = s.numIslands(chararr);
        System.out.println(n);
    }

    public int numIslands(char[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0)
            return 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                System.out.println("for loop");
                System.out.println(i);
                System.out.println(j);
                System.out.println(grid.length);
                System.out.println(grid[0].length);
                System.out.println(grid[i][j]);
                System.out.println(visited[i][j]);
                if(isValid(grid, i, j, visited)) {
                    System.out.println("isValid");
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
        if(i>=0 && i<grid.length && j>=0 && j<grid[0].length && grid[i][j]=='1' && visited[i][j])
            return true;
        return false;
    }
}