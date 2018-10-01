Design a HashMap without using any built-in hash table libraries.

To be specific, your design should include these functions:

put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.

Example:

MyHashMap hashMap = new MyHashMap();
hashMap.put(1, 1);          
hashMap.put(2, 2);         
hashMap.get(1);            // returns 1
hashMap.get(3);            // returns -1 (not found)
hashMap.put(2, 1);          // update the existing value
hashMap.get(2);            // returns 1 
hashMap.remove(2);          // remove the mapping for 2
hashMap.get(2);            // returns -1 (not found) 

Note:

All keys and values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashMap library.



Java:
class MyHashMap {

    private Entry[] entrys;

    /** Initialize your data structure here. */
    public MyHashMap() {
        entrys = new Entry[10000];
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = hashFunc(key);
        if (entrys[index] == null) 
        	entrys[index] = new Entry(key, value);
        else {
        	Entry curr = entrys[index];
        	while (curr.next != null && curr.key != key)
        		curr = curr.next;
        	if (curr.key == key)
        		curr.value = value;
        	else
        		curr.next = new Entry(key, value);
        }
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = hashFunc(key);
        if (entrys[index] == null)
        	return -1;
        else {
        	Entry curr = entrys[index];
        	while (curr != null && curr.key != key)
        		curr = curr.next;
        	if (curr == null)
        		return -1;
        	return curr.value;
        }
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
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

    /** Returns index in Entry bucket for a particular key */
    public int hashFunc(int key) {
    	return Integer.hashCode(key) % entrys.length;
    }
}

class Entry {
	int key;
	int value;
	Entry next;

	public Entry(int key, int value) {
		this.key = key;
		this.value = value;
	}
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
 */