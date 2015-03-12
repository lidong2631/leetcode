# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @return a ListNode
    def insertionSortList(self, head):
        if head == None:
            return head
        newNode = ListNode(0)       #加一个节点
        newNode.next = head
        current = head
        while current.next:
            if current.next.val < current.val:      #不是递增顺序 就从head开始找到大于current.next.val的数
                previous  = newNode
                while previous.next.val < current.next.val:     #从头开始找到第一个大于current.next.val的节点
                    previous = previous.next
                tmp = current.next                  #插入 4步 1－保存当前current.next值给tmp 2－将current下一节点指向current.next.next（跳过current.next节点 原位置删除current.next节点)
                current.next = tmp.next             #3－原先current.next的下一节点指向第一个比它大的节点previous.next 4-previous下一节点指向current.next 即tmp节点（插入current.next节点）
                tmp.next = previous.next
                previous.next = tmp
            else:                           #如果是递增顺序就继续到下一个节点判断
                current = current.next
        return newNode.next
            






# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @return a ListNode
    def insertionSortList(self, head):
        if head == None:
            return head
        newNode = ListNode(0)
        newNode.next = head
        start = head
        while start.next:
            p = start.next
            previous = newNode
            current = head
            while current!=p and p.val > current.val:
                previous = current
                current = current.next
            if current == p:
                previous.next = p
            else:
                previous.next = p
                tmp.next = p.next
                p.next = current
            start = start.next
        return newNode.next




由python版改成java:

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
    public ListNode insertionSortList(ListNode head) {
        if(head==null)
            return null;
        ListNode newNode = new ListNode(0);
        newNode.next = head;
        ListNode curr = head;
        while(curr.next!=null)
        {
            if(curr.val>curr.next.val)  //不是递增顺序 就从head开始找到大于current.next.val的数
            {
                ListNode prev = newNode;
                while(prev.next.val<curr.next.val)//从头开始找到第一个大于current.next.val的节点
                    prev = prev.next;
                ListNode tmp = curr.next;       //这里要记住那4步
                curr.next = curr.next.next;
                tmp.next = prev.next;
                prev.next = tmp;
            }
            else
                curr = curr.next;
        }
        return head;
    }
}