Implement a trie with insert, search, and startsWith methods.

Example:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true
Note:

You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.




Java:
class TrieNode {
    TrieNode[] children;
    boolean isLeaf;
    
    // Initialize your data structure here.
    public TrieNode() {
        children = new TrieNode[26];
        isLeaf = false;
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
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (p.children[index] == null)
                p.children[index] = new TrieNode();
            p = p.children[index];
        }
        p.isLeaf = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (p.children[index] == null) 
                return false;
            p = p.children[index];
        }
        return p.isLeaf;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode p = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (p.children[index] == null) 
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


O(key_length) O(Alphabet_size * key_length *N)



http://www.geeksforgeeks.org/trie-insert-and-search/




