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

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to 
find the number of connected components in an undirected graph.

Example 1:
     0          3
     |          |
     1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:
     0           4
     |           |
     1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

O(n)

same as Graph Valid Tree Union Find


DFS

public class Solution {
    public int countComponents(int n, int[][] edges) {
        int count = 0;
        List<List<Integer>> adjList = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(adjList, visited, i);
                count++;
            }
        }
        return count;
    }
    
    private void dfs(List<List<Integer>> adjList, boolean[] visited, int i) {
        visited[i] = true;
        List<Integer> adj = adjList.get(i);
        for (Integer j : adj) {
            if (!visited[j])
                dfs(adjList, visited, j);
        }
    }
}

BFS
public class Solution {

    public int countComponents(int n, int[][] edges) {
        if (n <= 1) {
            return n;
        }
        List<List<Integer>> adjList = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                Queue<Integer> queue = new LinkedList<Integer>();
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int index = queue.poll();
                    visited[index] = true;
                    for (int next : adjList.get(index)) {
                        if (!visited[next]) {
                            queue.offer(next);
                        }
                    }
                }
            }
        }

        return count;
    }
}
https://leetcode.com/discuss/93661/java-union-find-%26-dfs-%26-bfs-code-very-clean