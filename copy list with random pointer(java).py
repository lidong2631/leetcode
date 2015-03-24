<<<<<<< HEAD
# Definition for singly-linked list with a random pointer.
# class RandomListNode:
#     def __init__(self, x):
#         self.label = x
#         self.next = None
#         self.random = None

class Solution:
    # @param head, a RandomListNode
    # @return a RandomListNode
    def copyRandomList(self, head):
        if head == None:
            return None
        
        tmp = head                                         #原链表每个节点后都copy一个新节点
        while tmp:
            newNode = RandomListNode(tmp.label)
            newNode.next = tmp.next
            tmp.next = newNode
            tmp = tmp.next.next
        
        tmp = head                                         #copy每个节点的random link
        while tmp:
            if tmp.random:
                tmp.next.random = tmp.random.next
            tmp = tmp.next.next
        
        old = head                                         #最后将copy链表拆出来
        newHead = head.next
        new = newHead
        while new.next:
            old.next = new.next
            old = old.next
            new.next = old.next
            new = new.next
        new.next = old.next = None
        return newHead


Note: 三步， 需要熟记链表的各种基本操作套路 这一题包括copy每一个节点 copy每个节点的random pointer 以及如何拆分链表





解题思路：这题主要是需要深拷贝。看图就明白怎么写程序了。

 



首先，在原链表的每个节点后面都插入一个新节点，新节点的内容和前面的节点一样。比如上图，1后面插入1，2后面插入2，依次类推。

其次，原链表中的random指针如何映射呢？比如上图中，1节点的random指针指向3，4节点的random指针指向2。如果有一个tmp指针指向1（蓝色），则一条语句：tmp.next.random = tmp.random.next；就可以解决这个问题。

第三步，将新的链表从上图这样的链表中拆分出来。

代码：


# Definition for singly-linked list with a random pointer.
# class RandomListNode:
#     def __init__(self, x):
#         self.label = x
#         self.next = None
#         self.random = None

class Solution:
    # @param head, a RandomListNode
    # @return a RandomListNode
    def copyRandomList(self, head):
        if head == None: return None
        tmp = head
        while tmp:
            newNode = RandomListNode(tmp.label)
            newNode.next = tmp.next
            tmp.next = newNode
            tmp = tmp.next.next
        tmp = head
        while tmp:
            if tmp.random:
                tmp.next.random = tmp.random.next
            tmp = tmp.next.next
        newhead = head.next
        pold = head
        pnew = newhead
        while pnew.next:
            pold.next = pnew.next
            pold = pold.next
            pnew.next = pold.next
            pnew = pnew.next
        pold.next = None
        pnew.next = None
        return newhead






/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head==null)
            return null;
        //HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode newHead = new RandomListNode(head.label);
        newHead.random = head.random;
        //map.put(head, newHead);
        RandomListNode prev = newHead;
        RandomListNode node = head.next;
        while(node!=null)
        {
            RandomListNode newNode = node;
            newNode.random = node.random;
            //map.put(node, newNode);
            prev.next = newNode;
            prev = newNode;
            node = node.next;
        }
        return newHead;
    }
}

Note: 这个程序是做的浅拷贝 即copy的链表的random指针指向 原始链表的节点 这个地方要特别注意 oj会报如下错误

Input:  {-1,-1}
Output: Random pointer of node with label -1 points to a node from the original list.
Expected:   {-1,-1}

关于深拷贝和浅拷贝的区别：
简单的来说就是，在有指针的情况下，浅拷贝只是增加了一个指针指向已经存在的内存，而深拷贝就是增加一个指针并且申请一个新的内存，使这个增加的指针指向这个新的内存，

采用深拷贝的情况下，释放内存的时候就不会出现在浅拷贝时重复释放同一内存的错误！









/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head==null)
            return null;
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode newHead = new RandomListNode(head.label);    //建立copy list的头节点
        RandomListNode curr = newHead;
        RandomListNode node = head.next;
        map.put(head, newHead);     //将原始list的头节点和copy list的头节点放到map
        while(node!=null) {
            RandomListNode copy = new RandomListNode(node.label);   //每次copy一个节点
            curr.next = copy;                                       //将这个节点连到上一个节点
            map.put(node, copy);                                    //map放入两个节点
            curr = copy;                                            //指针调到下一个节点
            node = node.next;
        }
        RandomListNode p1 = head;
        RandomListNode p2 = newHead;
        while(p1!=null) {                       //再扫一遍两个list 从map中找新list的random指针指向的值
            p2.random = map.get(p1.random);
            p1 = p1.next;
            p2 = p2.next;
        }
        return newHead;
    }
}

Note：这题是用哈希表做的深拷贝 时间O(n), 空间O(n) 有点绕要多想想






/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head==null)
            return null;
        RandomListNode node = head;
        while(node!=null) {
            RandomListNode newNode = new RandomListNode(node.label);
            newNode.next = node.next;
            node.next = newNode;
            node = node.next.next;
        }

        node = head;
        while(node!=null) {
            if(node.random!=null)
                node.next.random = node.random.next;
            node = node.next.next;
        }
        
        node = head;
        RandomListNode newHead = head.next;
        RandomListNode p = newHead;
        while(node!=null) {
            node.next = node.next.next;
            if(p.next!=null)
                p.next = p.next.next;
            node = node.next;
            p = p.next;
        }
        return newHead;
    }
}

