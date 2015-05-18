class TrieNode {
    TrieNode[] children;
    boolean endOfWord;
    
    // Initialize your data structure here.
    public TrieNode() {
        children = new TrieNode[26];
        endOfWord = false;
    }
}

public class WordDictionary {
    private TrieNode root;
    
    public WordDictionary() {
        root = new TrieNode();
    }
    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode p = root;
        
        for(int i=0; i<word.length(); i++) {
            int index = word.charAt(i)-'a';
            if(p.children[index]==null)
                p.children[index] = new TrieNode();
            p = p.children[index];
        }
        p.endOfWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {     //相比于上一题 主要这部分有改变
        return helper(word, 0, root);
    }
    
    private boolean helper(String word, int index, TrieNode p) {
        if(index==word.length())
            return p.endOfWord;
        if(word.charAt(index)=='.') {   //如果是'.'
            for(int i=0; i<26; i++) {
                TrieNode tmp = p.children[i];   //注意这里要新建一个变量TrieNode tmp来存值 不能写p = p.children[i];
                if(tmp!=null && helper(word, index+1, tmp))     //DFS
                    return true;
            }
            return false;
        }
        else {      //不是'.'
            int n = word.charAt(index)-'a';
            if(p.children[n]==null)
                return false;
            return helper(word, index+1, p.children[n]);
        }
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");


这题相较implement trie实际只是更改了search() 用DFS递归处理了'.'的情况
http://www.cnblogs.com/grandyang/p/4507286.html
http://www.meetqun.com/thread-9245-1-1.html





