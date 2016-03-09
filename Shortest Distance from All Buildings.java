public class Solution {
    int[][] move = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    
    public int shortestDistance(int[][] grid) {
        int res = -1;
        int m = grid.length, n = grid[0].length;
        int[][] dis = new int[m][n];
        List<Tuple> buildings = new ArrayList<Tuple>();
        
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j]==1)
                    buildings.add(new Tuple(i, j, 0));                          // add to buildings list
                grid[i][j] = -grid[i][j];                                       // change building and block to negative value
            }
        }
        for(int k=0; k<buildings.size(); k++) {                                 // for every building do bfs
            bfs(grid, k, buildings.get(k), dis, m, n);
        }
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j]==buildings.size() && (res==-1 || dis[i][j]<res))  // for every empty land compare it with res
                    res = dis[i][j];                                            // if it is smaller than res update res
            }
        }
        return res;
    }
    
    private void bfs(int[][] grid, int k, Tuple p, int[][] dis, int m, int n) {
        Queue<Tuple> queue = new LinkedList<Tuple>();
        queue.add(p);
        while(!queue.isEmpty()) {
            Tuple curr = queue.poll();
            dis[curr.x][curr.y]+=curr.dis;                                      // every time add dis[][] with curr dis
            for(int i=0; i<4; i++) {                                            // bfs 4 directions
                int x = curr.x+move[i][0], y = curr.y+move[i][1];
                if(x>=0 && y>=0 && x<m && y<n && grid[x][y]==k) {               // if new x y are valid
                    grid[x][y] = k + 1;                                         // update grid[][]
                    queue.add(new Tuple(x, y, curr.dis+1));                     // add new Tuple to queue
                }
            }
        }
    }
}

class Tuple {
    int x;
    int y;
    int dis;
    
    public Tuple(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.dis = d;
    }
}


input: 
1 0 2 0 1
0 0 0 0 0
0 0 1 0 0

1st
grid                    dis
-1 1 -2 1 -1            0 1 0 5 0
 1 1  1 1  1            1 2 3 4 5
 1 1 -1 1  1            2 3 0 5 6


2nd
grid                    dis
-1 2 -2 2 -1            0 6 0 6 0
 2 2  2 2  2            6 6 6 6 6
 2 2 -1 2  2            8 8 0 8 8


3rd
grid                    dis
-1 3 -2 3 -1            0 9 0 9 0
 3 3  3 3  3            9 8 7 8 9
 3 3 -1 3  3           10 9 0 9 10

res = 7


https://leetcode.com/discuss/76018/share-a-java-implement