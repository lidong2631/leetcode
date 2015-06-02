Leetcode Data Structure

Two Sum iii - Data Structure design
设计一个two sum的数据结构 add加数字 find找两个数和为value
private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

public void add(int number) {
	int count = map.containsKey(number)?map.get(number):0;
	map.put(number, count+1);
}

public boolean find(int value) {
	for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
		int num = entry.getKey();
		int y = value - num;
		if(y==value) {
			if(map.get(y)>=2)
				return true;
		}
		else if(map.containsKey(y))
			return true;
	}
	return false;
}

add O(1) find O(n) space complexity O(n)





Min Stack
cc150也有这题 在栈基础上多加了一个返回最小值操作 用两个栈实现
Stack<Integer> stack = new Stack<Integer>();
Stack<Integer> minStack = new Stack<Integer>();

public void push(int x) {
	if(minStack.empty() || x<=minStack.peek())
		minStack.push(x);
	stack.push(x);
}

public int pop() {
	if(stack.empty())
		return;
	int x = stack.pop();
	if(!minStack.empty() && x==stack.peek())
		minStack.pop();
	return x;
}

public int top() {
	return stack.peek();
}

public int getMin() {
	return minStack.peek();
}

push O(1) pop O(1) space complexity O(n)





LRU Cache
实现一个least recent used cache 用 Doubly Linked List + HashMap
public class LRUCache {
	class Node {
		Node pre, next;
		int key;
		int val;

		public Node(int key, int val) {
			this.key = key;
			this.val = val;
		}
	}

	private int capacity;
	private int num;
	private Map<Integer, Node> map;
	private Node first, last;

	public LRUCache(int c) {
		this.capacity = c;
		num = 0;
		map = new HashMap<Integer, Node>();
		first = null;
		last = null;
	}

	public int get(int key) {
		Node curr = map.get(key);
		if(curr==null)
			return -1;
		else if(curr!=last) {
			if(curr==first) {
				first = first.next;
			}
			else {
				curr.pre.next = curr.next;
			}
			curr.next.pre = curr.pre;
			last.next = curr;
			curr.pre = last;
			curr.next = null;
			last = curr;
		}
		return curr.val;
	}

	public void set(int key, int val) {
		Node curr = map.get(key);
		if(curr!=null) {
			curr.val = val;
			if(curr!=last) {
				if(curr==first) {
					first = first.next;
				}
				else {
					curr.pre.next = curr.next;
				}
				curr.next.pre = curr.pre;
				last.next = curr;
				curr.pre = last;
				curr.next = null;
				last = curr;
			}
		}
		else {
			Node newNode = new Node(key, val);
			if(num>=capacity) {
				map.remove(first.key);
				first = first.next;
				if(first!=null)
					first.pre = null;
				else
					last = null;
				num--;
			}
			if(first==null || last==null)
				first = newNode;
			else
				last.next = newNode;
			newNode.pre = last;
			last = newNode;
			map.put(key, newNode);
			num++;
		}
	}
}

get: O(1) set: O(1) space complexity: HashMap开销以及每个节点开销(前后指针和key value)





Implement Trie (Prefix Tree)
实现字典树
class TrieNode {
	TrieNode[] children;
	boolean endOfWord;

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

	public void insert(String word) {
		TrieNode p = root;
		
		for(int i=0; i<word.length; i++) {
			int index = word.charAt(i)-'a';
			if(p.children[index]==null)
				p.children[index] = new TrieNode();
			p = p.children[index];
		}
		p.endOfWord = true;
	}

	public boolean search(String word) {
		TrieNode p = root;

		for(int i=0; i<word.length; i++) {
			int index = word.charAt(i)-'a';
			if(p.children[index]==null)
				return false;
			p = p.children[index];
		}
		return p.endOfWord;
	}

	public boolean startsWith(String prefix) {
		TrieNode p = root;

		for(int i=0; i<prefix.length; i++) {
			int index = prefix.charAt(i)-'a';
			if(p.children[index]==null)
				return false;
			p = p.children[index];
		}
		return true;
	}
}

O(key_length) O(Alphabet_size * key_length *N)





Add and Search Word - Data Structure design
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




