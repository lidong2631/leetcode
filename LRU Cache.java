public class LRUCache {
    class Node {
        int key;
        int val;
        Node pre;
        Node next;
        
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    private Map<Integer, Node> map;
    private int num;
    private int capacity;
    Node first, last;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.num = 0;
        map = new HashMap<Integer, Node>();
        first = null;
        last = null;
    }
    
    public int get(int key) {
        Node curr = map.get(key);
        if(curr==null) return -1;
        updatePos(curr);
        return curr.val;
    }
    
    public void set(int key, int value) {
        Node curr = map.get(key);
        if(curr!=null) {
            curr.val = value;
            updatePos(curr);
        }
        else {
            Node newNode = new Node(key, value);
            if(num>=capacity) {
                map.remove(first.key);
                first = first.next;
                if(first!=null)
                    first.pre = null;
                else
                    last = null;
                num--;
            }
            if(first==null)
                first = newNode;
            else
                last.next = newNode;
            newNode.pre = last;
            last = newNode;
            num++;
            map.put(key, newNode);
        }
    }
    
    private void updatePos(Node curr) {
        if(curr!=last) {
            if(curr==first)
                first = first.next;
            else
                curr.pre.next = curr.next; // use doubly linkedlist because we can access to previous node in O(1)
            curr.next.pre = curr.pre;
            last.next = curr;
            curr.pre = last;
            curr.next = null;
            last = curr;
        }
    }
}


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