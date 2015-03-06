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
        tmp = previous = head
        while previous and previous.next:       #一个一个数的递进
            while tmp.next and tmp.val == tmp.next.val:     #对每一个数 跳过它后面所有重复值得节点 直接连接到下一个非重复值得节点
                tmp = tmp.next
            previous.next = tmp.next        
            previous = previous.next        #往后一个节点
            tmp = tmp.next                  #往后一个节点
        return head


Note: 下面的解法比我的解法简练 应采用下面的方法



题意：

Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.

解题思路：链表节点的去重。比较简单。

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
        p = head
        while p.next:
            if p.val == p.next.val:
                p.next = p.next.next
            else:
                p = p.next
        return head