Design a HashSet without using any built-in hash table libraries.

To be specific, your design should include these functions:

add(value): Insert a value into the HashSet. 
contains(value) : Return whether the value exists in the HashSet or not.
remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.

Example:

MyHashSet hashSet = new MyHashSet();
hashSet.add(1);         
hashSet.add(2);         
hashSet.contains(1);    // returns true
hashSet.contains(3);    // returns false (not found)
hashSet.add(2);          
hashSet.contains(2);    // returns true
hashSet.remove(2);          
hashSet.contains(2);    // returns false (already removed)

Note:

All values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashSet library.



Java:
class MyHashSet {

    private Entry[] entrys;
    
    /** Initialize your data structure here. */
    public MyHashSet() {
        entrys = new Entry[10000];
    }
    
    public void add(int key) {
        int index = hashFunc(key);
        if (entrys[index] == null) 
        	entrys[index] = new Entry(key);
        else {
        	Entry curr = entrys[index];
        	while (curr.next != null && curr.key != key)
        		curr = curr.next;
        	if (curr.key != key)
        		curr.next = new Entry(key);
        }
    }
    
    public void remove(int key) {
        int index = hashFunc(key);
        if (entrys[index] == null)
        	return;
        else {
        	Entry prev = null;
        	Entry curr = entrys[index];
        	while (curr != null && curr.key != key) {
        		prev = curr;
        		curr = curr.next;
        	}
            if (curr == null)
                return;
        	else {
        		if (prev == null) {
                    curr = curr.next;
                    entrys[index] = curr;
                }
        		else
        			prev.next = curr.next;
        	}
        }
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int index = hashFunc(key);
        if (entrys[index] == null)
        	return false;
        else {
        	Entry curr = entrys[index];
        	while (curr != null && curr.key != key)
        		curr = curr.next;
        	if (curr == null)
        		return false;
        	return true;
        }
    }
    
    /** Returns index in Entry bucket for a particular key */
    public int hashFunc(int key) {
    	return Integer.hashCode(key) % entrys.length;
    }
}

class Entry {
	int key;
	Entry next;

	public Entry(int key) {
		this.key = key;
	}
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */