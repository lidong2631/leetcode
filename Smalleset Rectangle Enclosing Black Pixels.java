public class Solution {
    int maxX = 0, maxY = 0, minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
    
    public int minArea(char[][] image, int x, int y) {
        if(image==null || image.length==0 || image[0].length==0)
            return 0;
        dfs(image, x, y);
        return (maxX-minX+1)*(maxY-minY+1);
    }
    
    private void dfs(char[][] image, int x, int y) {
        if(x<0 || y<0 || x>=image.length || y>=image[0].length || image[x][y]!='1')
            return;
        image[x][y] = '0';
        maxX = Math.max(maxX, x);
        minX = Math.min(minX, x);
        maxY = Math.max(maxY, y);
        minY = Math.min(minY, y);
        dfs(image, x+1, y);
        dfs(image, x-1, y);
        dfs(image, x, y+1);
        dfs(image, x, y-1);
    }
}

DFS complexity is O(m * n) and if binary search it would be O(n * lgm + m * lgn)

https://leetcode.com/discuss/68233/java-dfs-solution-and-seeking-for-a-binary-search-solution


Binary Search Soluction https://leetcode.com/discuss/68246/c-java-python-binary-search-solution-with-explanation