# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param a ListNode
    # @return a ListNode
    def swapPairs(self, head):
        if head == None or head.next == None:
            return head
        newNode = ListNode(0)
        newNode.next = head
        p = newNode
        while p.next and p.next.next:
            tmp = p.next.next           #每一对中的第二个值保存为tmp
            p.next.next = tmp.next      #将当前对中第一个值指向下一对第一个值
            tmp.next = p.next           #当前对第二个值指向第一个值
            p.next = tmp                #p指向当前对第二个值
            p = p.next.next             #p跳到当前对第一个值准备翻转下一对
        return newNode.next

Note: 同其他链表操作一样 看照片理解较快



题意：将链表中的节点两两交换。Given 1->2->3->4, you should return the list as 2->1->4->3.

解题思路：这题主要涉及到链表的操作，没什么特别的技巧，注意不要出错就好。最好加一个头结点，操作起来会很方便。

代码：

复制代码
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param a ListNode
    # @return a ListNode
    def swapPairs(self, head):
        if head == None or head.next == None:
            return head
        dummy = ListNode(0); dummy.next = head
        p = dummy
        while p.next and p.next.next:
            tmp = p.next.next
            p.next.next = tmp.next
            tmp.next = p.next
            p.next = tmp
            p = p.next.next
        return dummy.next
        





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
    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next==null)
            return head;
        ListNode newNode = new ListNode(0);
        newNode.next = head;
        ListNode p = newNode;
        while(p.next!=null && p.next.next!=null)
        {
            ListNode tmp = p.next.next;
            p.next.next = tmp.next;
            tmp.next = p.next;
            p.next = tmp;
            p = p.next.next;
        }
        return newNode.next;
    }
}


from code ganker:

这道题属于链表操作的题目，思路比较清晰，就是每次跳两个节点，后一个接到前面，前一个接到后一个的后面，最后现在的后一个（也就是原来的前一个）

接到下下个结点（如果没有则接到下一个）。代码如下：

public ListNode swapPairs(ListNode head) {
    if(head == null)
        return null;
    ListNode helper = new ListNode(0);
    helper.next = head;
    ListNode pre = helper;
    ListNode cur = head;
    while(cur!=null && cur.next!=null)
    {
        ListNode next = cur.next.next;
        cur.next.next = cur;
        pre.next = cur.next;
        if(next!=null && next.next!=null)
            cur.next = next.next;
        else
            cur.next = next;
        pre = cur;
        cur = next;
    }
    return helper.next;
}

这道题中用了一个辅助指针作为表头，这是链表中比较常用的小技巧，因为这样可以避免处理head的边界情况，一般来说要求的结果表头会有变化的会经常用这个技巧，大家以后会经常遇到。

因为这是一遍过的算法，时间复杂度明显是O(n)，空间复杂度是O(1)。实现中注意细节就可以了，不过我发现现在面试中链表操作的题目出现并不多，所以个人觉得大家练一下就好了，

不用花太多时间哈