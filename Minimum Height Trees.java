public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        List<List<Integer>> adjList = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (adjList.get(i).size() == 1) leaves.add(i);
        }
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



public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> leaves = new ArrayList<Integer>();
        if(n==1) {
            leaves.add(0);
            return leaves;
        }
        
        List<Set<Integer>> adj = new ArrayList<Set<Integer>>();
        for(int i=0; i<n; i++) {                                        // create adj list
            adj.add(new HashSet<Integer>());
        }
        for(int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        
        for(int i=0; i<n; i++) {                                        // create leaves list
            if(adj.get(i).size()==1) {
                leaves.add(i);
            }
        }
        while(n>2) {                                                    // every remove leaf nodes
            List<Integer> newLeaves = new ArrayList<Integer>();
            n-=leaves.size();
            for(int i : leaves) {
                int j = adj.get(i).iterator().next();
                adj.get(j).remove(i);                                   // remove leaf node
                if(adj.get(j).size()==1)                                // add new leaf node to list
                    newLeaves.add(j);
            }
            leaves = newLeaves;
        }
        return leaves;
    }
}

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