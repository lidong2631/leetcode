public class Solution {
    
    int[][] moves = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if (m < 0 && n < 0) return res;
        int[] arr = new int[m * n];
        Arrays.fill(arr, -1);
        int count = 0;
        
        for (int[] pos : positions) {
            int index = pos[0] * n + pos[1];
            arr[index] = index;
            count++;
            
            for (int[] move: moves) {
                int r = pos[0] + move[0];
                int c = pos[1] + move[1];
                int newIndex = r * n + c;
                if (r < 0 || c < 0 || r >= m || c >= n || arr[newIndex] == -1) continue;
                int parent = find(arr, newIndex);
                if (parent != index) {
                    arr[index] = parent;
                    index = parent;
                    count--;
                }
            }
            res.add(count);
        }
        return res;
    }
    
    private int find(int[] parent, int i) {
        if (parent[i] != i) return find(parent, parent[i]);
        return i;
    }
}


Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]




https://discuss.leetcode.com/topic/29613/easiest-java-solution-with-explanations
http://www.geeksforgeeks.org/union-find/

https://leetcode.com/discuss/69392/python-clear-solution-unionfind-class-weighting-compression