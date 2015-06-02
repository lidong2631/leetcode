class LRUCache:

    # @param capacity, an integer
    def __init__(self, capacity):
        LRUCache.Dict = collections.OrderedDict()
        LRUCache.numItems = 0
        LRUCache.capacity = capacity

    # @return an integer
    def get(self, key):
        try:
            value = LRUCache.Dict[key]
            del LRUCache.Dict[key]
            LRUCache.Dict[key] = value
            return value
        except:
            return -1

    # @param key, an integer
    # @param value, an integer
    # @return nothing
    def set(self, key, value):
        try:
            del LRUCache.Dict[key]
            LRUCache.Dict[key] = value
        except:
            if LRUCache.capacity == LRUCache.numItems:
                LRUCache.Dict.popitem(last = False)
                LRUCache.numItems-=1
            LRUCache.Dict[key] = value
            LRUCache.numItems += 1
        return

Note:此解法from kitt 利用了python的OrderedDict数据结构 极大简化了代码 但这题考得是让你自己实现 用python感觉有点cheat感觉 

还是应该了解下java和python不利用已有数据结构的解法 不过OrderedDict也应该了解 感觉蛮实用 看下python doc



from doc:
Since an ordered dictionary remembers its insertion order, it can be used in conjuction with sorting to make a sorted dictionary:

>>> # regular unsorted dictionary
>>> d = {'banana': 3, 'apple':4, 'pear': 1, 'orange': 2}

>>> # dictionary sorted by key
>>> OrderedDict(sorted(d.items(), key=lambda t: t[0]))
OrderedDict([('apple', 4), ('banana', 3), ('orange', 2), ('pear', 1)])

>>> # dictionary sorted by value
>>> OrderedDict(sorted(d.items(), key=lambda t: t[1]))
OrderedDict([('pear', 1), ('orange', 2), ('banana', 3), ('apple', 4)])

>>> # dictionary sorted by length of the key string
>>> OrderedDict(sorted(d.items(), key=lambda t: len(t[0])))
OrderedDict([('pear', 1), ('apple', 4), ('orange', 2), ('banana', 3)])

The new sorted dictionaries maintain their sort order when entries are deleted. But when new keys are added, the keys are appended to the end and the sort is not maintained.

It is also straight-forward to create an ordered dictionary variant that remembers the order the keys were last inserted. If a new entry overwrites an existing entry, the original insertion position is changed and moved to the end:

class LastUpdatedOrderedDict(OrderedDict):
    'Store items in the order the keys were last added'

    def __setitem__(self, key, value):
        if key in self:
            del self[key]
        OrderedDict.__setitem__(self, key, value)


关于python 排序 讲得不错：

http://www.pythoncentral.io/how-to-sort-a-list-tuple-or-object-with-sorted-in-python/


from kitt:

Python的dictionary查找元素O(1)时间, 但缺点是无序。而collections.OrderedDict是有序的, 后加入的元素一定排在先加入元素的后面, 

操作和dictionary类似

import collections
a = collections.OrderedDict()
a[1] = 10
a[2] = 20
a[3] = 30
del a[2]
a.popitem(last = True)
a.popitem(last = False)





from 南郭:

南郭讲的挺清楚 但是代码过不了 回头改一下



题意：设计LRU Cache

参考文献：http://blog.csdn.net/hexinuaa/article/details/6630384 这篇博文总结的很到位。

　　　　   https://github.com/Linzertorte/LeetCode-in-Python/blob/master/LRUCache.py 代码参考的github人写的，思路非常清晰，写的也很好。

Cache简介：

Cache(高速缓存)， 一个在计算机中几乎随时接触的概念。CPU中Cache能极大提高存取数据和指令的时间，让整个存储器(Cache+内存)既有Cache的高速度，

又能有内存的大容量；操作系统中的内存page中使用的Cache能使得频繁读取的内存磁盘文件较少的被置换出内存，从而提高访问速度；

数据库中数据查询也用到Cache来提高效率；即便是Powerbuilder的DataWindow数据处理也用到了Cache的类似设计。

Cache的算法设计常见的有FIFO(first in first out)和LRU(least recently used)。根据题目的要求，显然是要设计一个LRU的Cache。

解题思路：

Cache中的存储空间往往是有限的，当Cache中的存储块被用完，而需要把新的数据Load进Cache的时候，我们就需要设计一种良好的算法来完成数据块的替换。

LRU的思想是基于“最近用到的数据被重用的概率比较早用到的大的多”这个设计规则来实现的。

为了能够快速删除最久没有访问的数据项和插入最新的数据项，我们双向链表连接Cache中的数据项，并且保证链表维持数据项从最近访问到最旧访问的顺序。

每次数据项被查询到时，都将此数据项移动到链表头部（O(1)的时间复杂度）。这样，在进行过多次查找操作后，最近被使用过的内容就向链表的头移动，

而没有被使用的内容就向链表的后面移动。当需要替换时，链表最后的位置就是最近最少被使用的数据项，我们只需要将最新的数据项放在链表头部，当Cache满时，

淘汰链表最后的位置就是了。

注： 对于双向链表的使用，基于两个考虑。首先是Cache中块的命中可能是随机的，和Load进来的顺序无关。其次，双向链表插入、删除很快，