第二遍写的 思路有点绕 多练几遍





/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head==null) return null;
        RandomListNode tmp = head;
        while(tmp!=null)            //复制
        {
            RandomListNode newNode = new RandomListNode(tmp.label);
            newNode.next = tmp.next;    //这里如果到了队尾 newNode的next可以直接指到null
            tmp.next = newNode;
            tmp = tmp.next.next;
        }
        tmp = head;
        while(tmp!=null)            //拷贝random指针
        {
            if(tmp.random!=null)    //注意要判断下random指针是否为空 否则会nullpointerexception
                tmp.next.random = tmp.random.next;
            tmp = tmp.next.next;
        }
        
        RandomListNode newHead = head.next;
        tmp = head;
        while(tmp!=null)            //拆分 
        {
            RandomListNode newNode = tmp.next;
            tmp.next = newNode.next;    //这里到了队尾 tmp的next就指向了null
            if(newNode.next!=null)      //这里却需要判断newNode的next是否为null 因为有newNode.next.next
                newNode.next = newNode.next.next;
            tmp = tmp.next;
        }
        return newHead;
    }
}

Note: 不用哈希表的解法 三步 1 在原链表每个节点后复制节点 2 拷贝random指针 3 拆分出新链表 三个while






from code ganker:

这是一道链表操作的题目，要求复制一个链表，不过链表的每个结点带有一个随机指针，指向某一个结点。

我们先介绍一种比较直接的算法，思路是先按照复制一个正常链表的方式复制，复制的时候把复制的结点做一个HashMap，以旧结点为key，新节点为value。

这么做的目的是为了第二遍扫描的时候我们按照这个哈希表把结点的随机指针接上。这个算法是比较容易想到的，总共要进行两次扫描，所以时间复杂度是O(2*n)=O(n)。

空间上需要一个哈希表来做结点的映射，所以空间复杂度也是O(n)。代码如下：

public RandomListNode copyRandomList(RandomListNode head) {
    if(head == null)
        return head;
    HashMap<RandomListNode,RandomListNode> map = new HashMap<RandomListNode,RandomListNode>();
    RandomListNode newHead = new RandomListNode(head.label);
    map.put(head,newHead);
    RandomListNode pre = newHead;
    RandomListNode node = head.next;
    while(node!=null)
    {
        RandomListNode newNode = new RandomListNode(node.label);
        map.put(node,newNode);
        pre.next = newNode;
        pre = newNode;
        node = node.next;
    }
    node = head;
    RandomListNode copyNode = newHead;
    while(node!=null)
    {
        copyNode.random = map.get(node.random);
        copyNode = copyNode.next;
        node = node.next;
    }
    return newHead;
}


那么有没有办法可以不用额外空间来完成这个任务呢？还是有的，前面我们需要一个哈希表的原因是当我们访问一个结点时可能它的随机指针指向的结点还没有访问过，结点还没有创建，

所以我们需要线性的额外空间。想避免使用额外空间，我们只能通过利用链表原来的数据结构来存储结点。基本思路是这样的，对链表进行三次扫描，第一次扫描对每个结点进行复制，

然后把复制出来的新节点接在原结点的next，也就是让链表变成一个重复链表，就是新旧更替；第二次扫描中我们把旧结点的随机指针赋给新节点的随机指针，

因为新结点都跟在旧结点的下一个，所以赋值比较简单，就是node.next.random = node.random.next，其中node.next就是新结点，因为第一次扫描我们就是把新结点接在旧结点后面。

现在我们把结点的随机指针都接好了，最后一次扫描我们把链表拆成两个，第一个还原原链表，而第二个就是我们要求的复制链表。因为现在链表是旧新更替，只要把每隔两个结点分别相连，

对链表进行分割即可。这个方法总共进行三次线性扫描，所以时间复杂度是O(n)。而这里并不需要额外空间，所以空间复杂度是O(1)。比起上面的方法，这里多做一次线性扫描，

但是不需要额外空间，还是比较值的。实现的代码如下：

public RandomListNode copyRandomList(RandomListNode head) {
    if(head == null)
        return head;
    RandomListNode node = head;
    while(node!=null)
    {
        RandomListNode newNode = new RandomListNode(node.label);
        newNode.next = node.next;
        node.next = newNode;
        node = newNode.next;
    }
    node = head;
    while(node!=null)
    {
        if(node.random != null)
            node.next.random = node.random.next;
        node = node.next.next;
    }
    RandomListNode newHead = head.next;
    node = head;
    while(node != null)
    {
        RandomListNode newNode = node.next;
        node.next = newNode.next;
        if(newNode.next!=null)
            newNode.next = newNode.next.next;
        node = node.next;
    }
    return newHead;
}

上面介绍了两种方法来解决这个问题，第二种方法利用了原来的链表省去了额外空间，虽然多进行一次扫描，不过对时间复杂度量级没有影响，还是对算法有提高的。

这个题目算是比较有难度的链表题目，既有基本操作，也需要一些算法思想