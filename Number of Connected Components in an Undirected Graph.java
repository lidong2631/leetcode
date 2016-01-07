public class Solution {
    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        
        for(int i=0; i<edges.length; i++) {
            int x = find(parent, edges[i][0]);
            int y = find(parent, edges[i][1]);
            
            if(x!=y) {
                parent[x] = y;
                n--;
            }
        }
        return n;
    }
    
    private int find(int[] parent, int i) {
        if(parent[i]==-1)
            return i;
        return find(parent, parent[i]);
    }
}

O(n)

same as Graph Valid Tree Union Find