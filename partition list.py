# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @param x, an integer
    # @return a ListNode
    def partition(self, head, x):
        head1 = ListNode(0)     #
        head2 = ListNode(0)
        tmp = head              #
        p1 = head1              #
        p2 = head2
        while tmp:              #
            if tmp.val < x:         #
                p1.next = tmp
                tmp = tmp.next
                p1 = p1.next
                p1.next = None
            else:                   #
                p2.next = tmp
                tmp = tmp.next
                p2 = p2.next
                p2.next = None
        p1.next = head2.next        #
        return head1.next

Note: 这题也不难 要熟记套路



题意：

Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.

解题思路：解决链表问题时，最好加一个头结点，问题会比较好解决。对这道题来说，创建两个头结点head1和head2，head1这条链表是小于x值的节点的链表，head2链表是大于等于x值的节点的链表，然后将head2链表链接到head链表的尾部即可。

代码：


# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @param x, an integer
    # @return a ListNode
    def partition(self, head, x):
        head1 = ListNode(0)
        head2 = ListNode(0)
        Tmp = head
        phead1 = head1
        phead2 = head2
        while Tmp:
            if Tmp.val < x:
                phead1.next = Tmp
                Tmp = Tmp.next
                phead1 = phead1.next
                phead1.next = None
            else:
                phead2.next = Tmp
                Tmp = Tmp.next
                phead2 = phead2.next
                phead2.next = None
        phead1.next = head2.next
        head = head1.next
        return head