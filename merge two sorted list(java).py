<<<<<<< HEAD
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param two ListNodes
    # @return a ListNode
    def mergeTwoLists(self, l1, l2):
        if l1 == None:
            return l2
        if l2 == None:
            return l1
        newNode = ListNode(0)
        tmp = newNode
        while l1 and l2:
            if l1.val < l2.val:
                tmp.next = l1
                l1 = l1.next
                tmp = tmp.next
            else:
                tmp.next = l2
                l2 = l2.next
                tmp = tmp.next
        while l1:
            tmp.next = l1
            l1 = l1.next
            tmp = tmp.next
        while l2:
            tmp.next = l2
            l2 = l2.next
            tmp = tmp.next
        return newNode.next

Note: 归并的链表实现



题意：Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

解题思路：合并两个已经排好序的链表。

代码：


# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param two ListNodes
    # @return a ListNode
    def mergeTwoLists(self, l1, l2):
        if l1 == None:
            return l2
        if l2 == None:
            return l1
        dummy = ListNode(0)
        tmp = dummy
        while l1 and l2:
            if l1.val <= l2.val:
                tmp.next = l1
                l1 = l1.next
                tmp = tmp.next
            else:
                tmp.next = l2
                l2 = l2.next
                tmp = tmp.next
        if l2 == None:
            tmp.next = l1
        else:
            tmp.next = l2
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode newHead = new ListNode(0);
        ListNode tmp = newHead;
        while(l1!=null && l2!=null)
        {
            if(l1.val<l2.val)
            {
                tmp.next = l1;
                l1 = l1.next;
                tmp = tmp.next;
            }
            else
            {
                tmp.next = l2;
                l2 = l2.next;
                tmp = tmp.next;
            }
        }
        while(l1!=null)
        {
            tmp.next = l1;
            l1 = l1.next;
            tmp = tmp.next;
        }
        while(l2!=null)
        {
            tmp.next = l2;
            l2 = l2.next;
            tmp = tmp.next;
        }
        return newHead.next;
    }
}

Note: 没什么好说的 简单一定要数量

T:O(m+n) S:O(1)




from code ganker:

这道题目比较简单，经典的链表基本操作。维护两个指针对应两个链表，因为一般会以一条链表为基准，比如说l1, 那么如果l1当期那的元素比较小，那么直接移动l1即可，

否则将l2当前的元素插入到l1当前元素的前面。算法时间复杂度是O(m+n),m和n分别是两条链表的长度，空间复杂度是O(1)，代码如下：

public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode helper = new ListNode(0);
    ListNode pre = helper;
    helper.next = l1;
    while(l1!=null && l2 != null)
    {
        if(l1.val>l2.val)
        {
            ListNode next = l2.next;
            l2.next = pre.next;
            pre.next = l2;
            l2 = next;
        }
        else
        {
            l1 = l1.next;
        }
        pre = pre.next;

    }
    if(l2!=null)
    {
        pre.next = l2;
    }
    return helper.next;
}

这个题类似的有Merge Sorted Array，只是后者是对数组进行合并操作，面试中可能会一起问到。扩展题目Merge k Sorted Lists, 

这是一个在分布式系统中比较有用的基本操作，还是需要重视，面试中可以发散出很多问题


=======
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param two ListNodes
    # @return a ListNode
    def mergeTwoLists(self, l1, l2):
        if l1 == None:
            return l2
        if l2 == None:
            return l1
        newNode = ListNode(0)
        tmp = newNode
        while l1 and l2:
            if l1.val < l2.val:
                tmp.next = l1
                l1 = l1.next
                tmp = tmp.next
            else:
                tmp.next = l2
                l2 = l2.next
                tmp = tmp.next
        while l1:
            tmp.next = l1
            l1 = l1.next
            tmp = tmp.next
        while l2:
            tmp.next = l2
            l2 = l2.next
            tmp = tmp.next
        return newNode.next

Note: 归并的链表实现



题意：Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

解题思路：合并两个已经排好序的链表。

代码：


# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param two ListNodes
    # @return a ListNode
    def mergeTwoLists(self, l1, l2):
        if l1 == None:
            return l2
        if l2 == None:
            return l1
        dummy = ListNode(0)
        tmp = dummy
        while l1 and l2:
            if l1.val <= l2.val:
                tmp.next = l1
                l1 = l1.next
                tmp = tmp.next
            else:
                tmp.next = l2
                l2 = l2.next
                tmp = tmp.next
        if l2 == None:
            tmp.next = l1
        else:
            tmp.next = l2
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode newHead = new ListNode(0);
        ListNode tmp = newHead;
        while(l1!=null && l2!=null)
        {
            if(l1.val<l2.val)
            {
                tmp.next = l1;
                l1 = l1.next;
                tmp = tmp.next;
            }
            else
            {
                tmp.next = l2;
                l2 = l2.next;
                tmp = tmp.next;
            }
        }
        while(l1!=null)
        {
            tmp.next = l1;
            l1 = l1.next;
            tmp = tmp.next;
        }
        while(l2!=null)
        {
            tmp.next = l2;
            l2 = l2.next;
            tmp = tmp.next;
        }
        return newHead.next;
    }
}

Note: 没什么好说的 简单一定要数量

T:O(m+n) S:O(1)




from code ganker:

这道题目比较简单，经典的链表基本操作。维护两个指针对应两个链表，因为一般会以一条链表为基准，比如说l1, 那么如果l1当期那的元素比较小，那么直接移动l1即可，

否则将l2当前的元素插入到l1当前元素的前面。算法时间复杂度是O(m+n),m和n分别是两条链表的长度，空间复杂度是O(1)，代码如下：

public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode helper = new ListNode(0);
    ListNode pre = helper;
    helper.next = l1;
    while(l1!=null && l2 != null)
    {
        if(l1.val>l2.val)
        {
            ListNode next = l2.next;
            l2.next = pre.next;
            pre.next = l2;
            l2 = next;
        }
        else
        {
            l1 = l1.next;
        }
        pre = pre.next;

    }
    if(l2!=null)
    {
        pre.next = l2;
    }
    return helper.next;
}

这个题类似的有Merge Sorted Array，只是后者是对数组进行合并操作，面试中可能会一起问到。扩展题目Merge k Sorted Lists, 

这是一个在分布式系统中比较有用的基本操作，还是需要重视，面试中可以发散出很多问题


>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
