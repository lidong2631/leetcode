<<<<<<< HEAD
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @param m, an integer
    # @param n, an integer
    # @return a ListNode
    def reverseBetween(self, head, m, n):
        if head == None or head.next == None:   #如果head为空或只有head一个节点 返回head
            return head
        previous = ListNode(0)      #建立一个新节点previous放到链表head之前
        previous.next = head
        head1 = previous            #设置head1初始为previous
        for i in range(m-1):        #循环m-1次使head1指向第m个节点的前一个节点
            head1 = head1.next
        p = head1.next              #建立新的节点p使它指向第m个节点
        for j in range(n-m):        #循环n-m次
            tmp = head1.next        #先保存当前head1.next节点 这样它后一节点才可以指向它
            head1.next = p.next     #head1的下一节点往后移动一个 更新为p.next节点
            p.next = p.next.next    #p.next节点往后移一个 更新为p.next.next节点
            head1.next.next = tmp   #最后将tmp的下一节点指回tmp 即head1.next.next = tmp
        return previous.next

Note: 要画图才好了解每一步操作 看照片 整个过程即是一个点一个点往后移 每次反向两个相邻节点的指针




题意：

Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.

解题思路：翻转链表的题目。

代码：


# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @param m, an integer
    # @param n, an integer
    # @return a ListNode
    def reverseBetween(self, head, m, n):
        if head == None or head.next == None:
            return head
        dummy = ListNode(0); dummy.next = head
        head1 = dummy
        for i in range(m - 1):
            head1 = head1.next
        p = head1.next
        for i in range(n - m):
            tmp = head1.next
            head1.next = p.next
            p.next = p.next.next
            head1.next.next = tmp
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head==null || head.next==null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for(int i=0; i<m-1; i++)
            prev = prev.next;
        ListNode p = prev.next;
        ListNode curr = prev.next.next;
        for(int i=0; i<n-m; i++) {
            ListNode next = curr.next;
            curr.next = prev.next;
            prev.next = curr;
            curr = next;
        }
        p.next = curr;
        return dummy.next;
    }
}

跟reverse linked list ii 一样的套路





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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head==null || head.next==null)
            return head;
        ListNode newNode = new ListNode(0);
        newNode.next = head;
        ListNode prev = newNode;
        for(int i=0; i<m-1; i++)    //走到m节点前一个位置
            prev = prev.next;
        ListNode curr = prev.next;
        for(int i=0; i<n-m; i++)    //循环n-m次 两两reverse节点
        {
            ListNode tmp = prev.next;
            prev.next = curr.next;      //这里注意要写curr.next 不可写tmp.next因为tmp。next已经被reverse指向它前一个点了
            curr.next = curr.next.next;
            prev.next.next = tmp;
        }
        return newNode.next;
    }
}





/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head==null || head.next==null)
            return head;
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        dummy.next = head;
        for(int i=0; i<m-1; i++)
            prev = prev.next;
        ListNode end = prev.next;
        for(int i=0; i<n-m+1; i++)
            end = end.next;
        ListNode first = prev.next;
        ListNode curr = prev.next.next;
        while(curr!=end) {
            ListNode next = curr.next;
            curr.next = prev.next;
            prev.next = curr;
            curr = next;
        }
        first.next = end;
        return dummy.next;
    }
}

参照reverse nodes in k group套路写的