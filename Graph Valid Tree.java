Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.



Java:
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        for (int i = 0; i < edges.length; i++) {
            int x = find(parent, edges[i][0]);
            int y = find(parent, edges[i][1]);
            
            if (x == y) return false;
            
            parent[x] = y;
        }
        return edges.length == n - 1;
    }
    
    private int find(int[] parent, int i) {
        if (parent[i] == -1) return i;
        return find(parent, parent[i]);
    }
}

This question is asking whether there is cycle in graph




union find

A disjoint-set data structure is a data structure that keeps track of a set of elements partitioned into a number of disjoint 
(non-overlapping) subsets. A union-find algorithm is an algorithm that performs two useful operations on such a data structure:

Find: Determine which subset a particular element is in. This can be used for determining if two elements are in the same subset.

Union: Join two subsets into a single subset.


http://www.geeksforgeeks.org/union-find/
https://leetcode.com/discuss/52563/ac-java-union-find-solution


https://leetcode.com/discuss/52568/ac-java-graph-dfs-solution-with-adjacency-list

public class Solution {
    public boolean validTree(int n, int[][] edges) {
        // initialize adjacency list
        List<List<Integer>> adjList = new ArrayList<List<Integer>>(n);

        // initialize vertices
        for (int i = 0; i < n; i++)
            adjList.add(i, new ArrayList<Integer>());

        // add edges    
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        boolean[] visited = new boolean[n];

        // make sure there's no cycle
        if (hasCycle(adjList, 0, visited, -1))
            return false;

        // make sure all vertices are connected
        for (int i = 0; i < n; i++) {
            if (!visited[i]) 
                return false;
        }

        return true;
    }

    // check if an undirected graph has cycle started from vertex u
    boolean hasCycle(List<List<Integer>> adjList, int u, boolean[] visited, int parent) {
        visited[u] = true;

        for (int i = 0; i < adjList.get(u).size(); i++) {
            int v = adjList.get(u).get(i);

            if ((visited[v] && parent != v) || (!visited[v] && hasCycle(adjList, v, visited, u)))
                return true;
        }

        return false;
    }
}