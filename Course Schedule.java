public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++)
            adjList.add(new ArrayList<>());
        for (int[] p : prerequisites)
            adjList.get(p[0]).add(p[1]);
        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (helper(visited, recStack, i, adjList)) return false;
        }
        return true;
    }
    
    private boolean helper(boolean[] visited, boolean[] recStack, int i, List<List<Integer>> adjList) {
        if (!visited[i]) {
            visited[i] = true;
            recStack[i] = true;
            List<Integer> list = adjList.get(i);
            for (int j = 0; j < list.size(); j++) {
                if (!visited[list.get(j)] && helper(visited, recStack, list.get(j), adjList)) return true;
                else if (recStack[list.get(j)]) return true;
            }
        }
        recStack[i] = false;        // careful this need to be executed outside if
        return false;
    }
}

问题的本质是detect cycle in a Directed Graph dfs遍历有向图相当于产生一颗树 每一次递归相当于遍历了树的一条枝 如果有cycle 则会有backedge

DFS for a connected graph produces a tree

There is a cycle int a graph only if there is a back edge(from a node to itself or one of its ancestors in the tree)

To detect a back edge, we keep track of vertices in current rec stack of function for DFS traversal

there is a cycle if a vertex is already in rec stack

The edge that connects current vertex to the vertex in rec stack is a back edge 

Time Complexity is O(E+V) same as DFS traversal 

http://www.geeksforgeeks.org/detect-cycle-in-a-graph/
