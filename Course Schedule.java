There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.




Java:
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
            if (detectCycle(visited, recStack, i, adjList)) 
                return false;
        }
        return true;
    }
    
    private boolean detectCycle(boolean[] visited, boolean[] recStack, int i, List<List<Integer>> adjList) {
        if (!visited[i]) {
            visited[i] = true;
            recStack[i] = true;
            List<Integer> list = adjList.get(i);
            for (int j = 0; j < list.size(); j++) {
                if (!visited[list.get(j)] && detectCycle(visited, recStack, list.get(j), adjList)) 
                    return true;
                else if (recStack[list.get(j)]) 
                    return true;
            }
        }
        recStack[i] = false;        // careful this need to be executed outside if
        return false;
    }
}
[1, 0], [2, 1]
2 -> 1 -> 0

问题的本质是detect cycle in a Directed Graph dfs遍历有向图相当于产生一颗树 每一次递归相当于遍历了树的一条枝 如果有cycle 则会有backedge

DFS for a connected graph produces a tree


Time Complexity is O(E+V) same as DFS traversal 

http://www.geeksforgeeks.org/detect-cycle-in-a-graph/
