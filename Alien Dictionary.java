public class Solution {
    int N = 26;
    
    public String alienOrder(String[] words) {
        boolean[][] adj = new boolean[N][N];
        int[] visited = new int[N];
        BuildGraph(words, adj, visited);
        
        StringBuilder res = new StringBuilder();
        
        for(int i=0; i<N; i++) {                                            // topological sort
            if(visited[i]==0) {
                if(!dfs(adj, visited, res, i))
                    return "";
            }
        }
        return res.reverse().toString();
    }
    
    private boolean dfs(boolean[][] adj, int[] visited, StringBuilder res, int i) {
        visited[i] = 1;
        
        for(int j=0; j<N; j++) {
            if(adj[i][j]) {
                if(visited[j]==1)                                           // has cycle
                    return false;
                if(visited[j]==0) {                                         // if not visited, dfs
                    if(!dfs(adj, visited, res, j))
                        return false;
                }
            }
        }
        visited[i] = 2;                                                     // done visiting
        res.append((char)('a'+i));                                          // be careful need to change into char format
        return true;
    }
    
    private void BuildGraph(String[] words, boolean[][] adj, int[] visited) {
        Arrays.fill(visited, -1);                                       // initially set visited to -1
        
        for(int i=0; i<words.length; i++) {
            for(char c : words[i].toCharArray())
                visited[c-'a'] = 0;                                     // for every character in words mark visited to 0
            if(i>0) {
                String s1 = words[i-1], s2 = words[i];
                int len = Math.min(s1.length(), s2.length());
                for(int j=0; j<len; j++) {                              // build up adjacency matrix
                    char c1 = s1.charAt(j), c2 = s2.charAt(j);
                    if(c1!=c2) {
                        adj[c1-'a'][c2-'a'] = true;
                        break;
                    }
                }
            }
        }
    }
}

topological sort 

normal case:
wrt
wrf
er
ett
rftt

w->e->r->t->f



invalid input:
w
e
w

has cycle so cannot get result



multiple solutions:
g
ze

g->e->z, e->g->z, g->z->e

visited has 4 states:
-1: not exist
0 : exist
1 : visiting
2 : visited


https://leetcode.com/discuss/78602/3ms-clean-java-solution-dfs

Another good solution using BFS
https://leetcode.com/discuss/77078/easiest-java-bfs-solution