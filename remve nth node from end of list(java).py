<<<<<<< HEAD
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @return a ListNode
    def removeNthFromEnd(self, head, n):
        newNode = ListNode(0)       #新建一节点newNode指向head
        newNode.next = head
        p1 = p2 = newNode           #p1, p2指向newNode
        for i in range(n):          #循环n次 让p2提前n个节点
            p2 = p2.next
        while p2.next:          #循环直到p2到队尾 此时p1所在位置即是要删除节点的前一节点
            p1 = p1.next
            p2 = p2.next
        p1.next = p1.next.next      #删除该节点
        return newNode.next

Note: 这题要往head节点前加一节点 否则有很多情况要考虑 如删除头节点等






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
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null)
            return null;
        ListNode p1 = head;
        int i=0;
        while(p1!=null && i<n)  //先让p1跑n
        {
            p1 = p1.next;
            i++;
        }
        if(i<n)     //如果i<n 说明n大于链表长度 是不合法输入 我们返回head
            return head;
        if(p1==null)        //如果p1恰好是null 说明n就是链表长度 我们要移除头节点 返回head.next
            return head.next;
        ListNode p2 = head;
        while(p1.next!=null)    //第二个指针开始移动 老套路 当p1.next为空时 p2.next = p2.next.next
        {
            p1 = p1.next;
            p2 = p2.next;
        }
        p2.next = p2.next.next;
        return head;
    }
}

Note: 根据code ganker改编 本题假设输入的n都有效 code ganker版有处理输入n无效时的情况 在83到89行

python版是假设输入都有效



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

=======
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @return a ListNode
    def removeNthFromEnd(self, head, n):
        newNode = ListNode(0)       #新建一节点newNode指向head
        newNode.next = head
        p1 = p2 = newNode           #p1, p2指向newNode
        for i in range(n):          #循环n次 让p2提前n个节点
            p2 = p2.next
        while p2.next:          #循环直到p2到队尾 此时p1所在位置即是要删除节点的前一节点
            p1 = p1.next
            p2 = p2.next
        p1.next = p1.next.next      #删除该节点
        return newNode.next

Note: 这题要往head节点前加一节点 否则有很多情况要考虑 如删除头节点等






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
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null)
            return null;
        ListNode p1 = head;
        int i=0;
        while(p1!=null && i<n)  //先让p1跑n
        {
            p1 = p1.next;
            i++;
        }
        if(i<n)     //如果i<n 说明n大于链表长度 是不合法输入 我们返回head
            return head;
        if(p1==null)        //如果p1恰好是null 说明n就是链表长度 我们要移除头节点 返回head.next
            return head.next;
        ListNode p2 = head;
        while(p1.next!=null)    //第二个指针开始移动 老套路 当p1.next为空时 p2.next = p2.next.next
        {
            p1 = p1.next;
            p2 = p2.next;
        }
        p2.next = p2.next.next;
        return head;
    }
}

Note: 根据code ganker改编 本题假设输入的n都有效 code ganker版有处理输入n无效时的情况 在83到89行

python版是假设输入都有效



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

>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
