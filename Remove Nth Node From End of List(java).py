题意：

Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.
   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.

这道题的含义是删除链表的倒数第n个节点。

解题思路：加一个头结点dummy，并使用双指针p1和p2。p1先向前移动n个节点，然后p1和p2同时移动，当p1.next==None时，此时p2.next指的就是需要删除的节点。

代码：


# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @return a ListNode
    def removeNthFromEnd(self, head, n):
        dummy=ListNode(0); dummy.next=head
        p1=p2=dummy
        for i in range(n): p1=p1.next
        while p1.next:
            p1=p1.next; p2=p2.next
        p2.next=p2.next.next
        return dummy.next


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode left = head, right = head;
        for (int i = 0; i < n; i++)
            right = right.next;
        if (right == null) return head.next;
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return head;
    }
}





from code ganker:

public ListNode removeNthFromEnd(ListNode head, int n) {
    if(head == null)
        return null;
    int i=0;
    ListNode runner = head;
    while(runner!=null && i<n)
    {
        runner = runner.next;
        i++;
    }
    if(i<n)
        return head;
    if(runner == null)
        return head.next;
    ListNode walker = head;
    while(runner.next!=null)
    {
        walker = walker.next;
        runner = runner.next;
    }
    walker.next = walker.next.next;
    return head;
}
