public class Solution {
    public boolean validTree(int n, int[][] edges) {
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        
        for(int i=0; i<edges.length; i++) {
            int x = find(parent, edges[i][0]);
            int y = find(parent, edges[i][1]);
            
            if(x==y)        //there is cycle
                return false;
            parent[x] = y;
        }
        return edges.length==n-1;
    }
    
    private int find(int[] parent, int i) { //find will recursively find the representative for the current element
        if(parent[i]==-1)   //find representative
            return i;
        return find(parent, parent[i]); //recursively call
    }
}

a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, 
any connected graph without simple cycles is a tree
This question is asking whether there is cycle in graph

union find

A disjoint-set data structure is a data structure that keeps track of a set of elements partitioned into a number of disjoint 
(non-overlapping) subsets. A union-find algorithm is an algorithm that performs two useful operations on such a data structure:

Find: Determine which subset a particular element is in. This can be used for determining if two elements are in the same subset.

Union: Join two subsets into a single subset.


http://www.geeksforgeeks.org/union-find/
https://leetcode.com/discuss/52563/ac-java-union-find-solution