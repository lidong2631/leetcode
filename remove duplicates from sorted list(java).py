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
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null || head.next == null)
            return head;
        ListNode p = head;
        while(p.next!=null)
        {
            if(p.val==p.next.val)
                p.next = p.next.next;
            else
                p = p.next;
        }
        return head;
    }
}

Note: 简单题不说什么了






from code ganker:

这是一道比较简单的链表操作的题目，要求是删去有序链表中重复的元素。方法比较清晰，维护两个指针，一个指向当前不重复的最后一个元素，一个进行依次扫描，

遇到不重复的则更新第一个指针，继续扫描，否则就把前面指针指向当前元素的下一个（即把当前元素从链表中删除）。时间上只需要一次扫描，所以是O(n)，

空间上两个额外指针，是O(1)。代码如下： 

public ListNode deleteDuplicates(ListNode head) {
    if(head == null)
        return head;
    ListNode pre = head;
    ListNode cur = head.next;
    while(cur!=null)
    {
        if(cur.val == pre.val)
            pre.next = cur.next;
        else    
            pre = cur;
        cur = cur.next;
    }
    return head;
}