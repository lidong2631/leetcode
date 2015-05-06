# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param a list of ListNode
    # @return a ListNode
    def mergeKLists(self, lists):
        heap = []
        for node in lists:
            if node:
                heap.append((node.val, node))
        head = ListNode(0)
        curr = head
        heapq.heapify(heap)
        while heap:
            pop = heapq.heappop(heap)
            curr.next = ListNode(pop[0])
            curr = curr.next
            if pop[1].next:
                heapq.heappush(heap, (pop[1].next.val, pop[1].next))
        return head.next




from code ganker:

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
        if(lists == null || lists.size() == 0)      //注意
            return null;
        return helper(lists, 0, lists.size()-1);        //调用helper
    }
    
    private ListNode helper(List<ListNode> lists, int left, int right) {        //每次分半 递归调用helper 直到就剩两个list 用merge将它们归并
        if(left<right)
        {
            int middle = (left+right)/2;
            return Merge(helper(lists, left, middle), helper(lists, middle+1, right));
        }
        return lists.get(left);
    }
    
    private ListNode Merge(ListNode l1, ListNode l2) {      //跟merge two sorted list完全一样
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode newHead = new ListNode(0);
        ListNode tmp = newHead;
        while(l1!=null && l2!=null)
        {
            if(l1.val<l2.val)
            {
                tmp.next = l1;
                l1 = l1.next;
                tmp = tmp.next;
            }
            else
            {
                tmp.next = l2;
                l2 = l2.next;
                tmp = tmp.next;
            }
        }
        while(l1!=null)
        {
            tmp.next = l1;
            l1 = l1.next;
            tmp = tmp.next;
        }
        while(l2!=null)
        {
            tmp.next = l2;
            l2 = l2.next;
            tmp = tmp.next;
        }
        return newHead.next;
    }
}

Note：时间复杂度为 O(nklogk) 可以联想merge sort这个要mergesort k个list 所以复杂度是O(klogk)同时每个list还有n个元素 所以merge时时O(n) 总时间复杂度是O(nklogk)

空间复杂度递归栈大小O(logk) 因为每次都在二分



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
        if(lists==null || lists.size()==0)      //注意 要写上
            return null;
        PriorityQueue<ListNode> heap = new PriorityQueue(10, new Comparator<ListNode>(){
            public int compare(ListNode l1, ListNode l2) {
                return l1.val-l2.val;
            }
        });
        for(int i=0; i<lists.size(); i++) {
            if(lists.get(i)!=null)          //这里要注意判断下元素是不是空
                heap.offer(lists.get(i));
        }
        ListNode curr = heap.poll();
        ListNode pre = curr;
        ListNode head = curr;
        while(heap.size()>0) {
            if(curr.next!=null)
                heap.offer(curr.next);
            curr = heap.poll();
            pre.next = curr;
            pre = curr;
        }
        return head;
    }
}

我的写法 这种解法有个地方要注意 就是127 - 132 不能写成如下
 while(heap.size()>0) {
    curr = heap.poll();
    pre.next = curr;
    pre = curr;
    if(curr.next!=null)
        heap.offer(curr.next);
}
假设碰到这个case {1,2,2}, {1,1,2}会出错{1,1,1,2} 因为while前已经poll了一次 进入while后首先poll一次 这样此时heap就空了 之后每次循环如果

都是对同一个list一弹出一进入 当这个list为空时if(curr.next!=null)这一步就不会再加元素了 这样就会跳出while循环而其他list的元素还没有加上

所以这里要特别注意不可以这样让heap始终维持在空的状态 至少要有一个元素在里面 这样新进来的元素才可以与之比较



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(10, new Comparator<ListNode>(){
        
        public int compare(ListNode n1, ListNode n2)
        {
            return n1.val - n2.val;
        }});
        for(int i=0; i<lists.size(); i++)   //将每个list头节点入堆
        {
            ListNode first = lists.get(i);
            if(first!=null)
                heap.offer(first);
        }
        ListNode head = null;
        ListNode prev = head;
        while(heap.size()>0)        //循环 每次poll出堆顶点加到结果list中 将该顶点对应的下一个节点加到堆中
        {
            ListNode curr = heap.poll();
            if(head==null)
            {
                head = curr;
                prev = head;
            }
            else
            {
                prev.next = curr;
            }
            prev = curr;
            if(curr.next!=null)
                heap.offer(curr.next);
        }
        return head;
    }
}

Note: 这个利用heapsort

顺便看看 PriorityQueue offer poll, Comparator


接下来我们来看第二种方法。这种方法用到了堆的数据结构，思路比较难想到，但是其实原理比较简单。维护一个大小为k的堆，每次取堆顶的最小元素放到结果中，

然后读取该元素的下一个元素放入堆中，重新维护好。因为每个链表是有序的，每次又是去当前k个元素中最小的，所以当所有链表都读完时结束，

这个时候所有元素按从小到大放在结果链表中。这个算法每个元素要读取一次，即是k*n次，然后每次读取元素要把新元素插入堆中要logk的复杂度，

所以总时间复杂度是O(nklogk)。空间复杂度是堆的大小，即为O(k)。代码如下：

public ListNode mergeKLists(ArrayList<ListNode> lists) {
    PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(10,new Comparator<ListNode>(){
            @Override
            public int compare(ListNode n1, ListNode n2)
            {
                return n1.val-n2.val;
            }
        });
    for(int i=0;i<lists.size();i++)
    {
        ListNode node = lists.get(i); 
        if(node!=null)
        {
            heap.offer(node);
        }
    }
    ListNode head = null;
    ListNode pre = head;
    while(heap.size()>0)
    {
        ListNode cur = heap.poll();
        if(head == null)
        {
            head = cur;
            pre = head;
        }
        else
        {
            pre.next = cur;
        }
        pre = cur;
        if(cur.next!=null)
            heap.offer(cur.next);
    }
    return head;
}

可以看出两种方法有着同样的时间复杂度，都是可以接受的解法，但是却代表了两种不同的思路，数据结构也不用。个人觉得两种方法都掌握会比较好哈，

因为在实际中比较有应用，所以也是比较常考的题目

