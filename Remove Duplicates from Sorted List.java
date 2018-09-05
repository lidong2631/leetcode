Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:

Input: 1->1->2
Output: 1->2
Example 2:

Input: 1->1->2->3->3
Output: 1->2->3



Python:
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def deleteDuplicates(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        p1, p2 = head, head
        while p2:
            while p2 and p1.val == p2.val:
                p2 = p2.next
            p1.next = p2
            p1 = p1.next
        return head



Java:
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode p1 = head, p2 = head;
        while (p2 != null) {
            while (p2 != null && p1.val == p2.val) p2 = p2.next;
            p1.next = p2;
            p1 = p1.next;
        }
        return head;
    }
}



Go:
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func deleteDuplicates(head *ListNode) *ListNode {
    p1, p2 := head, head
    for p2 != nil {
        for p2 != nil && p1.Val == p2.Val {
            p2 = p2.Next
        }
        p1.Next = p2
        p1 = p1.Next
    }
    return head
}

https://stackoverflow.com/questions/28447297/how-to-check-for-an-empty-struct




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