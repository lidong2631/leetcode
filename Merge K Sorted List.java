Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6


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




/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return helper(lists, 0, lists.length-1);
    }
    
    private ListNode helper(ListNode[] lists, int left, int right) {
        if (left>=right) return lists[left];
        int mid = (left + right) / 2;
        ListNode l = helper(lists, left, mid);
        ListNode r = helper(lists, mid+1, right);
        return merge(l, r);
    }
    
    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        
        ListNode l = new ListNode(0);
        ListNode head = l;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                l.next = l1;
                l1 = l1.next; l = l.next;
            }
            else {
                l.next = l2;
                l2 = l2.next; l = l.next;
            }
        }
        while (l1 != null) {
            l.next = l1;
            l1 = l1.next; l = l.next;
        }
        while (l2 != null) {
            l.next = l2;
            l2 = l2.next; l = l.next;
        }
        return head.next;
    }
}



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
        
            public int compare(ListNode n1, ListNode n2) {
                return n1.val - n2.val;
            }});
        for (int i = 0; i < lists.size(); i++) {
            ListNode first = lists.get(i);
            if (first != null)
                heap.offer(first);
        }
        ListNode head = null;
        ListNode prev = head;
        while (heap.size() > 0) {
            ListNode curr = heap.poll();
            if (head == null) {
                head = curr;
                prev = head;
            }
            else {
                prev.next = curr;
            }
            prev = curr;
            if (curr.next != null)
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

