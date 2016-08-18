解题思路：

1，先将链表截断为两个相等长度的链表，如果链表长度为奇数，则第一条链表长度多1。如原链表为L={1,2,3,4,5}，那么拆分结果为L1={1,2,3}；L2={4,5}。

    拆分的技巧还是快慢指针的技巧。

2，将第二条链表L2翻转，如将L2={4,5}翻转为L2={5,4}。

3，按照题意归并链表。


# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @return nothing
    def reorderList(self, head):
        if head==None or head.next==None or head.next.next==None: return head
        
        # break linked list into two equal length
        slow = fast = head                              #快慢指针技巧
        while fast and fast.next:                       #需要熟练掌握
            slow = slow.next                            #链表操作中常用
            fast = fast.next.next
        head1 = head
        head2 = slow.next
        slow.next = None

        # reverse linked list head2
        dummy=ListNode(0); dummy.next=head2             #翻转前加一个头结点
        p=head2.next; head2.next=None                   #将p指向的节点一个一个插入到dummy后面
        while p:                                        #就完成了链表的翻转
            tmp=p; p=p.next                             #运行时注意去掉中文注释
            tmp.next=dummy.next
            dummy.next=tmp
        head2=dummy.next

        # merge two linked list head1 and head2
        p1 = head1; p2 = head2
        while p2:
            tmp1 = p1.next; tmp2 = p2.next
            p1.next = p2; p2.next = tmp1
            p1 = tmp1; p2 = tmp2




/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {       // careful here
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode prev = slow.next, curr = slow.next.next;
        prev.next = null;
        slow.next = null; slow = head;
        while (curr != null) {          // reverse linked list
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        while (slow != null && prev != null) {
            ListNode next1 = slow.next, next2 = prev.next;
            slow.next = prev; prev.next = next1;
            slow = next1; prev = next2;
        }
    }
}






from code ganker:

这是一道比较综合的链表操作的题目，要按照题目要求给链表重新连接成要求的结果。其实理清思路也比较简单，分三步完成：

（1）将链表切成两半，也就是找到中点，然后截成两条链表；（2）将后面一条链表进行reverse操作，就是反转过来；（3）将两条链表按顺序依次merge起来。

这几个操作都是我们曾经接触过的操作了，第一步找中点就是用Linked List Cycle中的walker-runner方法，一个两倍速跑，一个一倍速跑，知道快的碰到链表尾部，

慢的就正好停在中点了。第二步是比较常见的reverse操作，在Reverse Nodes in k-Group也有用到了，一般就是一个个的翻转过来即可。第三步是一个merge操作，

做法类似于Sort List中的merge，只是这里不需要比较元素打小了，只要按顺序左边取一个，右边取一个就可以了。

接下来看看时间复杂度，第一步扫描链表一遍，是O(n)，第二步对半条链表做一次反转，也是O(n)，第三部对两条半链表进行合并，也是一遍O(n)。

所以总的时间复杂度还是O(n)，由于过程中没有用到额外空间，所以空间复杂度O(1)。代码如下：

public void reorderList(ListNode head) {
    if(head == null || head.next==null)
    {
        return;
    }
    ListNode walker = head;
    ListNode runner = head;
    while(runner.next!=null && runner.next.next!=null)
    {
        walker = walker.next;
        runner = runner.next.next;
    }
    ListNode head1 = head;
    ListNode head2 = walker.next;
    walker.next = null;
    head2 = reverse(head2);
    while(head1!=null && head2!=null)
    {
        ListNode next = head2.next;
        head2.next = head1.next;
        head1.next = head2;
        head1 = head2.next;
        head2 = next;
    }
}
private ListNode reverse(ListNode head)
{
    ListNode pre = null;
    ListNode cur = head;
    while(cur!=null)
    {
        ListNode next = cur.next;
        cur.next = pre;
        pre = cur;
        cur = next;
    }
    return pre;
} 

这道题看起来比较复杂，其实理清思路之后就都是链表常见的几个基本操作。这里我想多说一下reverse操作，因为这是链表最常见的操作。

有时候在第一轮电面这种比较基础的面试中，可能会要求实现reverse操作，但是因为有点过于简单，面试官会要求递归和非递归都实现一下。

上面的代码使用非递归的方式实现reverse。下面我们列举一下递归的代码，有兴趣的朋友可以看看哈。

public ListNode recursive_reverse(ListNode head) {
    if(head == null || head.next==null)
        return head;
    return recursive_reverse(head, head.next);
}
private ListNode recursive_reverse(ListNode current, ListNode next) 
{
    if (next == null) return current;
    ListNode newHead = recursive_reverse(current.next, next.next);
    next.next = current;
    current.next = null;
    return newHead;
}
