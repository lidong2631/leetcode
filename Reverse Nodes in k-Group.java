/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, curr = head;
        int i = 1;
        while (curr != null) {
            ListNode next = curr.next;
            if (i == k) {                   // every k nodes do a reverse
                prev = reverse(prev, next); // call reverse linked list ii subroutine
                i = 0;
            }
            curr = next;                    // careful here
            i++;
        }
        return dummy.next;
    }
    
    private ListNode reverse(ListNode prev, ListNode end) {
        ListNode head = prev.next, curr = prev.next.next;
        while (curr != end) {
            ListNode next = curr.next;
            curr.next = prev.next;      // same as reverse linkedlist ii
            prev.next = curr;
            curr = next;
        }
        head.next = end;
        return head;
    }
}

这题跟reverse list ii区别是不能边走边reverse 因为最后不足k的部分不reverse 所以不能用类似reverse list ii的套路

reverse list ii的套路即边走边reverse更好 只需要扫一遍 而且代码简洁



Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5





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