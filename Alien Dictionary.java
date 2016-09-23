public class Solution {
    int N = 26;
    
    public String alienOrder(String[] words) {
        boolean[][] adj = new boolean[N][N];
        int[] visited = new int[N];
        BuildAdj(words, adj, visited);
        
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < N; i++) {       // careful
            if (visited[i] == 0) {          // careful
                if (!tps(res, i, adj, visited))
                    return "";
            }
        }
        return res.reverse().toString();
    }
    
    private boolean tps(StringBuffer res, int i, boolean[][] adj, int[] visited) {
        visited[i] = 1;
        for (int j = 0; j < N; j++) {
            if (adj[i][j]) {
                if (visited[j] == 1) return false;      // check cycle
                if (visited[j] == 0) {
                    if (!tps(res, j, adj, visited)) return false;
                } 
            }
        }
        visited[i] = 2;
        res.append((char)('a' + i));
        return true;
    }
    
    private void BuildAdj(String[] words, boolean[][] adj, int[] visited) {
        Arrays.fill(visited, -1);
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray())
                visited[c-'a'] = 0;
            if (i > 0) {
                String s1 = words[i-1], s2 = words[i];      //careful
                int len = Math.min(s1.length(), s2.length());
                for (int j = 0; j < len; j++) {
                    char c1 = s1.charAt(j), c2 = s2.charAt(j);
                    if (c1 != c2) {
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

BuildGraph():
adj[t][f] = true
adj[w][e] = true
adj[r][t] = true
adj[e][r] = true

dfs():
initially e f r t w
e -> r -> t -> f
visited[e] = 2, visited[r] = 2, visited[t] = 2, visited[f] = 2
res: ftre
visited[w] = 2
res: ftrew



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



There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. 

You receive a list of words from the dictionary, wherewords are sorted lexicographically by the rules of this 

new language. Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:

You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.