public class Solution {
    
    int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0) return res;
        Arrays.sort(words);
        for (int i = 0; i < words.length; i++) {
            if (i > 0 && words[i].equals(words[i-1])) continue;
            if (isValid(board, words[i])) res.add(words[i]);
        }
        return res;
    }
    
    private boolean isValid(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, visited, word, 0, i, j)) return true;
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, boolean[][] visited, String word, int len, int i, int j) {
        if (len == word.length()) return true;
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j] || word.charAt(len) != board[i][j])
            return false;
        visited[i][j] = true;
        boolean res = false;
        for (int k = 0; k < 4; k++) {
            int r = i + move[k][0], c = j + move[k][1];
            if (dfs(board, visited, word, len+1, r, c)) {
                res = true;
                break;
            }
        }
        visited[i][j] = false;
        return res;
    }
}

Time Limit Exceed



public class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<String>();
        if(board==null || board.length==0 || board[0].length==0 || words==null || words.length==0)
            return res;
        Arrays.sort(words);
        for(int i=0; i<words.length; i++) {
            if(i>0 && words[i].equals(words[i-1]))
                continue;
            if(exist(board, words[i]))
                res.add(words[i]);
        }
        return res;
    }
    
    public boolean exist(char[][] board, String word) {
        if(word==null || word.length()==0)
            return true;
        if(board==null || board.length==0 || board[0].length==0)
            return false;
        boolean[][] used = new boolean[board.length][board[0].length];
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                if(search(board, 0, i, j, used, word))
                    return true;
            }
        }
        return false;
    }
    
    private boolean search(char[][] board, int index, int i, int j, boolean[][] used, String word) {
        if(index==word.length())
            return true;
        if(i<0 || j<0 || i>=board.length || j>=board[0].length || used[i][j] || word.charAt(index)!=board[i][j])
            return false;
        used[i][j] = true;
        boolean res = (search(board, index+1, i+1, j, used, word)
                        || search(board, index+1, i-1, j, used, word)
                        || search(board, index+1, i, j+1, used, word)
                        || search(board, index+1, i, j-1, used, word));
        used[i][j] = false;
        return res;
    }
}

这种解法跟word search没区别 还是dfs判断word是不是可以由board的字符组成 要再看看运用trie或hash table的解法




Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. 

The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.

click to show hint.

You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?

If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. What kind of data structure could answer 

such query efficiently? Does a hash table work? Why or why not? How about a Trie? If you would like to learn how to implement a basic trie, 

please work on this problem: Implement Trie (Prefix Tree) first.




