http://www.geeksforgeeks.org/trie-insert-and-search/

class TrieNode {
    TrieNode[] children;
    boolean endOfWord;
    
    // Initialize your data structure here.
    public TrieNode() {
        children = new TrieNode[26];
        endOfWord = false;
    }
}

public class Trie {
    private TrieNode root;
    

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode p = root;
        
        for(int i=0; i<word.length(); i++) {
            int index = word.charAt(i)-'a';
            if(p.children[index]==null)
                p.children[index] = new TrieNode();
            p = p.children[index];
        }
        p.endOfWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode p = root;
        
        for(int i=0; i<word.length(); i++) {
            int index = word.charAt(i)-'a';
            if(p.children[index]==null)
                return false;
            p = p.children[index];
        }
        return p!=null && p.endOfWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode p = root;
        
        for(int i=0; i<prefix.length(); i++) {
            int index = prefix.charAt(i)-'a';
            if(p.children[index]==null)
                return false;
            p = p.children[index];
        }
        return true;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");