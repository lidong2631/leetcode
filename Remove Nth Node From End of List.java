题意：

Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.
   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.




Python:
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def removeNthFromEnd(self, head, n):
        """
        :type head: ListNode
        :type n: int
        :rtype: ListNode
        """
        l, r = head, head
        for i in range(n):
            r = r.next
        if r is None:
            return head.next
        while r.next is not None:
            l = l.next; r = r.next
        l.next = l.next.next
        return head


Java:
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



Golang:
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func removeNthFromEnd(head *ListNode, n int) *ListNode {
    l, r := head, head
    for i := 0; i < n; i++ {
        r = r.Next
    }
    if r == nil {
        return head.Next
    }
    for r.Next != nil {
        l, r = l.Next, r.Next
    }
    l.Next = l.Next.Next
    return head
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
