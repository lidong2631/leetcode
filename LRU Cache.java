Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4


This is the laziest implementation using Java's LinkedHashMap. In the real interview, however, it is definitely not what interviewer expected.

import java.util.LinkedHashMap;

public class LRUCache {
    private LinkedHashMap<Integer, Integer> map;
    private final int CAPACITY;
    public LRUCache(int capacity) {
        CAPACITY = capacity;
        map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > CAPACITY;
            }
        };
    }
    public int get(int key) {
        return map.getOrDefault(key, -1);
    }
    public void set(int key, int value) {
        map.put(key, value);
    }
}

In the constructor, the third boolean parameter specifies the ordering mode. If we set it to true, it will be in access order. 
(https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html#LinkedHashMap-int-float-boolean-)
By overriding removeEldestEntry in this way, we do not need to take care of it ourselves. 
It will automatically remove the least recent one when the size of map exceeds the specified capacity.
(https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html#removeEldestEntry-java.util.Map.Entry-)



Below is a "normal" HashMap + doubly-linked list implementation:

public class LRUCache {
    
    private class Node {
        int key, val;
        Node prev, next;

        Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
        Node() {
            this(0, 0);
        }
    }

    private int capacity, count;
    private Map<Integer, Node> map;
    private Node head, tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node n = map.get(key);
        if (n == null) {
            return -1;
        }
        update(n);
        return n.val;
    }
    
    public void set(int key, int val) {
        Node n = map.get(key);
        if (n == null) {
            n = new Node(key, val);
            map.put(key, n);
            add(n);
            count++;
        } else {
            n.val = val;
            update(n);
        }
        if (count > capacity) {
            Node toDel = tail.prev;
            remove(toDel);
            map.remove(toDel.key);
            count--;
        }
    }
    
    private void update(Node node){
        remove(node);
        add(node);
    }

    private void add(Node node) {
        Node after = head.next;
        head.next = node;
        node.prev = head;
        node.next = after;
        after.prev = node;
    }
    
    private void remove(Node node) {
        Node before = node.prev, after = node.next;
        before.next = after;
        after.prev = before;
    }
}

https://leetcode.com/problems/lru-cache/discuss/45939/Laziest-implementation:-Java's-LinkedHashMap-takes-care-of-everything


see also LFU cache http://stackoverflow.com/questions/21117636/how-to-implement-a-least-frequently-used-lfu-cache




from code ganker:

这是一道非常综合的题目，主要应用在操作系统的资源管理中。按照题目要求，要实现get和set功能，为了满足随机存储的需求我们首先想到的一般是用数组，

如果用链表会有O(n)的访问时间。然而他又有另一个条件就是要维护least used的队列，也就是说经常用的放在前面，用的少的放在后面。

这样当资源超过cache的容积的时候就可以把用得最少的资源删掉。这就要求我们要对节点有好的删除和插入操作，这个要求又会让我们想到用链表，

因为数组的删除和插入是O(n)复杂度的。

那么我们能不能维护一个数据结构使得访问操作和插入删除操作都是O(1)复杂度的呢？答案是肯定的。这个数据结构比较复杂，

是用一个hash表加上一个双向链表来实现。基本思路是这样的，用一个hash表来维护结点的位置关系，也就是hash表的key就是资源本身的key，

value是资源的结点（包含key和value的信息）。然后把结点维护成一个双向链表构成的队列，这样子如果我们要访问某一个结点，

那么可以通过hash表和key来找到结点，从而取到相应的value。而当我们想要删除或者插入结点时，我们还是通过hash表找到结点，

然后通过双向链表和队列的尾结点把自己删除同时插入到队尾。通过hash表访问结点我们可以认为是O(1)的操作（假设hash函数足够好），

然后双向链表的插入删除操作也是O(1)的操作。如此我们便实现了用O(1)时间来完成所有LRU cache的操作。

空间上就是对于每一个资源有一个hash表的的项以及一个对应的结点（包含前后指针和资源的<key, value>）。代码如下：

public class LRUCache {
    class Node
    {
        Node pre, next;
        int key;
        int val;
        public Node(int key, int value)
        {
            this.key = key;
            this.val = value;
        }
    }
    
    private int capacity;
    private int num;
    private HashMap<Integer, Node> map;
    private Node first, last;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        num = 0;
        map = new HashMap<Integer, Node>();
        first = null;   //first is least recently visited node
        last = null;    //last is most recently visited node
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if(node == null)
            return -1;
        else if(node!=last)
        {
            if(node == first)       //这个routine要记住 是update node到most recent visited point上
                first = first.next;
            else
                node.pre.next = node.next;
            node.next.pre = node.pre;
            last.next = node;
            node.pre = last;
            node.next = null;
            last = node;
        }
        return node.val;
    }
    
    public void set(int key, int value) {
        Node node = map.get(key);
        if(node != null)    //更新node
        {
            node.val = value;
            if(node!=last)
            {
                if(node == first)
                    first = first.next;
                else
                    node.pre.next = node.next;
                node.next.pre = node.pre;
                last.next = node;
                node.pre = last;
                node.next = null;
                last = node;
            }
        }
        else    //新建node
        {
            Node newNode = new Node(key,value);

            if(num>=capacity)
            {
                map.remove(first.key);
                first = first.next;
                if(first!=null)
                    first.pre = null;
                else
                    last = null;
                num--;    
            }
            if(first == null || last == null)
            {
                first = newNode;
            }
            else
            {
                last.next = newNode;
            }
            newNode.pre = last;
            last = newNode;
            map.put(key,newNode);
            num++;
        }

    }
}

实现的时候还是有很多细节的，因为我们不经常使用双向链表，插入删除操作要维护前后指针，并且同时要维护成队列，增加了许多注意点。

这道题是一道很实际的题目，思路和数据结构都是很适合面试的题目，但是代码量有些偏大，所以一般只在onsite的时候有可能遇到，


可能也不会让你完整地写出全部代码，主要还是讲出维护的数据结构和各种操作复杂度的分析