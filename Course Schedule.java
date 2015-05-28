public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites==null || prerequisites.length==0 || prerequisites[0].length==0)
            return true;
        boolean[] visited = new boolean[numCourses];    //dfs中标记节点是否访问过 一旦设为true就不会再变回来
        boolean[] recStack = new boolean[numCourses];   //标记当前递归栈中该节点是否已访问过 
                                                    //如果当前一轮递归访问完没有cycle 会重新设为false
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for(int i=0; i<numCourses; i++)       //先构建一个adjacency list
            res.add(new ArrayList<Integer>());
        for(int i=0; i<prerequisites.length; i++) {
            for(int j=1; j<prerequisites[i].length; j++)
                res.get(prerequisites[i][0]).add(prerequisites[i][j]);
        }
        
        for(int i=0; i<numCourses; i++) {       //dfs
            if(helper(i, visited, recStack, res))   //helper用来detect cycle
                return false;
        }
        return true;
    }
    
    private boolean helper(int v, boolean[] visited, boolean[] recStack, List<List<Integer>> res) {
        if(!visited[v]) {
            visited[v] = true;
            recStack[v] = true;
            
            List<Integer> item = res.get(v);    //拿到当前点的邻居list
            for(int i=0; i<item.size(); i++) {
                if(!visited[item.get(i)] && helper(item.get(i), visited, recStack, res))    //dfs将遇到的节点visited和recstack都设为true
                    return true;
                else if(recStack[item.get(i)])  //如果已经访问过这个邻居节点并且其rec记录为true 说明有cycle
                    return true;
            }
        }
        recStack[v] = false;    //如果遍历完当前点所有邻居都没有cycle 要将rec重新设为false
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
