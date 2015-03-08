<<<<<<< HEAD
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
        ListNode newNode = new ListNode(0);
        newNode.next = head;
        ListNode p = newNode;
        ListNode tmp = head;
        while(p.next!=null)
        {
            while(tmp.next!=null && (tmp.next.val==p.next.val))
                tmp = tmp.next;
            if(p.next==tmp)
            {
                p = p.next;
                tmp = tmp.next;
            }
            else
                p.next = tmp.next;
        }
        return newNode.next;
    }
}

Note: 直接用python改编版 这种题不需要想很多 直到一种解法就可以了




from code ganker:

这道题跟Remove Duplicates from Sorted List比较类似，只是这里要把出现重复的元素全部删除。其实道理还是一样，

只是现在要把前驱指针指向上一个不重复的元素中，如果找到不重复元素，则把前驱指针知道该元素，否则删除此元素。算法只需要一遍扫描，时间复杂度是O(n)，

空间只需要几个辅助指针，是O(1)。代码如下： 

public ListNode deleteDuplicates(ListNode head) {
    if(head == null)
        return head;
    ListNode helper = new ListNode(0);
    helper.next = head;
    ListNode pre = helper;
    ListNode cur = head;
    while(cur!=null)
    {
        while(cur.next!=null && pre.next.val==cur.next.val)
        {
            cur = cur.next;
        }
        if(pre.next==cur)
        {
            pre = pre.next;
        }
        else
        {
            pre.next = cur.next;
        }
        cur = cur.next;
    }
    
    return helper.next;
}

可以看到，上述代码中我们创建了一个辅助的头指针，是为了修改链表头的方便。前面介绍过，一般会改到链表头的题目就会需要一个辅助指针，是比较常见的技巧。

=======
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
        ListNode newNode = new ListNode(0);
        newNode.next = head;
        ListNode p = newNode;
        ListNode tmp = head;
        while(p.next!=null)
        {
            while(tmp.next!=null && (tmp.next.val==p.next.val))
                tmp = tmp.next;
            if(p.next==tmp)
            {
                p = p.next;
                tmp = tmp.next;
            }
            else
                p.next = tmp.next;
        }
        return newNode.next;
    }
}

Note: 直接用python改编版 这种题不需要想很多 直到一种解法就可以了




from code ganker:

这道题跟Remove Duplicates from Sorted List比较类似，只是这里要把出现重复的元素全部删除。其实道理还是一样，

只是现在要把前驱指针指向上一个不重复的元素中，如果找到不重复元素，则把前驱指针知道该元素，否则删除此元素。算法只需要一遍扫描，时间复杂度是O(n)，

空间只需要几个辅助指针，是O(1)。代码如下： 

public ListNode deleteDuplicates(ListNode head) {
    if(head == null)
        return head;
    ListNode helper = new ListNode(0);
    helper.next = head;
    ListNode pre = helper;
    ListNode cur = head;
    while(cur!=null)
    {
        while(cur.next!=null && pre.next.val==cur.next.val)
        {
            cur = cur.next;
        }
        if(pre.next==cur)
        {
            pre = pre.next;
        }
        else
        {
            pre.next = cur.next;
        }
        cur = cur.next;
    }
    
    return helper.next;
}

可以看到，上述代码中我们创建了一个辅助的头指针，是为了修改链表头的方便。前面介绍过，一般会改到链表头的题目就会需要一个辅助指针，是比较常见的技巧。

>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