可以灵活的调整相互间的次序，时间复杂度为O(1)。

查找一个链表中元素的时间复杂度是O(n)，每次命中的时候，我们就需要花费O(n)的时间来进行查找，如果不添加其他的数据结构，这个就是我们能实现的最高效率了。

目前看来，整个算法的瓶颈就是在查找这里了，怎么样才能提高查找的效率呢？Hash表，对，就是它，数据结构中之所以有它，就是因为它的查找时间复杂度是O(1)。

梳理一下思路：对于Cache的每个数据块，我们设计一个数据结构来储存Cache块的内容，并实现一个双向链表，其中属性next和prev是双向链表的两个指针，

key用于存储对象的键值，value用于存储cache块对象本身。

Cache的接口：

查询：

•根据键值查询hashmap，若命中，则返回节点key值对应的value，否则返回-1。
•从双向链表中删除命中的节点，将其重新插入到表头。
•所有操作的复杂度均为O(1)。
插入：

•将新的节点关联到Hashmap
•如果Cache满了，删除双向链表的尾节点，同时删除Hashmap对应的记录
•将新的节点插入到双向链表中头部
更新：

•和查询相似
删除：

•从双向链表和Hashmap中同时删除对应的记录。

class Node:　　　　　　　　　　　　　　　　　　　　　　　　　　#双向链表中节点的定义
    def __init__(self,k,x):
        self.key=k
        self.val=x
        self.prev=None
        self.next=None

class DoubleLinkedList:　　　　　　　　　　　　　　　　　　　#双向链表是一个表头，head指向第一个节点，tail指向最后一个节点
    def __init__(self):
        self.tail=None
        self.head=None
        
    def isEmpty():　　　　　　　　　　　　　　　　　　　　　　#如果self.tail==None，那么说明双向链表为空
        return not self.tail
    def removeLast(self):　　　　　　　　　　　　　　　　　　#删除tail指向的节点
        self.remove(self.tail)
        
    def remove(self,node):　　　　　　　　　　　　　　　　　 #具体双向链表节点删除操作的实现
        if self.head==self.tail:
            self.head,self.tail=None,None
            return
        if node == self.head:
            node.next.prev=None
            self.head=node.next
            return
        if node ==self.tail:
            node.prev.next=None
            self.tail=node.prev
            return
        node.prev.next=node.next
        node.next.prev=node.prev
        
    def addFirst(self,node):　　　　　　　　　　　　　　　　  #在双向链表的第一个节点前面再插入一个节点　　
        if not self.head:
            self.head=self.tail=node
            node.prev=node.next=None
            return
        node.next=self.head
        self.head.prev=node
        self.head=node
        node.prev=None

class LRUCache:

    # @param capacity, an integer
    def __init__(self, capacity):　　　　　　　　　　　　　　#初始化LRU Cache
        self.capacity=capacity　　　　　　　　　　　　　　　 #LRU Cache的容量大小　　　　　　　　　　　　　
        self.size=0　　　　　　　　　　　　　　　　　　　　　　#LRU Cache目前占用的容量
        self.P=dict()　　　　　　　　　　　　　　　　　　　　　#dict为文章中提到的hashmap，加快搜索速度，{key：对应节点的地址}
        self.cache=DoubleLinkedList()
    # @return an integer
    def get(self, key):　　　　　　　　　　　　　　　　　　　　#查询操作
        if (key in self.P) and self.P[key]:　　　　　　　　#如果key在字典中
            self.cache.remove(self.P[key])　　　　　　　　　#将key对应的指针指向的节点删除
            self.cache.addFirst(self.P[key])　　　　　　　　#然后将这个节点添加到双向链表头部
            return self.P[key].val　　　　　　　　　　　　　　#并返回节点的value
        else: return -1

    # @param key, an integer
    # @param value, an integer
    # @return nothing
    def set(self, key, value):　　　　　　　　　　　　　　　　#设置key对应的节点的值为给定的value值
        if key in self.P:　　　　　　　　　　　　　　　　　　　#如果key在字典中
            self.cache.remove(self.P[key])　　　　　　　　  #先删掉key对应的节点
            self.cache.addFirst(self.P[key])　　　　　　　　#然后将这个节点插入到表的头部
            self.P[key].val=value　　　　　　　　　　　　　　 #将这个节点的值val改写为value
        else:　　　　　　　　　　　　　　　　　　　　　　　　　　#如果key不在字典中
            node=Node(key,value)　　　　　　　　　　　　　　　#新建一个Node节点，val值为value
            self.P[key]=node　　　　　　　　　　　　　　　　　 #将key和node的对应关系添加到字典中
            self.cache.addFirst(node)　　　　　　　　　　　　#将这个节点添加到链表表头
            self.size +=1　　　　　　　　　　　　　　　　　　　#容量加1
            if self.size > self.capacity:　　　　　　　　   #如果链表的大小超过了缓存的大小，删掉最末尾的节点，
                self.size -=1　　　　　　　　　　　　　　　　 #并同时删除最末尾节点key值和末节点在字典中的对应关系
                del self.P[self.cache.tail.key]
                self.cache.removeLast()







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