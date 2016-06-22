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
        maxX = Math.max(maxX, x);                   // 对于每一个黑格子做dfs 维护maxX, maxY, minX, minY 最终计算面积
        minX = Math.min(minX, x);
        maxY = Math.max(maxY, y);
        minY = Math.min(minY, y);
        dfs(image, x+1, y);
        dfs(image, x-1, y);
        dfs(image, x, y+1);
        dfs(image, x, y-1);
    }
}

An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., 

there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, 

return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2
return 6


DFS complexity is O(m * n) and if binary search it would be O(n * lgm + m * lgn)

https://leetcode.com/discuss/68233/java-dfs-solution-and-seeking-for-a-binary-search-solution


Binary Search Soluction https://leetcode.com/discuss/68246/c-java-python-binary-search-solution-with-explanation