For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, 
those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1 :

Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3 

Output: [1]
Example 2 :

Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5 

Output: [3, 4]
Note:

According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, 
any connected graph without simple cycles is a tree.”
The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.


Java:
public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        // Creat adjency List
        List<List<Integer>> adjList = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        // Create leaves list
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (adjList.get(i).size() == 1) leaves.add(i);
        }
        // Find root
        while (n > 2) {
            List<Integer> newLeaves = new ArrayList<>();
            n -= leaves.size();
            for (Integer i : leaves) {          // careful need to write Integer not int
                int j = adjList.get(i).get(0);
                adjList.get(j).remove(i);       // careful
                if (adjList.get(j).size() == 1) newLeaves.add(j);
            }
            leaves = newLeaves;
        }
        return leaves;
    }
}

In Java List has 2 remove
remove(Object o)
remove(int index)
here I use first one so in for loop need to write Integer otherwise it will use second method

O(n)


n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

0   1   2
 \  |  /
    3
    |
    4
    |
    5

0: HashSet
1: HashSet
2: HashSet
3: HashSet
4: HashSet
5: HashSet

0: 3 leave
1: 3 leave
2: 3 leave
3: 0 1 2 4
4: 3 5
5: 4 leave

return [3, 4]

https://leetcode.com/discuss/71763/share-some-thoughts

