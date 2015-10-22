public class Solution {
    public void wallsAndGates(int[][] rooms) {
        if(rooms==null || rooms.length==0 || rooms[0].length==0)
            return;
        Queue<int[]> queue = new LinkedList<int[]>();
        for(int i=0; i<rooms.length; i++) {
            for(int j=0; j<rooms[0].length; j++) {
                if(rooms[i][j]==0) {
                    int[] arr = {i,j};
                    queue.add(arr);
                }
            }
        }
        while(!queue.isEmpty()) {
            int[] tmp = queue.remove();
            int row = tmp[0], col = tmp[1];
            if(row>0 && rooms[row-1][col]==Integer.MAX_VALUE) { //up
                rooms[row-1][col] = rooms[row][col]+1;
                queue.add(new int[]{row-1, col});
            }
            if(row<rooms.length-1 && rooms[row+1][col]==Integer.MAX_VALUE) {    //down
                rooms[row+1][col] = rooms[row][col]+1;
                queue.add(new int[]{row+1, col});
            }
            if(col>0 && rooms[row][col-1]==Integer.MAX_VALUE) { //left
                rooms[row][col-1] = rooms[row][col]+1;
                queue.add(new int[]{row, col-1});
            }
            if(col<rooms[0].length-1 && rooms[row][col+1]==Integer.MAX_VALUE) { //right
                rooms[row][col+1] = rooms[row][col]+1;
                queue.add(new int[]{row, col+1});
            }
        }
    }
}

BFS 
Push all gates into queue first. Then for each gate update its neighbor cells and push them to the queue.

Repeating above steps until there is nothing left in the queue.

O(m*n) O(m*n)

https://leetcode.com/discuss/60179/java-bfs-solution-o-mn-time