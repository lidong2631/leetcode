# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @return a ListNode
    def deleteDuplicates(self, head):
        if head == None or head.next == None:
            return head
        newNode = ListNode(0)
        newNode.next = head
        p = newNode; tmp = head
        while p.next:                   #只要p.next存在就一直进行循环
            while tmp.next and tmp.next.val == p.next.val:      #如果是重复的值 tmp keep going直到最后一个重复值
                tmp = tmp.next
            if p.next == tmp:           #如果tmp是p的下一节点 说明是非重复数值 将p和tmp都往前移动一个节点
                p = p.next
                tmp = p.next
            else:                  #否则将p下一节点指向tmp.next 即下一个非重复的数值
                p.next = tmp.next
        return newNode.next

Note: 类似这种链表操作的题目 应熟练记住操作套路



题意：

Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.

解题思路：链表的基本操作。

代码：


# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @return a ListNode
    def deleteDuplicates(self, head):
        if head == None or head.next == None:
            return head
        dummy = ListNode(0); dummy.next = head
        p = dummy
        tmp = dummy.next
        while p.next:
            while tmp.next and tmp.next.val == p.next.val:
                tmp = tmp.next
            if tmp == p.next:
                p = p.next
                tmp = p.next
            else:
                p.next = tmp.next
        return dummy.next