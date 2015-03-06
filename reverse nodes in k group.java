

from code ganker:

这道题是Swap Nodes in Pairs的扩展，Swap Nodes in Pairs其实是这道题k=2的特殊情况，大家可以先练习一下。不过实现起来还是比较不一样的，因为要处理比较general的情形。

基本思路是这样的，我们统计目前节点数量，如果到达k，就把当前k个结点reverse，这里需要reverse linked list的subroutine。这里我们需要先往前走，到达k的时候才做reverse，

所以总体来说每个结点会被访问两次。总时间复杂度是O(2*n)=O(n)，空间复杂度是O(1)。实现的代码如下：

public ListNode reverseKGroup(ListNode head, int k) {
    if(head == null)
    {
        return null;
    }
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    int count = 0;
    ListNode pre = dummy;
    ListNode cur = head;
    while(cur != null)
    {
        count ++;
        ListNode next = cur.next;
        if(count == k)
        {
            pre = reverse(pre, next);
            count = 0;   
        }
        cur = next;
    }
    return dummy.next;
}
private ListNode reverse(ListNode pre, ListNode end)
{
    if(pre==null || pre.next==null)
        return pre;
    ListNode head = pre.next;
    ListNode cur = pre.next.next;
    while(cur!=end)
    {
        ListNode next = cur.next;
        cur.next = pre.next;
        pre.next = cur;
        cur = next;
    }
    head.next = end;
    return head;
}

有朋友可能会说为什么不边走边reverse，这样可以省一个pass。但是问题是这个题目的要求中最后如果不足k个不需要reverse，所以没法边走边倒。

这道题考查的还是链表的基本操作，其中reverse是一个非常重要的链表操作，大家要多练习，尽量做到一遍做对无bug。